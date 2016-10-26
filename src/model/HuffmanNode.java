// Matthew Wu 
// TCSS 342 - Compressed Literature

package model;

// This class implements the huffman node 
public class HuffmanNode implements Comparable<HuffmanNode> { 
   public int frequency; // Frequency of this node
   public char character; // a specific character
   public HuffmanNode left; // Reference to left subtree
   public HuffmanNode right; // Reference to right subtree
   
   
   // Basic constructor for a huffman node with a frequency and a character
   public HuffmanNode(int frequency, char character) {
      this(frequency, character, null, null);
   }
   
   // Constructor for a huffman node with a frequency, a specific character, and a left and right child
   public HuffmanNode(int frequency,char character, HuffmanNode left, HuffmanNode right) {
      this.frequency = frequency; 
      this.character = character;
      this.left = left; 
      this.right = right; 
   }
   
   // Constructor for a huffman node with a frequency and a left and right child node
   public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
      this.frequency = frequency;
      this.left = left;
      this.right = right;
   }
   
   // To string method for a huffman node 
   public String toString() { 
      return "[ " + frequency + ", " + character + "]";
   }
   
   // For comparing huffman nodes
   @Override
   public int compareTo(HuffmanNode other) {
      return frequency - other.frequency;
   }
   
}

