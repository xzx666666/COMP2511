package unsw.movies;

public interface Price {
    public double getCharge(int daysRented);
    
    public Price makeClassic();
    
    public Price makeRegular();
    
    public Price makeChildrens();
    
    public Price makeNewRelease();
}
