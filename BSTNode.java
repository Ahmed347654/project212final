/****************************
CLASS: BSTNode.java
CSC212 Data structures - Project phase II
Fall 2023
EDIT DATE:12-02-2023
TEAM: ByteCodeMasters
AUTHORS:
Ahmed alheraisi 441105489
Feras Yahya Alassiri 443101049
Alwaleed alfuhaid 443102064 
***********************************/
public class BSTNode {
	
		public String key;
		public Contact data;
		public BSTNode left, right;
		
		/** Creates a new instance of BSTNode */
		public BSTNode(String k, Contact val) {
			key = k;
			data = val;
			left = right = null;
		}
		
		public BSTNode(String k, Contact val, BSTNode l, BSTNode r) {
			key = k;
			data = val;
			left = l;
			right = r;
		}
	}

