package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Booking class is contains booking information and list of booked room
 * @author zixin xiao
 *
 */

public class Booking implements Comparable<Booking>{
	
	private String name;
	private ArrayList<Room> roomlist ; 
	private String id;
	private LocalDate start;
	private LocalDate end;
	private int small;
	private int medium;
	private int large;
	
	public Booking(String id, LocalDate start, LocalDate end,int small, int medium, int large) {
		this.setId(id);
		this.start = start;
		this.setEnd(end);
		this.setSmall(small);
		this.setMedium(medium); 
		this.setLarge(large);
	}
	
	
	
	@Override
	public String toString() {
		return "Booking [name=" + name + ", roomlist=" + roomlist + ", id=" + id + ", start=" + start + ", end=" + end
				+ ", small=" + small + ", medium=" + medium + ", large=" + large + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	/**
	 * Get the list of Room from booked roomlist 
	 * Initial booking object this roomlist is empty 
	 * If booking succeed this roomlist will set into room 
	 * @return booked roomlist 
	 */
	public ArrayList<Room> getRoomlist() {
		return roomlist;
	}
	
	/**
	 * Set the list of Room with succeed booking
	 * @param roomlist booked arraylist of room
	 */
	public void setRoomlist(ArrayList<Room> roomlist) {
		this.roomlist = roomlist;
	}
	/**
	 * Get the Venue name
	 * @return Venue name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the booking Venue name
	 * @param name Venue name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Get the booking id 
	 * @return booking id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Set the booking id
	 * @param id booking id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Get quantity of booking small size 
	 * @return quantity of small size
	 */
	public int getSmall() {
		return small;
	}
	/**
	 * Set quantity of booking small size 
	 * @param small quantity of small size
	 */
	public void setSmall(int small) {
		this.small = small;
	}
	/**
	 * Get quantity of booking medium size 
	 * @return quantity of medium size
	 */
	public int getMedium() {
		return medium;
	}
	/**
	 * Set quantity of booking medium size 
	 * @param medium quantity of medium size
	 */
	public void setMedium(int medium) {
		this.medium = medium;
	}
	/**
	 * Get quantity of booking large size 
	 * @return quantity of large size
	 */
	public int getLarge() {
		return large;
	}
	/**
	 * Set quantity of booking large size 
	 * @param large quantity of large size
	 */
	public void setLarge(int large) {
		this.large = large;
	}
	/**
	 * Get a end of time
	 * @return end of time
	 */
	public LocalDate getEnd() {
		return end;
	}
	/**
	 * Set a end of time
	 * @param end end of time
	 */
	public void setEnd(LocalDate end) {
		this.end = end;
	}


	/**
	 * Get a start of time
	 * @return start of time
	 */
	public LocalDate getStart() {
		return start;
	}

	/**
	 * Set a start of time
	 * @param start end of time
	 */
	public void setStart(LocalDate start) {
		this.start = start;
	}
	/**
	 * Check this period of time t is or not clash with current time 
	 * if clash return true
	 * not clash return false
	 * @param t Period of time should be compare to
	 * @return the boolean of clash or not
	 */
	public boolean timeClash(Booking t) {
		
		if(t.getStart().compareTo(this.getStart())<0 && t.getEnd().compareTo(this.getStart())<0) {
			 
			return false;
		}
		else if(t.getEnd().compareTo(this.getEnd())>0 && t.getStart().compareTo(this.getEnd())>0) {
			
			return false;
		}
		return true;
	}
	
	@Override
	public int compareTo(Booking o) {
		
		return this.getStart().compareTo(o.getStart());
	}

}
