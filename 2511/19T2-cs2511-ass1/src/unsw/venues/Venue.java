package unsw.venues;

import java.util.ArrayList;

/**
 * Venue class contains venue name and list of room
 * @author zixin xiao
 *
 */
public class Venue {
	private String venue;
	private ArrayList<Room> room = new ArrayList<Room>();
	
	public Venue(String venue, String a,String b) {
		setVenue(venue);
		Room r = new Room(a,b);
		room.add(r);
	}
	/**
	 * get the venue name
	 * @return the venue name
	 */
	public String getVenue() {
		return venue;
	}
	/**
	 * set the venue name
	 * @param venue name of venue
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}
	/**
	 * get the arraylist of roomlist
	 * @return arraylist of roomlist
	 */
	public ArrayList<Room> getRoom() {
		return room;
	}
	/**
	 * set the room with arraylist
	 * @param room the arraylist of roomlist
	 */
	public void setRoom(ArrayList<Room> room) {
		this.room = room;
	}
	/**
	 * add the room into roomlist
	 * @param r the object room
	 */
	public void addRoom(Room r) {
		room.add(r);
	}
	/**
	 * remove the room from roomlist
	 * @param r the object room
	 */
	public void removeRoom(Room r) {
		room.remove(r);
		
	}
	/**
	 * get the Quantity of available Room from roomlist at one period
	 * @param Size the RoomSize
	 * @param t period of booking
	 * @return
	 */
	public int RoomSizeQuentity(String Size,Booking t) {
		int index = 0;
		
		for(Room r : this.room) {
			if(r.getSize().equals(Size)&&r.isAliable(t)) {
				index++;
			}
			
		}
		return index;
	}
	/**
	 * override the toString function
	 */
	@Override
	public String toString() {
		return "Venue:"+this.venue+room.toString();
		
	}
	/**
	 * override the equals function
	 */
	@Override
	public boolean equals(Object o){
		if(this!=o) {
			return false;
		}
		if(!(o instanceof Venue)) {
			return false;
		}
		Venue v = (Venue) o;
		if(this.venue.equals(v.getVenue())) {
			ArrayList<Room> ComRoom = v.getRoom();
			if(this.room.size()!=ComRoom.size()) {
				return false;
			}
			for(int i = 0;i<ComRoom.size();i++) {
				if(!(ComRoom.get(i).equals(this.room.get(i)))) {
					return false;
				}
			}
			return true;
		}
		return false;
		
	}
	
	
}
