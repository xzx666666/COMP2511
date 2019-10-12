package unsw.movies;

public class Classic implements Price{

	@Override
	public double getCharge(int daysRented) {
		if(daysRented<=5) {
			return 2;
		}
		return 2+(daysRented-5);
	}

	@Override
	public Price makeClassic() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Price makeRegular() {
		return new Regular();
		//throw new UnsupportedOperationException("Cant make a regular become a regular");
	}

	@Override
	public Price makeChildrens() {
		return new Childrens(); 
		//throw new UnsupportedOperationException("Cant make a regular become a children");
	}

	@Override
	public Price makeNewRelease() {
		return new NewRelease();
		//throw new UnsupportedOperationException("Cant make a regular become a newrelease");
	}

}
