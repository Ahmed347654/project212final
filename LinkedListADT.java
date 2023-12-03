/****************************
CLASS: LinkedListADT.java
CSC212 Data structures - Project phase II
Fall 2023
EDIT DATE:12-02-2023
TEAM: ByteCodeMasters
AUTHORS:
Ahmed alheraisi 441105489
Feras Yahya Alassiri 443101049
Alwaleed alfuhaid 443102064 
***********************************/
public class LinkedListADT {

	private Node Head;
	private Node Current;

	// Constructor to initialize the linked list
	public LinkedListADT() {
		// Initialize the Head and Current pointers to null as the list is initially
		// empty
		Head = Current = null;
	}

	public void adding(Event data) {
		// Create a new Node with the provided data
		Node NewNode = new Node(data);

		// Check if the linked list is empty
		if (Head == null) {
			// If it's empty, set the new node as both the Head and Current node
			Head = NewNode;
			Current = Head;
		}

		else {
			// If the list is not empty, insert the new node after the Current node
			// Adjust the Next pointers to insert the new node into the list
			NewNode.Next = Current.Next;
			Current.Next = NewNode;
			// Move the Current node to the new node
			Current = NewNode;
		}
	}

	
	public boolean RemoveEvents(String name) {
	    Node beforeCurrent = null;
	    Node current = Head;
	    boolean removed = false;

	    while (current != null) {
	        Event currentEvent = (Event) current.data;

	        if (currentEvent != null && currentEvent.getContactNames().contains(name)) {
	            // Remove the contact from the event
	            currentEvent.getContactNames().remove(name);
	            removed = true;

	            // Check if the event has no more contacts
	            if (currentEvent.getContactNames().isEmpty()) {
	                // Remove the event itself
	                if (beforeCurrent == null) {
	                    Head = current.Next;
	                } else {
	                    beforeCurrent.Next = current.Next;
	                }
	            } else {
	                beforeCurrent = current;
	            }
	        } else {
	            beforeCurrent = current;
	        }

	        current = current.Next;
	    }

	    return removed;
	}

	public void sortEventsByTitle() {
        // Check if the linked list is empty or has only one element; no sorting needed
        if (Head == null || Head.Next == null) {
            return;
        }

        // Start at the head of the list
        Node current = Head;

        // Initialize the sorted part of the list
        Node sorted = null;

        while (current != null) {
            Node next = current.Next;
            if (current.data instanceof Event) {
                // Cast the data to Event
                Event currentEvent = (Event) current.data;

                // Insert currentEvent into the sorted part of the list
                if (sorted == null || currentEvent.getEventTitle().compareTo(((Event) sorted.data).getEventTitle()) < 0) {
                    // If the sorted list is empty or currentEvent should be at the beginning
                    current.Next = sorted;
                    sorted = current;
                } else {
                    // Find the position to insert currentEvent
                    Node search = sorted;
                    while (search.Next != null && currentEvent.getEventTitle().compareTo(((Event) search.Next.data).getEventTitle()) >= 0) {
                        search = search.Next;
                    }
                    current.Next = search.Next;
                    search.Next = current;
                }
            }

            // Move to the next node
            current = next;
        }

        // Update the Head with the sorted part
        Head = sorted;
    }

	public void printAllEventNames() {
		// Check if the linked list is empty
		if (Head == null) {
            System.out.println();
			System.out.println("There's no events found.");
            System.out.println();
			// Exit the method if the list is empty
			return;
		}
		// Save the current position in the linked list
		Node SaveCurrent = Current;
		// Start at the head of the list
		Current = Head;
        System.out.println();

		System.out.println("Events Alphabetically:");

		// Iterate through the linked list and print event titles
		while (Current != null) {
			// Extract event details from the current node
			Event event = (Event) Current.data;
			System.out.println(event.getEventTitle());
			// Move to the next node in the list
			Current = Current.Next;
		}
	    System.out.println();
		// Restore the original current position
		Current = SaveCurrent;
	}
	

	public void SearchByPrintAll(String type, String data) {
	    boolean found = false;

	    Node searchCurrent = Head;
	    while (searchCurrent != null) {
	        Event currentEvent = (Event) searchCurrent.data;

	        // Check if searching by event title
	        if (type.equals("EventTitle") && currentEvent.getEventTitle().equalsIgnoreCase(data)) {
	            System.out.println("Event found!");
	            currentEvent.displayEvent();
	            found = true;
	            break; // Once an event is found, exit the loop
	        }
	        // Check if searching by contact name
	        else if (type.equals("EventName")) {
	            // Here, check if the current event contains the contact name
	            if (currentEvent.getContactNames().contains(data)) {
	                System.out.println("Event found!");
	                currentEvent.displayEvent();
	                found = true;
	                // Do not break here as there could be multiple events with the same contact
	            }
	        }

	        searchCurrent = searchCurrent.Next;
	    }

	    if (!found) {
            System.out.println();
	        System.out.println("Sorry, no matching event found.");
            System.out.println();
	    }
	}



	public boolean SearchByDateAndTime(String Date, String Time) {
		// Check if the linked list is empty
		if (Head == null)
			return false;

		// Start from the beginning of the linked list
		Node SearchCurrent = Head;

		// Iterate through the linked list
		while (SearchCurrent != null) {
			// Check if the event's date and time match the search criteria
			if (((Event) SearchCurrent.data).getDate().equalsIgnoreCase(Date)
					&& ((Event) SearchCurrent.data).getTime().equalsIgnoreCase(Time)) {
				// Event found with the specified date and time
				return true;
			}
			// Move to the next
			SearchCurrent = SearchCurrent.Next;
		}
		// Event not found with the specified date and time
		return false;
	}
	public boolean SearchByName(String name) {
	    if (Head == null) return false;

	    Node SearchCurrent = Head;
	    while (SearchCurrent != null) {
	        Event currentEvent = (Event) SearchCurrent.data;
	        if (currentEvent.getName() != null && currentEvent.getName().equalsIgnoreCase(name)) {
	            return true;
	        }
	        SearchCurrent = SearchCurrent.Next;
	    }
	    return false;
	}

	 public boolean hasExistingAppointment(String contactName) {
	        Node current1 = this.Head; // Assuming 'Head' is the start of your linked list

	        while (current1 != null) {
	            Event event = current1.data;
	            // Check if the event is an appointment and if the name matches the contactName
	            if (current1 != null && event.isAppointment() && event.getName().equalsIgnoreCase(contactName)) {
	                return true; // The contact already has an appointment
	            }
	            current1 = current1.Next; // Proceed to the next node
	        }
	        return false; // No appointment found for this contact
	    }
	 
	 public boolean isTimeSlotAvailable(String date, String time) {
		    Node current = Head;
		    while (current != null) {
		        Event event = (Event) current.data;
		        // Check if there is a date and time match, indicating a conflict
		        if (event.getDate().equals(date) && event.getTime().equals(time)) {
		            return false; // Time slot is not available
		        }
		        current = current.Next;
		    }
		    return true; // Time slot is available
		}

	 
	 
	 
	 

}