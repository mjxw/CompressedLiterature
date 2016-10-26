package model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// This class implements the coding tree which takes in a message and measures character frequencies. 
// This message is then encoded using the huffman encoding scheme. 
public class CodingTree {

	public static String myLiterature; 
	public static Map<Character, Integer> myCharMap; // map of characters and their frequencies
	public static PriorityQueue<HuffmanNode> nodeQueue; // priority queue of nodes 
	public static Map<Character, String> codes; // map of characters and their encoding
	public static HuffmanNode root; 
	public static String bits;

	// Constructs the coding tree which is a binary tree of huffman nodes 
	public CodingTree(String message) {
		myLiterature = message; 
		bits = "";
		codes = new HashMap<Character, String>();
		nodeQueue= new PriorityQueue<HuffmanNode>();
		characterCount(myLiterature);
		myCharMap.forEach( (k,v) -> createNode(v, k)); // create nodes for each character & frequency
		buildTree(nodeQueue);
		root = nodeQueue.peek();
	}
	
	// Sets up the map of characters and their encoding
	public Map<Character, String> codes() {
		String temp = "";
		if (root != null) {
			codes = codes(temp, root);
		}
		return codes;
	}
	
	// Helper method to create map of characters with encoding. 
	// Encoding is appending 0 for every left, and 1 for every right
	private Map<Character, String> codes(String temp, HuffmanNode root) {
		if (root.left == null && root.right == null) {
			codes.put(root.character, temp);
		} else {
			codes(temp + "0", root.left);
			codes(temp + "1", root.right);
		}
		return codes;
	}
	
	// Uses a hashmap to get unique character frequency
	public void characterCount(String message) {
		myCharMap = new HashMap<Character, Integer>();

		for(int i = 0; i < message.length(); i++) {
			Character c = message.charAt(i);
			if(myCharMap.containsKey(c)) { // if it already exists
				myCharMap.put(c, myCharMap.get(c) + 1);
			} else {
				myCharMap.put(c, 1);
			}
		}
	}
	
	// A method for creating a huffman node. Takes in a frequency and a character to make a node. 
	// After node is created, add it to the priority queue
	private void createNode(int frequency, char character) {
		HuffmanNode node = new HuffmanNode(frequency, character);
		nodeQueue.add(node);
	}
	
	// Combines two nodes by order of frequencies to build a tree 
	private PriorityQueue<HuffmanNode> buildTree(PriorityQueue<HuffmanNode> priorityQueue) {
		while(nodeQueue.size() > 1) {
			HuffmanNode node1 = nodeQueue.poll();
			HuffmanNode node2 = nodeQueue.poll();
			HuffmanNode newNode = new HuffmanNode(node1.frequency + node2.frequency, node1, node2);
			nodeQueue.add(newNode);			
		}
		return nodeQueue;
	}
	
	// Create a string representation of the binary encoding 
	public String bits() {
		StringBuilder bitsBuilder = new StringBuilder();
		for(int i = 0; i < myLiterature.length(); i++) {
			String binaryValue = codes.get(myLiterature.charAt(i));
			bitsBuilder.append(binaryValue);
		}
		bits = bitsBuilder.toString();
		return bits;		
	}
}
