package unsw.movies;

public class Childrens implements Price {

    @Override
    public double getCharge(int daysRented) {
        double charge = 1.5;
        if (daysRented > 3)
            charge += (daysRented - 3) * 1.5;
        return charge;
    }

	@Override
	public Price makeClassic() {
		return new Classic();
		//throw new UnsupportedOperationException("Cant make a regular become a classic");
	}

	@Override
	public Price makeRegular() {
		return new Regular();
		//throw new UnsupportedOperationException("Cant make a regular become a regular");
	}

	@Override
	public Price makeChildrens() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Price makeNewRelease() {
		return new NewRelease();
		//throw new UnsupportedOperationException("Cant make a regular become a new release");
	}

}
