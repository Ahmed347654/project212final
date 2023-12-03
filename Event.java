/****************************
CLASS: Event.java
CSC212 Data structures - Project phase II
Fall 2023
EDIT DATE:12-02-2023
TEAM: ByteCodeMasters
AUTHORS:
Ahmed alheraisi 441105489
Feras Yahya Alassiri 443101049
Alwaleed alfuhaid 443102064 
***********************************/

import java.util.ArrayList;
import java.util.List;

public class Event {

	private String EventTitle;
	private String Date;
	private String Time;
	private String Location;
	private String Name;
	private boolean isAppointment;
	private List<String> contactNames;
	private String arraylist [];
	
	
	public Event(String eventTitle2, List<String> validContacts, String eventDate, String eventTime, String eventLocation) {
	    this.EventTitle = eventTitle2;
	    this.Date = eventDate;
	    this.Time = eventTime;
	    this.Location = eventLocation;
	    this.contactNames = new ArrayList<>(validContacts); // Use the provided list of valid contacts
	}

	 public Event(String eventTitle, String date, String time, String location, boolean isAppointment, String Name) {
	        this.EventTitle = eventTitle;
	        this.Date = date;
	        this.Time = time;
	        this.Location = location;
	        this.isAppointment = isAppointment;
	        this.Name=Name;
	        this.contactNames = new ArrayList<>(); // Initialize even for appointments
	        if (isAppointment) {
	            this.contactNames.add(Name); // Add the contact name for appointments
	        }
	    }

	

	public boolean isAppointment() {
		return isAppointment;
	}

	public void setAppointment(boolean isAppointment) {
		this.isAppointment = isAppointment;
	}

	// Getter and Setter
	public String getEventTitle() {
		return EventTitle;
	}

	public void setEventTitle(String eventTitle) {
		EventTitle = eventTitle;
	}

	public String getDate() {
		return Date;
	}
	 public List<String> getContactNames() {
	        return contactNames;
	    }
	public void setDate(String date) {
		Date = date;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	// Display the event's information
	public void displayEvent() {
		String joinedContacts = String.join(", ", contactNames);
		System.out.println();
		System.out.println("The Event title is " + EventTitle);
		System.out.println("The Contact is " + joinedContacts);
		System.out.println("The Event date is " + Date);
		System.out.println("The Event time is " + Time);
		System.out.println("The Event location is " + Location);
		System.out.println();

	}
	public void displayAppointment() {
		System.out.println();
		System.out.println("The Appointment title is " + EventTitle);
		System.out.println("The Contact is " + Name);
		System.out.println("The Appointment date is " + Date);
		System.out.println("The Appointment time is " + Time);
		System.out.println("The Appointment location is " + Location);
		System.out.println();

	}


    // Check if the event has any contacts
   
    public boolean hasContacts() {
        return contactNames != null && !contactNames.isEmpty();
    }


}
