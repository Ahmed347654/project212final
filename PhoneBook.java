/****************************
CLASS: PhoneBook.java
CSC212 Data structures - Project phase II
Fall 2023
EDIT DATE:12-02-2023
TEAM: ByteCodeMasters
AUTHORS:
Ahmed alheraisi 441105489
Feras Yahya Alassiri 443101049
Alwaleed alfuhaid 443102064 
***********************************/
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.text.ParseException;
public class PhoneBook {

	
	Scanner input = new Scanner(System.in);
	ContactBST ContactTree = new ContactBST();
	LinkedListADT EventList = new LinkedListADT();

	public void AddContact() {
        System.out.println();
	    System.out.print("Enter the contact's name: ");
	    String name = input.nextLine();

	    if (name.isBlank()) {
	        System.out.println("The name is required.");
	        System.out.println();
	        return;
	    }

	    if (ContactTree.searchByNameOrNumber("Name", name) != null) {
	        System.out.println("A contact with the same name already exists.");
            System.out.println();
	        return;
	    }

	    System.out.print("Enter the contact's phone number: ");
	    String number = input.nextLine();

	    if (number.isBlank()) {
	        System.out.println("The phone number is required.");
	        System.out.println();
	        return;
	    }

	   if (!number.matches("\\d{10}")) {
	        System.out.println("Invalid phone number format. It should contain 10 digits.");
            System.out.println();
	        return;
	    }

	    if (ContactTree.searchByNameOrNumber("Number", number) != null) {
	        System.out.println("A contact with the same phone number already exists.");
            System.out.println();
	        return;
	    }

	    System.out.print("Enter the contact's email address: ");
	    String email = input.nextLine();

	    if (email.isBlank() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	        System.out.println("Invalid email format. Please enter a valid email.");
            System.out.println();
	        return;
	    }

	    System.out.print("Enter the contact's address: ");
	    String address = input.nextLine();
	    System.out.print("Enter the contact's birthday (MM/DD/YYYY): ");
	    String birthday =  validateDateFormat(input);
	    if (!isValidDate(birthday)) {
	        System.out.println("Invalid date format or an invalid date. Please try again.");
	        System.out.println();
	        return;
	    }
	    System.out.print("Enter any notes for the contact: ");
	    String notes = input.nextLine();

	    Contact NewContact = new Contact(name, number, email, address, birthday, notes);
	    ContactTree.adding(name,NewContact);
	    System.out.println();

	    System.out.println("The contact has been added successfully!");
	    System.out.println();
	    
	}


