/****************************
CLASS: Node.java
CSC212 Data structures - Project phase II
Fall 2023
EDIT DATE:12-02-2023
TEAM: ByteCodeMasters
AUTHORS:
Ahmed alheraisi 441105489
Feras Yahya Alassiri 443101049
Alwaleed alfuhaid 443102064 
***********************************/
public class Node {

	public Event data;
	public Node Next;

	// Constructor to initialize node details
	public Node(Event data) {
		this.data = data;
		Next = null;
	}

}