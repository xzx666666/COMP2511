package unsw.movies;

public class Regular implements Price {

    @Override
    public double getCharge(int daysRented) {
        double charge = 2;
        if (daysRented > 2)
            charge += (daysRented - 2) * 1.5;
        return charge;
    }

	@Override
	public Price makeNewRelease() {
		// TODO Auto-generated method stub
		return new NewRelease();
		//throw new UnsupportedOperationException("Cant make a regular become a new release");
	}

	@Override
	public Price makeRegular() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Price makeClassic() {
		return new Classic();
		//throw new UnsupportedOperationException("Cant make a regular become a classic");
	}

	@Override
	public Price makeChildrens() {
		return new Childrens();
		//throw new UnsupportedOperationException("Cant make a regular become a chidren");
	}

}
