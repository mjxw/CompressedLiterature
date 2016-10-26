import java.util.PriorityQueue;


/**
 * TCSS 342
 * Assignment 4 Compressed Literature 2
 */

/**
 * 
 * 
 * @author Arrunn Chhouy
 * @version 1.0
 */
public class CodingTree {
	/** 
	  * A data member that is a map of characters in the messages to binary codes 
	  * created by your tree 
	  */
	private MyHashTable<String, String> codes;
	
	// A map of the character frequency
	private MyHashTable<String, String> wordFrequency;
	
	// A PriorityQueue  
	private PriorityQueue<TreeNode> queue;
	
	// Contains the main tree
	private TreeNode mainTree;
	
	// A string of the message
	private String text;
		
	public CodingTree(String message) {
		codes = new MyHashTable<String, String>(32768);
		wordFrequency = new MyHashTable<String, String>(32768);
		queue = new PriorityQueue<TreeNode>();
		text = message;
		
		frequency();
	}
	
	/**
	 * Counting the frequency of each character in the text file
	 */
	private void frequency() {
		for(int i = 0; i < text.length(); i++) {
			if(!wordFrequency.containsKey(text.charAt(i))) {
				wordFrequency.put(text.charAt(i), 1);
			} else {
				int count = wordFrequency.get(text.charAt(i));
				count++;
				wordFrequency.put(text.charAt(i), count);
			}
		}
	}
    
    private void stringParse(String text) {
        String target = text;
        for (char c : target.toCharArrray()) {
            if(
        }
    }
	
	/**
	 * A TreeNode class.
	 * 
	 * @author Arrunn
	 * @version 1.0
	 */
	public class TreeNode implements Comparable<TreeNode> {
		// Holds the data in the left of the tree
		private TreeNode myLeft;
		
		// Holds the data in the right of the tree
		private TreeNode myRight;
		
		// The character in the node
		private Character myData;
		
		// The weight of the letter.
		private int myWeight;
		
		/**
		 * A constructor of the TreeNode that initializes the fields
		 * 
		 * @param data of the Character
		 * @param left is TreeNode
		 * @param right is TreeNode
		 * @param weight is the frequency
		 */
		public TreeNode(Character data, TreeNode left, TreeNode right, int weight) {
			myData = data;
			myLeft = left;
			myRight = right;
			myWeight = weight;
		}
		
		/**
		 * Checks to see if the TreeNode is a leaf
		 * 
		 * @return a boolean
		 */
		public boolean isLeaf() {
			return (myLeft == null && myRight == null);
		}
		
		/**
		 * Returns the character data
		 * 
		 * @return a Character
		 */
		public Character getData() {
			return myData;
		}
		
		/**
		 * Gets the frequency of the character
		 * 
		 * @return an int.
		 */
		public int getWeight() {
			return myWeight;
		}
		
		/**
		 * Gets the left node of this TreeNode
		 * 
		 * @return a TreeNode
		 */
		public TreeNode getLeft() {
			return myLeft;
		}
		
		/**
		 * Gets the right node of this TreeNode
		 * 
		 * @return a TreeNode
		 */
		public TreeNode getRight() {
			return myRight;
		}
		
		/**
		 * Compares with another TreeNode to see which TreeNode 
		 * is larger
		 * 
		 * @return an int
		 */
		@Override
		public int compareTo(TreeNode other) {
			TreeNode node = other;
			int compare = 0;
			if(myWeight > node.getWeight()) {
				compare = 1;
			} else if(myWeight < node.getWeight()) {
				compare = -1;
			}
			return compare;
		}
		
		/**
		 * A string representation of the TreeNode
		 * 
		 * @return a String
		 */
		public String toString() {
			return "Character: " + myData +" Weight: "+ myWeight;
			
		}
	}
}
