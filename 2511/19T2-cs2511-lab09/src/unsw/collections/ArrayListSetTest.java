package unsw.collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unsw.fruit.Apple;
import unsw.fruit.Fruit;
import unsw.fruit.Orange;

class ArrayListSetTest {

    @Test
    void testBasics() {
        Set<String> set = new ArrayListSet<>();
        set.add("Hello");
        set.add("World");
        assertTrue(set.contains("Hello"));
        assertTrue(set.contains("World"));

        set.remove("Hello");
        assertFalse(set.contains("Hello"));
        assertTrue(set.contains("World"));
        
        Set<String> set1 = new ArrayListSet<>();
        set1.add("Hello");
        set1.add("World");
       
        //test for union and intersection
        Set<String> union = set.union(set1);
        Set<String> intersection = set.intersection(set1);
        assertTrue(intersection.equals(set));
        assertFalse(union.equals(set));
        assertTrue(union.equals(set1));
        
        //test for subsetof
        assertTrue(set.subsetOf(set1));
        
        //test for equals
        Set<String> set2 = new ArrayListSet<>();
        set2.add("Hello");
        set2.add("World");
        assertTrue(set1.equals(set2));
        
        //test for iterator
    }

    @Test
    void testSubsetOf() {
        Set<Fruit> fruit = new ArrayListSet<Fruit>();
        fruit.add(new Apple("Gala"));
        fruit.add(new Apple("Fuji"));
        fruit.add(new Orange("Navel"));

        Set<Apple> apples = new ArrayListSet<>();
        apples.add(new Apple("Gala"));
        apples.add(new Apple("Fuji"));

        assertTrue(apples.subsetOf(fruit));
        assertFalse(fruit.subsetOf(apples));

        fruit.remove(new Apple("Fuji"));
        assertFalse(apples.subsetOf(fruit));
        
        Set<Orange> orange = new ArrayListSet<>();
        orange.add(new Orange("Navel"));
        
        assertTrue(orange.subsetOf(fruit));
        assertFalse(orange.subsetOf(apples));
        
        assertFalse(apples.equals(orange));
    }

}
