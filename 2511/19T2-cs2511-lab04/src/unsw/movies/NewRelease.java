package unsw.movies;

public class NewRelease implements Price {

    @Override
    public double getCharge(int daysRented) {
        return daysRented * 3;
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
		return new Childrens(); 
		//throw new UnsupportedOperationException("Cant make a regular become a chidren");
	}

	@Override
	public Price makeNewRelease() {
		// TODO Auto-generated method stub
		return this;
	}

}
