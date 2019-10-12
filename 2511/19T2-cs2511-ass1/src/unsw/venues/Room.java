package unsw.venues;

import java.util.ArrayList;

/**
 * Room class contains Room's name and size list of booking that the room reserved
 * @author zixin xiao
 *
 */
public class Room {
	
	private String name;
	private String Size;
	private ArrayList<Booking> bookings;
	
	public Room(String name, String size) {
		setName(name);
		setSize(size);
		bookings = new ArrayList<Booking>();
	}
	/**
	 * override toString function
	 */
	@Override
	public String toString() {
		return " Name:"+this.name+" Size:"+this.Size+" Booking:"+this.bookings.toString();
		
	}
	/**
	 * override equals function
	 */
	@Override
	public boolean equals(Object o){
		if(o != this) {
			return false;
			
		}
		if(!(o instanceof Room)) {
			return false;
		}
		Room r = (Room) o;
		if(r.getName().equals(this.getName())&&r.getSize().equals(this.getSize())) {
			return true;
		}
		return false;
		
	}
	/**
	 * get the Room's name
	 * @return room name
	 */
	public String getName() {
		return name;
	}
	/**
	 * set the Room's name
	 * @param name room nmae
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 *get the size of room
	 * @return size of room
	 */
	public String getSize() {
		return Size;
	}
	/**
	 * set the size of room
	 * @param size size of room
	 */
	public void setSize(String size) {
		Size = size;
	}
	/**
	 * get the booking when the room has been successful reserved
	 * @return the period of booking when reserved  
	 */
	public ArrayList<Booking> getBooking() {
		return bookings;
	}
	/**
	 * set a period of booking
	 * @param booking a period of booking
	 */
	public void setBooking(ArrayList<Booking> booking) {
		this.bookings = booking;
	}
	/**
	 * add the booking into bookinglist
	 * @param t a period of booking
	 */
	public void addBooking(Booking t) {
		this.bookings.add(t);
	}
	/**
	 * remove the booking into bookinglist
	 * @param t a period of booking
	 */
	public void removeBooking(Booking t) {
		this.bookings.remove(t);
	}
	/**
	 * check the booking isAliable for this room
	 * @param t booking which should compare with this class
	 * @return
	 */
	public boolean isAliable(Booking t) {
		for(Booking t1:this.getBooking()) {
			if(t1.timeClash(t)) {
				return false;
			}
		}
		return true;
	}

}
