/****************************
CLASS: ContactBST.java
CSC212 Data structures - Project phase II
Fall 2023
EDIT DATE:12-02-2023
TEAM: ByteCodeMasters
AUTHORS:
Ahmed alheraisi 441105489
Feras Yahya Alassiri 443101049
Alwaleed alfuhaid 443102064 
***********************************/
public class ContactBST {
	BSTNode root, current;
		
		public ContactBST() {
			root = current = null;
		}
		private boolean isDeleted; // Class-level variable to track deletion status

		public boolean RemoveContact(String key) {
		    if (root == null) {
		        // Tree is empty
		        return false;
		    }
		    isDeleted = false; // Reset before deletion
		    BSTNode dummyRoot = new BSTNode("", null); // Dummy root node
		    dummyRoot.left = root;
		    root = deleteRecursive(dummyRoot, key, true).left;
		    root = dummyRoot.left; // Reset the root
		    return isDeleted; // Return the deletion status
		}

		private BSTNode deleteRecursive(BSTNode parent, String key, boolean isLeftChild) {
		    BSTNode current = isLeftChild ? parent.left : parent.right;
		    if (current == null) {
		        return parent; // Base case: key not found, no changes needed
		    }

		    if (key.equals(current.key)) {
		        // Node to delete found
		        isDeleted = true;

		        if (current.left == null && current.right == null) {
		            // Case 1: No children
		            if (isLeftChild) parent.left = null;
		            else parent.right = null;
		        } else if (current.left == null) {
		            // Case 2: Only right child
		            if (isLeftChild) parent.left = current.right;
		            else parent.right = current.right;
		        } else if (current.right == null) {
		            // Case 2: Only left child
		            if (isLeftChild) parent.left = current.left;
		            else parent.right = current.left;
		        } else {
		            // Case 3: Two children
		            BSTNode smallestNode = findSmallestNode(current.right);
		            current.key = smallestNode.key;
		            current.data = smallestNode.data;
		            deleteRecursive(current, smallestNode.key, false); // Delete the smallest node
		        }
		    } else {
		        if (key.compareTo(current.key) < 0) {
		            deleteRecursive(current, key, true);
		        } else {
		            deleteRecursive(current, key, false);
		        }
		    }
		    return parent;
		}



			private BSTNode findSmallestNode(BSTNode root) {
			    return root.left == null ? root : findSmallestNode(root.left);
			}

		 public void adding(String key, Contact data) {
		        root = addRecursive(root, key, data);
		    }

		    private BSTNode addRecursive(BSTNode current, String key, Contact data) {
		        if (current == null) {
		            return new BSTNode(key, data);
		        }

		        if (key.compareTo(current.key) < 0) {
		            current.left = addRecursive(current.left, key, data);
		        } else if (key.compareTo(current.key) > 0) {
		            current.right = addRecursive(current.right, key, data);
		        } else {
		            // key already exists, update the data
		            current.data = data;
		        }

		        return current;
		    }

		    public Contact searchByNameOrNumber(String type, String data) {
		        if (root == null)
		            return null;

		        if (type.equalsIgnoreCase("Name")) {
		            return searchByName(root, data);
		        } else if (type.equalsIgnoreCase("Number")) {
		            return searchByNumber(root, data);
		        }

		        return null; // If the type is neither "Name" nor "Number"
		    }

		    private Contact searchByName(BSTNode current, String name) {
		    	 if (current == null) {
			            return null;
			        }
			        if (name.equals(current.data.getName())) {
			            return current.data;
			        }
			        Contact leftSearch = searchByName(current.left, name);
			        if (leftSearch != null) {
			            return leftSearch;
			        }
			        return searchByName(current.right, name);
		    }

		    private Contact searchByNumber(BSTNode current, String number) {
		        if (current == null) {
		            return null;
		        }
		        if (number.equals(current.data.getPhoneNumber())) {
		            return current.data;
		        }
		        Contact leftSearch = searchByNumber(current.left, number);
		        if (leftSearch != null) {
		            return leftSearch;
		        }
		        return searchByNumber(current.right, number);
		    }
		
		    public void SearchByPrintAll(String type, String data) {
		        boolean found = false;

		        if (type.equals("Email") || type.equals("Address") || type.equals("Birthday")) {
		            // For these types, since they are not the key of the BST, a full traversal is required.
		            found = searchAndPrintAll(root, type, data);
		        } else if (type.equals("PrintByFirstName")) {
		            // If searching by first name, which is the key, use a more efficient search.
		            found = searchAndPrintByName(root, data);
		        }

		        if (!found) {
		            System.out.println("Sorry, no contacts found matching the search criteria.");
		        }
		    }


		    private boolean searchAndPrintAll(BSTNode node, String type, String data) {
		        if (node == null) {
		            return false;
		        }

		        boolean foundLeft = searchAndPrintAll(node.left, type, data); // Recursive call on left child
		        boolean foundRight = searchAndPrintAll(node.right, type, data); // Recursive call on right child

		        boolean foundCurrent = false;
		        if (type.equals("Email") && node.data.getEmailAddress().equalsIgnoreCase(data)) {
		            foundCurrent = true;
		        } else if (type.equals("Address") && node.data.getAddress().equalsIgnoreCase(data)) {
		            foundCurrent = true;
		        } else if (type.equals("Birthday") && node.data.getBirthday().equalsIgnoreCase(data)) {
		            foundCurrent = true;
		        }

		        if (foundCurrent) {
		            node.data.display(); // Display the contact data if it matches the search criteria
		        }

		        return foundLeft || foundCurrent || foundRight; // Return true if found in any part of the subtree
		    }


		    private boolean searchAndPrintByName(BSTNode node, String name) {
		        if (node == null) {
		            return false;
		        }

		        int compareResult = name.compareToIgnoreCase(node.key);
		        if (compareResult == 0) {
		            node.data.display();
		            return true;
		        } else if (compareResult < 0) {
		            return searchAndPrintByName(node.left, name);
		        } else {
		            return searchAndPrintByName(node.right, name);
		        }
		    }
		    
		    public void searchAndPrintByFirstName(String firstName) {
		        searchAndPrintByFirstNameRecursive(root, firstName);
		    }

		    private void searchAndPrintByFirstNameRecursive(BSTNode node, String firstName) {
		        if (node == null) {
		            return;
		        }

		        // Assuming the first name is the first part of the full name
		        String contactFirstName = node.key.split(" ")[0];
		        if (contactFirstName.equalsIgnoreCase(firstName)) {
		            node.data.display(); // Display the contact if the first name matches
		        }else {
		            System.out.println();
		            System.out.println("There's no contact's found");
		    	    System.out.println();
		        }

		        // Recur for left and right subtrees
		        searchAndPrintByFirstNameRecursive(node.left, firstName);
		        searchAndPrintByFirstNameRecursive(node.right, firstName);
		    }
		    public boolean isNameExist(String name) {
		        return searchByName(root, name) != null;
		    }


}
