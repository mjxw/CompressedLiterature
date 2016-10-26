
public class Main {
	public static void main(String[] args) {
		//testHashTable();
	}
	
	/**
	 * A test to ensure that the methods of our hash table is working correctly.
	 * The method test the put, get, contains, and the size.
	 */
	private static void testHashTable() {
		MyHashTable<String, Integer> hashTable = new MyHashTable<String, Integer>(20);
		
		/* Test value with the same hash code to see if it probe and 
		 store into the next spot available */
		hashTable.put("Hello", 5);
		hashTable.put("elloH", 7);
		hashTable.put("olleH", 8);
		hashTable.put("lqHel", 23);
		hashTable.put("Apple", 6);
		hashTable.put("elppA", 20);
		hashTable.put("elApp", 81);
		hashTable.put("Aplpe", 1);
		hashTable.put("World", 26);
		hashTable.put("Wlrod", 34);
		hashTable.put("Wolrd", 56);
		hashTable.put("dlroW", 10);
		hashTable.put("Cake", 13);
		hashTable.put("akeC", 17);
		hashTable.put("ekaC", 53);
		hashTable.put("aCke", 16);
		hashTable.put("Table", 10);
		hashTable.put("Tebla", 73);
		hashTable.put("elbaT", 81);
		hashTable.put("elbTa", 44);
		
		
		
		/* Make sure same key values will overwrite the value */
		hashTable.put("Hello", 10);
		
		/* Prints out the value after inputting a key */
		System.out.println("Key: olleH Value 1: " + hashTable.get("olleH"));
		System.out.println("Key: Hello Value 2: " + hashTable.get("Hello"));
		System.out.println("Key: elloH Value 3: " + hashTable.get("elloH"));
		System.out.println("Key: lqHel Value 4: " + hashTable.get("lqHel"));
		System.out.println("Key: Apple Value 5: " + hashTable.get("Apple"));
		
		// Checks the size to ensure the right amount of element is being added
		System.out.println("Size: " + hashTable.size());
		
		// Should be true
		System.out.println("Contains the key Table: " + hashTable.containsKey("Table"));
		System.out.println("Contains the key Hello: " + hashTable.containsKey("Hello"));
		System.out.println("Contains the key World: " + hashTable.containsKey("World"));
		System.out.println("Contains the key lqHel: " + hashTable.containsKey("lqHel"));
		System.out.println("Contains the key Apple: " + hashTable.containsKey("Apple"));
		
		// Should be false
		System.out.println("Contains the key ello: " + hashTable.containsKey("ello"));
		System.out.println("Contains the key rock: " + hashTable.containsKey("rock"));
		
		hashTable.stats();
		
		System.out.println(hashTable);
		
	}
}
