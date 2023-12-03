/****************************
CLASS: Contact.java
CSC212 Data structures - Project phase II
Fall 2023
EDIT DATE:12-02-2023
TEAM: ByteCodeMasters
AUTHORS:
Ahmed alheraisi 441105489
Feras Yahya Alassiri 443101049
Alwaleed alfuhaid 443102064 
***********************************/
public class Contact  /*implements Comparable<Contact>*/  {

	private String Name;
	private String PhoneNumber;
	private String EmailAddress;
	private String Address;
	private String Birthday;
	private String Notes;

    // Constructor to initialize contact details
	public Contact(String name, String phoneNumber, String emailAddress, String address, String birthday,
			String notes) {
		Name = name;
		PhoneNumber = phoneNumber;
		EmailAddress = emailAddress;
		this.Address = address;
		this.Birthday = birthday;
		this.Notes = notes;
	}

	// Getter and Setter
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		this.Address = address;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		this.Birthday = birthday;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		this.Notes = notes;
	}

	// Display the contact's information
	public void display() {
		System.out.println();
		System.out.println("Name is " + Name);
		System.out.println("Number is " + PhoneNumber);
		System.out.println("Email is " + EmailAddress);
		System.out.println("Address is " + Address);
		System.out.println("Birthday is " + Birthday);
		System.out.println("Notes about the Contact " + Notes);
		System.out.println();
	}

//	@Override
//	public int compareTo(Contact o) {
//		// TODO Auto-generated method stub
//		return this.Name.compareTo(o.Name);
//	}

}
