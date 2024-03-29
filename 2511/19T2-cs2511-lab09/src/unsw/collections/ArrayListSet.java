/**
 *
 */
package unsw.collections;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of Set that uses an ArrayList to store the elements.
 *
 * @invariant All e in elements occur only once
 *
 * @author Robert Clifton-Everest
 *
 */
public class ArrayListSet<E> implements Set<E> {

    private ArrayList<E> elements;

    public ArrayListSet() {
        elements = new ArrayList<>();
    }

    @Override
    public void add(E e) {
        if (!elements.contains(e))
            elements.add(e);
    }

    @Override
    public void remove(E e) {
        elements.remove(e);
    }

    @Override
    public boolean contains(Object e) {
        return elements.contains(e);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean subsetOf(Set<?> other) {
    	ArrayListSet<E> array = (ArrayListSet<E>) other;
        for(E e : this.elements) {
        	if(!array.contains(e)) {
        		return false;
        	}
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        // TODO Implement me
        return this.elements.iterator();
    	
    }

    @Override
    public Set<E> union(Set<? extends E> other) {
    	ArrayListSet<E> array= new ArrayListSet<E>();
    	for(E e :this.elements) {
    		array.add(e);
    	}
    	for(E e :other) {
    		if(!this.elements.contains(e)) {
    			array.add(e);
    		}
    	}
    	
        return array;
    }

    @Override
    public Set<E> intersection(Set<? extends E> other) {
    	ArrayListSet<E> array= new ArrayListSet<E>();
    	for(E e :other) {
    		if(this.elements.contains(e)) {
    			array.add(e);
    		}
    	}
        return array;
        
    }

    /**
     * For this method, it should be possible to compare all other possible sets
     * for equality with this set. For example, if an ArrayListSet<Fruit> and a
     * LinkedListSet<Fruit> both contain the same elements they are equal.
     * Similarly, if a Set<Apple> contains the same elements as a Set<Fruit>
     * they are also equal.
     */
    @Override
    public boolean equals(Object other) {
        if(other == null) {
        	return false;
        }
        if(other == this) {
        	return true;
        }
        if(other.getClass()!=this.getClass()) {
        	return false;
        }
        ArrayListSet<E> array = (ArrayListSet<E>) other;
        
        if(array.subsetOf(this)&&this.subsetOf(array)) {
        	return true;
        }
        return false;
    }

}