	public void SearchContact() {
        System.out.println();
		// Display search criteria options to the user.
		System.out.println("Enter search criteria:\r\n" + "1. Name\r\n" + "2. Phone Number\r\n" + "3. Email Address\r\n"
				+ "4. Address\r\n" + "5. Birthday\r\n" + "");
		// Asking the user to enter their choice.
		int choice;
		 try {
		        System.out.print("Enter your choice:");
		        choice = input.nextInt();
		        input.nextLine(); // Consume the newline character
		        // ... rest of the code for handling choices ...
		    } catch (InputMismatchException e) {
		        System.out.println("Invalid input, please only enter numbers.");
                System.out.println();
		        input.nextLine(); // Clear the scanner's buffer
		        return; // Return to the main menu
		    }
		System.out.println();
		// Check the user's choice to determine the search criteria.
		if (choice == 1) {
			// If the user chose to search by name, asking for the name.
			System.out.print("Enter the contact's name: ");
			String name = input.nextLine();
			// Check if the input is empty
			if (name.isBlank()) {
				System.out.println("the name is required");
				System.out.println();
				return;
			}
			System.out.println();
			// Check if a contact with the given name exists.
			if (ContactTree.searchByNameOrNumber("Name", name) == null) {
				System.out.println("Sorry Contact is not found!");
				System.out.println("Because Your Contact is not Exists");
				
			} else {
				// If the contact is found, display the contact's details.
				System.out.println("Contact found!");
				ContactTree.searchByNameOrNumber("Name", name).display();
			}
		} else if (choice == 2) {
			// If the user chose to search by phone number, asking for the number.
			System.out.print("Enter the Contact's Number: ");
			String number = input.nextLine();
			// Check if the input is empty
			if (number.isBlank()) {
				System.out.println("the phone number is required");
				System.out.println();
				return;
			}
			System.out.println();
			// Check if a contact with the given number exists.
			if (ContactTree.searchByNameOrNumber("Number", number) == null) {
				System.out.println("Sorry Contact is not found!");
				System.out.println("Because Your Contact is not Exists");
			} else {
				// If the contact is found, display the contact's details.
				System.out.println("Contact found!");
				ContactTree.searchByNameOrNumber("Number", number).display();
			}

		}

		else if (choice == 3) {
			// If the user chose to search by email address, asking for the email address.
			System.out.print("Enter the Contact's Email Address:");
			String Email = input.nextLine();
			// Check if the input is empty
			if (Email.isBlank()) {
				System.out.println("the email is required");
				System.out.println();
				return;
			}
			System.out.println();
			// Search and display contacts matching the email address.
			ContactTree.SearchByPrintAll("Email", Email);
		} else if (choice == 4) {
			// If the user chose to search by address, asking for the address.
			System.out.print("Enter the Contact's Address:");
			String Address = input.nextLine();
			// Check if the input is empty
			if (Address.isBlank()) {
				System.out.println("the address is required");
				System.out.println();
				return;
			}
			System.out.println();
			// Search and display contacts matching the address.
			ContactTree.SearchByPrintAll("Address", Address);
		}

		else if (choice == 5) {
			// If the user chose to search by birthday, asking for the birthday.
			System.out.print("Enter the Contact's Birthday:");
			String Birthday = input.nextLine();
			// Check if the input is empty
			if (Birthday.isBlank()) {
				System.out.println("the birthday is required");
				System.out.println();
				return;
			}
			System.out.println();
			// Search and display contacts matching the birthday.
			ContactTree.SearchByPrintAll("Birthday", Birthday);

		} else {
			// Display an error message if an invalid choice is entered.
			System.out.println("please enter number between 1 and 5");
		}

		System.out.println();

	}

	public void DeleteContact() {
        System.out.println();
		// Asking the user to enter the name of the contact to delete.
		System.out.print("Enter the Contact's name: ");
		String name = input.nextLine();
		// Check if the input is empty
		if (name.isBlank()) {
			System.out.println("the name is required");
			System.out.println();
			return;
		}
		System.out.println();
		// Check if the contact with the provided name exists.
		if (ContactTree.RemoveContact(name)) {
			// If the contact exists, remove all his events from eventList.
			EventList.RemoveEvents(name);
			System.out.println("The Contact has been deleted Successfully!");
			System.out.println();
		} else {
			System.out.println("Sorry Contact can not be Deleted!");
			System.out.println("Because Your Contact is not Exist");
			System.out.println();
		}

	}
	public void ScheduleEvent() {
        int eventType ;
        boolean isAppointment;
        System.out.println();
        System.out.println("Enter type:\n"
        		+ "1. event\n"
        		+ "2. appointment");
        int choice;
		 try {
             System.out.println();

		        System.out.print("Enter your choice:");
		        choice = input.nextInt();
		        //input.nextLine(); // Consume the newline character
		        // ... rest of the code for handling choices ...
		    } catch (InputMismatchException e) {
				System.out.println();
		        System.out.println("Invalid input, please only enter numbers.");
				System.out.println();
		        input.nextLine(); // Clear the scanner's buffer
		        return; // Return to the main menu
		    }
		System.out.println();
        if(choice == 1) {
         isAppointment = false;
        }else {
           isAppointment = true;

        }
    	input.nextLine();


        List<String> contactNames = new ArrayList<>();
        if (isAppointment) {
            System.out.print("Enter appointment title: ");
            String title = input.nextLine();
            System.out.print("Enter the contact's name for the appointment: ");
            String contactName = input.nextLine();
            if(!ContactTree.isNameExist(contactName)) {
                System.out.println();
                System.out.println("Contact " + contactName + " does not exist.");
                System.out.println();
            	return;
            }
            // Check if this contact already has an appointment at the given time
            if (EventList.hasExistingAppointment(contactName)) {
                System.out.println();
                System.out.println("This contact already has an appointment and cannot be scheduled for another.");
                System.out.println();

                return;
            }
            System.out.print("Enter the date (MM/DD/YYYY): ");
            String date = validateDateFormat(input);

            System.out.print("Enter the time (HH:MM): ");
            String time = validateTimeFormat(input);
         // Inside ScheduleEvent or ScheduleEvents before adding a new event/appointment
            if (!EventList.isTimeSlotAvailable(date, time)) {
                System.out.println();
                System.out.println("An event or appointment already exists at this date and time.");
                System.out.println();
                return;
            }


            System.out.print("Enter the appointment location: ");
            String location = input.nextLine();

            Event newEvent = new Event(title,time,location,date,true, contactName);
            System.out.println();
            System.out.println("The Appointment has been Scheduled Successfully");
            System.out.println();
            EventList.adding(newEvent);
        } else {
        	ScheduleEvents();
        }
        EventList.sortEventsByTitle();
    }

