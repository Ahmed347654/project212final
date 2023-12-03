/****************************
CLASS: Main.java
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
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice = 0;
        PhoneBook contacts = new PhoneBook();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the BST PhoneBook!");

        do {
            System.out.println("Please choose an option: ");
            System.out.println("1. Add a contact");
            System.out.println("2. Search for a contact");
            System.out.println("3. Delete a contact");
            System.out.println("4. Schedule an event/appointment");
            System.out.println("5. Print event details");
            System.out.println("6. Print contacts by first name");
            System.out.println("7. Print all events alphabetically");
            System.out.println("8. Exit");
            System.out.print("Enter your choice:");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                if (choice < 1 || choice > 8) {
                    System.out.println("Invalid number. Please only enter a number from 1 to 8.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        contacts.AddContact();
                        break;
                    case 2:
                        contacts.SearchContact();
                        break;
                    case 3:
                        contacts.DeleteContact();
                        break;
                    case 4:
                        contacts.ScheduleEvent();
                        break;
                    case 5:
                        contacts.PrintEventDetails();
                        break;
                    case 6:
                        contacts.PrintContactsByFirstName();
                        break;
                    case 7:
                        contacts.PrintEventsAlphabetically();
                        break;
                    case 8:
                	    System.out.println();
                        System.out.println("Goodbye!");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please only enter numbers from 1 to 8.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        } while (choice != 8);

        scanner.close();
    }
}
