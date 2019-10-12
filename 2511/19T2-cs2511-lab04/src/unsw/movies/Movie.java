package unsw.movies;
/*
 * Moving the getCharge method to the class Movie,
 * Encapsulate Field on the type code to ensure that all uses of the type code go through getting
 * and setting methods. Because most of the code came from other classes, most methods already
 * use the getting method. However, the constructors do access the price code 
 */
public class Movie {

    private String title;
    private Price price;

    public Movie(String title) {
        this.title = title;
        this.price = new NewRelease();
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public void becomeClassic() {
    	price = price.makeClassic();
    }
    
    public void becomeRegular() {
    	price = price.makeRegular();
    }
    public void becomeNewRelease() {
    	price = price.makeNewRelease();
    }
    public void becomeChildren() {
    	price = price.makeChildrens();
    }
    
}