	public void ScheduleEvents() {
	    // Asking the user to enter the event title.
	    System.out.print("Enter event title: ");
	    String EventTitle = input.nextLine();
	    // Check if the input is empty
	    if (EventTitle.isBlank()) {
	        System.out.println("The event title is required.");
	        System.out.println();
	        return;
	    }

	    // Asking the user to enter contact names, separated by commas.
	    System.out.print("Enter contact names (separated by commas): ");
	    String inputNames = input.nextLine();

	    // Check if the input is empty
	    if (inputNames.isBlank()) {
	        System.out.println("Contact names are required.");
	        System.out.println();
	        return;
	    }

	    String[] names = inputNames.split(",");
	    List<String> validContacts = new ArrayList<>();
	    for (String name : names) {
	        name = name.trim(); // Trim whitespace
	        if (ContactTree.searchByNameOrNumber("Name", name) != null) {
	            validContacts.add(name);
	        } else {
	            System.out.println();
	            System.out.println("Contact '" + name + "' does not exist.");
	        }
	    }

	    if (validContacts.isEmpty()) {
	        System.out.println("No valid contacts provided. Please try again.");
	        System.out.println();
	        return;
	    }

	    // Asking the user to enter the event date.
	    System.out.print("Enter event date (MM/DD/YYYY): ");
	    String EventDate = validateDateFormat(input);

	    // Check date validity
	    if (EventDate.isBlank() || !isValidDate(EventDate)) {
            System.out.println();
	        System.out.println("Invalid date format or an invalid date. Please try again.");
	        System.out.println();
	        return;
	    }

	    // Asking the user to enter the event time.
	    System.out.print("Enter event time (HH:MM): ");
	    String EventTime = validateTimeFormat(input);

	    // Check time validity
	    if (EventTime.isBlank() || !isValidTime(EventTime)) {
            System.out.println();
	        System.out.println("Invalid time format. Please try again.");
	        System.out.println();
	        return;
	    }
	    if (!EventList.isTimeSlotAvailable(EventDate, EventTime)) {
            System.out.println();
	        System.out.println("An event or appointment already exists at this date and time.");
            System.out.println();
	        return;
	    }


	    // Asking the user to enter the event location.
	    System.out.print("Enter event location: ");
	    String EventLocation = input.nextLine();

	    // Check if the input is empty
	    if (EventLocation.isBlank()) {
	        System.out.println("The event location is required.");
	        System.out.println();
	        return;
	    }

	    // Create a new Event object with the list of valid contacts.
	    Event NewEvent = new Event(EventTitle, validContacts, EventDate, EventTime, EventLocation);
	    EventList.adding(NewEvent);
        System.out.println();
	    System.out.println("The Event has been Scheduled Successfully!");
	    System.out.println();

	    // Sort the events in the EventList by title.
	    EventList.sortEventsByTitle();
	}

