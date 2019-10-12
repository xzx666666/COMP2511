
package unsw.venues;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author zixin xiao
 *
 */
public class VenueHireSystem {

    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */
	private HashMap<String,Venue> venueList;
	private Stack<Booking> successList;
	
	
    public VenueHireSystem() {
        venueList = new HashMap<String,Venue>();
        
        successList = new Stack<Booking>();
    }
    /**
     * change the json into string format and separate it into different case 
     * @param json  json format with input
     */
    private void processCommand(JSONObject json) {
    	
    	
    	String id;
    	LocalDate start;
    	LocalDate end;
    	int small;
    	int medium;
    	int large;
    	JSONObject result;
    	
    	
        switch (json.getString("command")) {

        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            addRoom(venue, room, size);
            break;

        case "request":
            id = json.getString("id");
            start = LocalDate.parse(json.getString("start"));
            end = LocalDate.parse(json.getString("end"));
            small = json.getInt("small");
            medium = json.getInt("medium");
            large = json.getInt("large");

            result = request(id, start, end, small, medium, large);

            System.out.println(result);
            break;

        case "change":
        	id = json.getString("id");
            start = LocalDate.parse(json.getString("start"));
            end = LocalDate.parse(json.getString("end"));
            small = json.getInt("small");
            medium = json.getInt("medium");
            large = json.getInt("large");

            result = change(id, start, end, small, medium, large);

            System.out.println(result);
            break;
        	
        case "cancel":
        	id = json.getString("id");
            

            result = cancel(id,false);

            System.out.println(result);
            break;
            
        case "list":
        	 String venue_name = json.getString("venue");
        	 JSONArray result1;
             result1 = list(venue_name);

            System.out.println(result1.toString(2));
            break;
        }
    }
    
    
	/**
	 * function by list room by given venue's name
	 * @param venue_name the venue's full name
	 * @return Information of venue by json format
	 */
    private JSONArray list(String venue_name) {
    	JSONArray result = new JSONArray();
    	
    	for(Room r1:this.venueList.get(venue_name).getRoom()) {
    		
    		Collections.sort(r1.getBooking());
    		
    		JSONObject res = new JSONObject();
    		res.put("room", r1.getName());
    		
    		JSONArray reservations = new JSONArray();
    		for(int j=0;j<r1.getBooking().size();j++) {
	    		for(int i =this.successList.size()-1;i>=0;i--) {
	    			JSONObject subbooking = new JSONObject();
	    			subbooking.put("id", this.getSuccessList().get(i).getId());
	    			
	    			if(this.getSuccessList().get(i).equals(r1.getBooking().get(j))
	    					&&this.getSuccessList().get(i).getRoomlist().contains(r1)) {
	    				
	    				subbooking.put("start", r1.getBooking().get(j).getStart());
	    				subbooking.put("end", r1.getBooking().get(j).getEnd());
	    				reservations.put(subbooking);
	    			}
    			}
    			
    		}
    		
    		res.put("reservations", reservations);
			result.put(res);
    		
	    }
    	
    	
    	//sample output
    	/*JSONObject resn = new JSONObject();
    	resn.put("room", "hhh");
        JSONObject res = new JSONObject();
        res.put("id", "CSE Autumn Ball");
        res.put("start", "2019-03-25");
        res.put("end", "2019-03-26");
        
        resn.put("reservations",res);
        result.put(resn);*/
        
   	 	return result;
	}
    /**
     * get the Booking from the Bookinglist search by booking's id
     * @param id Booking's id
     * @return One succeed Booking otherwise null
     */
    private Booking getBookingById(String id) {
		Booking old = null;
		if(!this.successList.empty()) {
			
			for( Booking b :this.getSuccessList()) {
				
				if(b.getId().equals(id)) {
					return b;
				}
			}
		 
		}
		return old;
    }
    /**
     * function for cancel a request if the request available succeed otherwise reject
     * @param id booking id
     * @param status it is from change or otherwise. change is true, otherwise is false
     * @return Information about cancel in json format
     */
	private JSONObject cancel(String id,boolean status) {
    	 JSONObject result = new JSONObject();
    	 
    	 Booking old = getBookingById(id);
    	 if(!status) {
	    	 if(old!=null) {
	    		 
	    		 result.put("status","success");
	    	
				 for(Room r :this.venueList.get(old.getName()).getRoom()) {
					if(old.getRoomlist().contains(r)) {
						r.removeBooking(old);
					}
					
				 }
				 
				 this.getSuccessList().remove(old);
	    	 }
	    	 else {
	    		 result.put("status", "rejected");
	    	 }
    	 }
    	 
    	 else {
    		 if(old!=null) {
	    		 
				 for(Room r :this.venueList.get(old.getName()).getRoom()) {
					if(old.getRoomlist().contains(r)) {
						r.removeBooking(old);
					}
					
				 }
				 
				 this.getSuccessList().remove(old);
				
	    	 }
	    	 
    	 }
    	 return result;
	}
	
