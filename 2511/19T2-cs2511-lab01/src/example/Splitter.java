package example;

public class Splitter {

    public static void main(String[] args){
        System.out.println("Enter a sentence specified by spaces only: ");
        
       String s = "are you good";
       String sh[] = s.split(" ");
       for( String c:sh)
    	   		System.out.println(c);

    }
}