	private String validateDateFormat(Scanner input) {
		String datePattern = "^\\d{2}/\\d{2}/\\d{4}$";
	    String eventDate;

	    while (true) {
	        eventDate = input.nextLine();

	        if (eventDate.matches(datePattern)) {
	            break;
	        } else {
	            System.out.println("Invalid date format. Please use MM/DD/YYYY format.");
	        }
	    }

	    return eventDate;
	}

	private String validateTimeFormat(Scanner input) {
	    String timePattern = "^\\d{2}:\\d{2}$";
	    String eventTime;

	    while (true) {
	        eventTime = input.nextLine();

	        if (eventTime.matches(timePattern)) {
	            break;
	        } else {
	            System.out.println("Invalid time format. Please use HH:MM format.");
	            System.out.println("Enter time  (HH:MM):");
	        }
	    }

	    return eventTime;
	}
	private boolean isValidDate(String date) {
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        dateFormat.setLenient(false); // Disable lenient parsing
	        dateFormat.parse(date);
	        return true;
	    } catch (ParseException e) {
	        return false;
	    }
	}

	private boolean isValidTime(String time) {
	    // Use regular expression to validate time in HH:MM format
	    Pattern timePattern = Pattern.compile("^([01]?\\d|2[0-3]):[0-5]\\d$");
	    return timePattern.matcher(time).matches();
	}

	public void PrintEventDetails() {
        System.out.println();
	    // Display the menu for selecting the search criteria.
	    System.out.println("Enter search criteria:\r\n" + "1. Event title\r\n" + "2. Contact name");
	    System.out.println();
	    // Asking the user to enter their choice.
	    int choice;
		 try {
		        System.out.print("Enter your choice:");
		        choice = input.nextInt();
		        input.nextLine(); // Consume the newline character
		        // ... rest of the code for handling choices ...
		    } catch (InputMismatchException e) {
	            System.out.println();
		        System.out.println("Invalid input, please only enter numbers.");
	            System.out.println();
		        input.nextLine(); // Clear the scanner's buffer
		        return; // Return to the main menu
		    }
	    System.out.println();

	    if (choice == 1) {
	        // If the user chose to search by event title, asking for the event title.
	        System.out.print("Enter the Event title: ");
	        String title = input.nextLine();
	        // Check if the input is empty
	        if (title.isBlank()) {
	            System.out.println("the event title is required");
	            System.out.println();
	            return;
	        }
	        // Search and print events matching the provided title.
	        EventList.SearchByPrintAll("EventTitle", title);
	    }
	    else if (choice == 2) {
	        // If the user chose to search by contact name, asking for the contact name.
	        System.out.print("Enter the Contact name: ");
	        String name = input.nextLine();
	        // Check if the input is empty
	        if (name.isBlank()) {
	            System.out.println("the name is required");
	            System.out.println();
	            return;
	        }
	        // Search and print events associated with the provided contact name.
	        EventList.SearchByPrintAll("EventName", name);
	    } else {
	        // If the user entered an invalid choice, display an error message.
	        System.out.println("please enter 1 or 2");
	        System.out.println();
	    }
	}


	public void PrintContactsByFirstName() {
        System.out.println();
		// Asking the user to enter the first name for contact search.
		System.out.print("Enter the first name: ");
		String name = input.nextLine();
		// Check if the input is empty
		if (name.isBlank()) {
			System.out.println("the name is required");
			System.out.println();
			return;
		}
		// Search and print contacts whose first names match the provided name.
		ContactTree.searchAndPrintByFirstName(name);
	}

	public void PrintEventsAlphabetically() {
		// Call the 'printAllEventNames' method of the 'EventList' to display event
		// title alphabetically.
		EventList.printAllEventNames();
	}

}