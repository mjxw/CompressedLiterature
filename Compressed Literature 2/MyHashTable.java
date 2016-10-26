import java.util.ArrayList;
import java.util.List;

/* Notes: 
 * Need to fix size to that when you try to add the same key it does not add to the size.
 * 
 * getValue - find out why recursion won't return the value for a key
 */

/**
 * TCSS 342
 * Assignment 4 Compressed Literature 2
 */

/**
 * MyHashTable class that implements a hash table
 * 
 * @author Arrunn Chhouy
 * @author Matt Wu
 * @version 1.0
 */
public class MyHashTable<K, V> {
	
	// The max size of the hash table
	private int capacity;
	
	// Number of elements in the hash table
	private int size;
	
	// Maximum amount of probes
	private int maxProbe;
	
	// Total amount of probes
	private int totalProbe;
	
	// The value of a key
	private V getValue;
	
	// Contains all the entries
	private Entry<K, V>[] entryList; 
	
	// Keeps track of the probing
	private int[] probeStat;
	
	// Constructor
	public MyHashTable(int capacity) {
		this.capacity = capacity;
		size = 0;
		maxProbe = 0;
		totalProbe = 0;
		entryList = new Entry[this.capacity];
		probeStat = new int[this.capacity];
	}
	
	/**
	 * Sets up an entry with the key and value 
	 * 
	 * @param searchKey is the key
	 * @param newValue is the value
	 */
	public void put(K searchKey, V newValue) {
		Entry<K, V> entry = new Entry<K, V>(searchKey, newValue);
		 
		// Index is the hashcode of searchKey
		int index = hash(searchKey);
		int count = 0;
		// If the key does not exist create a new entry and add it into the array
		if(entryList[index] == null) {
			entryList[index] = entry;
			size++;
		// If there is an entry but the keys are not the same then probe until open spot	
		} else if(entryList[index] !=  null && !entryList[index].getKey().equals(searchKey)) {
			/* If the index is equal to the last index of the array set the index to 0 
			 * because if there an element in the last index there won't be a out of bound exception 
			 * and we can reset back to the first index 
			 */
			if(index == capacity - 1) {
				probe(entry, 0, count);
			} else {
				probe(entry, index + 1, count);
			}
			size++;
		//	If the key exist overwrite the value at this key.
		} else {
			entryList[index].setValue(newValue);
		}
	}
	
	// 
	private void probe(Entry<K, V> entry, int index, int count) {
		if(entryList[index] == null) {
			//System.out.println("Count: " + count);
			probeStat[count]++;
			totalProbe += count;
			maxProbe = Math.max(maxProbe, count);
			entryList[index] = entry;
		} else if(index == capacity - 1) {
			count++;
			probe(entry, 0, count);
		} else {
			count++;
			probe(entry, index + 1, count);
		}
	}
	
	public V get(K searchKey) {
//		V value = null;
		int index = hash(searchKey);
		if(entryList[index] != null) {
			if(entryList[index].getKey().equals(searchKey)) {
				getValue = entryList[index].getValue();
			} else if(index == capacity - 1) {
				search(searchKey, 0);
			} else {
				search(searchKey, index + 1);
			}
		} else {
			System.out.println("Key does not exist!");
		}
		return getValue;
	}
	
	private void search(K searchKey, int index) {
		if(entryList[index].getKey().equals(searchKey)) {
			getValue = entryList[index].getValue();
		} else if(index == capacity - 1) {
			search(searchKey, 0);
		} else {
			search(searchKey, index + 1);
		}
	}
	
	public boolean containsKey(K searchKey) {
		boolean contain = false;
		int index = hash(searchKey);

		int count = 0;
		// If the value at the index is null 
		if(entryList[index] == null) {
			contain = false;
		
		// If search key is equal to the key 	
		} else if(entryList[index] != null && !entryList[index].getKey().equals(searchKey)) {
			if(index == capacity - 1) {
				contain = contains(searchKey, 0, count);
			} else {
				contain = contains(searchKey, index + 1, count);
			}
		} else {
			contain = true;
		}
		
		return contain;
	}
	
	private boolean contains(K searchKey, int index, int count) {
		boolean contain;
		
		if(entryList[index] == null || count == capacity - 1) {
			contain = false;
		} else if(entryList[index].getKey().equals(searchKey)) {
			contain = true;
		} else if(index == capacity - 1) {
			contain = contains(searchKey, 0, count + 1);
		} else {
			contain = contains(searchKey, index + 1, count + 1);
		}
		return contain;
	}
	
	public void stats() {
		int probe = 0;
		int countEmpty = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("Hash Table Stats\n");
		sb.append("================\n");
		sb.append(String.format("Number of Entries: %d \n", size));
		sb.append(String.format("Number of Buckets: %d \n", capacity));
		sb.append("Histogram of Probes: \n");
		sb.append("[" + probeStat[0]);
		for(int i = 1; i < maxProbe; i++) {
			if(probeStat[i] == 0) {
				countEmpty++;
			}
			probe += probeStat[i];
			sb.append(", " + probeStat[i]);
		}
		sb.append("] \n");
		double percent = (((double)maxProbe - countEmpty )/ maxProbe) * 100; 
		sb.append(String.format("Fill Percentage: %f \n", percent));
		sb.append(String.format("Max Linear Probe: %d \n", maxProbe));
		sb.append(String.format("Average Linear Probe: %f \n", ((double)totalProbe / probe)));
		System.out.println(sb);
	}
	
	public int size() {
		return size;
	}
	
	private int hash(K key) {
		
//		return Math.abs(key.hashCode() % capacity);
		
		// or
		
		return key.hashCode() % (( capacity / 2 ) + ( capacity / 2 ));
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < capacity - 1; i++) {
			sb.append("Key: " + entryList[i].getKey() + " || ");
			sb.append("Value: " + entryList[i].getValue() + "\n");
		}
		return sb.toString();
	}
	
	// Inner class that contains the key and value.
	private static class Entry<K,V>{
		
		// Sets the key to final so it cannot change
		final K key;
		
		// The value to be store to the key
		V value;
		
		public Entry(K key,V value){
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "(" + key + "," + value + ")";
		}
	}
}