	/**
	 * change the request, firstly cancel request and booking a new request
	 * @param id the booking id
	 * @param start the start time
	 * @param end the end time
	 * @param small the number of booking in small size
	 * @param medium the number of booking in medium size
	 * @param large the number of booking in large size
	 * @return Information of change in json format
	 */
	private JSONObject change(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
		Booking old = this.getBookingById(id);
		
		JSONObject result = new JSONObject();
		cancel(id,true);
		
		result = request(id, start, end, small, medium, large);
		if(old!=null) {
			
			switch(result.getString("status")) {
				case "rejected":
					
					//if rejected new request. book new again
					for(Room r :this.venueList.get(old.getName()).getRoom()) {
						if(old.getRoomlist().contains(r)) {
							r.addBooking(old);
						}
						
					 }
					 
					this.getSuccessList().add(old);
					break;
				case "success" :
					
					//do not do anything
					break;
			}
		}
		
   	 	return result;
	}
	/**
	 * add a room by different venue 
	 * @param venue name of venue
	 * @param room name of room
	 * @param size the room's size
	 */
	private void addRoom(String venue, String room, String size) {
        if(this.venueList.containsKey(venue)) {
        	Room r = new Room(room,size);
        	this.venueList.get(venue).addRoom(r);;
        }
        else {
        	Venue v = new Venue(venue,room,size);
        	this.venueList.put(venue, v);
        }
    }
	/**
	 * booking room in one period 
	 * check the room is available in venue in this period
	 * if have succeed booking otherwise reject booking
	 * @param id name of booking
	 * @param start the booking start time
	 * @param end the booking end time
	 * @param small the quality of booking in small size
	 * @param medium the quality of booking in medium size
	 * @param large the quality of booking in large size
	 * @return Information of request in json format 
	 */
    public JSONObject request(String id, LocalDate start, LocalDate end,
            int small, int medium, int large) {
        JSONObject result = new JSONObject();
        
        Booking request = new Booking(id, start,end, small, medium, large);
        //if succeed set it into successlist 
        //if not succeed result as set reject 
        ArrayList<Room> reservedRoom = new ArrayList<Room>();
        //check the room is alivable
       
        for (Entry<String, Venue> entry : this.venueList.entrySet()) {
        	
        	int small1 = entry.getValue().RoomSizeQuentity("small",request);
    		int medium1 = entry.getValue().RoomSizeQuentity("medium",request);
    		int large1 = entry.getValue().RoomSizeQuentity("large",request);
    		 
        	if(small1>=request.getSmall()&&medium1>=request.getMedium()&&large1>=request.getLarge()) {
        		
        		ArrayList<Room> roomlist = entry.getValue().getRoom();
        		for(Room r:roomlist) {
        			
        			if(small==0&&medium==0&&large==0) {
        				break;
        			}
        			if(r.getSize().equals("small")&&small>0&&r.isAliable(request)) {
        				small--;
        				reservedRoom.add(r);
        			}
        			else if(r.getSize().equals("medium")&&medium>0&&r.isAliable(request)) {
        				medium--;
        				reservedRoom.add(r);
        			}
        			else if(r.getSize().equals("large")&&large>0&&r.isAliable(request)) {
        				large--;
        				reservedRoom.add(r);
        			}
        			
        			
        		}
        			
        		
        	}
        	if(!reservedRoom.isEmpty()) {
    			
    			request.setRoomlist(reservedRoom);
    			request.setName(entry.getKey());
    			this.successList.add(request);
    			
    			for(Room r :this.venueList.get(request.getName()).getRoom()) {
    				if(reservedRoom.contains(r)) {
    					r.addBooking(request);
    				}
    			}
    			
    			break;
    		}
		    
		}
        
        if(!reservedRoom.isEmpty()) {
        	
        	Booking b = this.successList.peek();
        	
        	result.put("venue", b.getName());
        	JSONArray rooms = new JSONArray();
        	for(Room r:b.getRoomlist()) {
        		rooms.put(r.getName());
        	}
        	result.put("rooms", rooms);
        	result.put("status","success");
		}
        //sample output
        /*result.put("status", "success");
        result.put("venue", "Zoo");

        JSONArray rooms = new JSONArray();
        rooms.put("Penguin");
        rooms.put("Hippo");

        result.put("rooms", rooms);*/
        else {
        	result.put("status", "rejected");
        }
       
        return result;
    }
    
    
/**
 * main function
 * @param args
 */
    public static void main(String[] args) {
    	VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
                
            }
        }
        sc.close();


    }
/**
 * get the list of venue from hashmap and key with venue_name value with venue
 * @return venuelist the venuelist
 */
public HashMap<String,Venue> getVenueList() {
	return venueList;
}
/**
 * set the list of venue to hashmap and set key with venue_name value with venue
 * @param venueList the venuelist
 */
public void setVenueList(HashMap<String,Venue> venueList) {
	this.venueList = venueList;
}
/**
 * get the success booking list 
 * @return stack of success booking list
 */
public Stack<Booking> getSuccessList() {
	return successList;
}
/**
 * set the success booking list 
 * @param successList
 */
public void setSuccessList(Stack<Booking> successList) {
	this.successList = successList;
}


}
