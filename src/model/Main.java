// Matthew Wu 
// TCSS 342 - Compressed Literature

package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

public class Main {
		
	public static StringBuilder ORIGINAL_MESSAGE = new StringBuilder();
	
	// Reads in text file and creates compressed file. Testing the coding tree is commented out temporarily
	public static void main(String[] args) {
		String file="WarAndPeace.txt";
		readTextFile(file);
		compressFile();
//		testCodingTree();
	}
	
	// Reads in a text file and saves it as the original message
	public static void readTextFile(String file) {
		Scanner inputFile;
		try {
			inputFile = new Scanner(new File(file));
			
			while (inputFile.hasNextLine()) {
				StringBuilder line = new StringBuilder();
				line.append(inputFile.nextLine());
				if (inputFile.hasNextLine()) {
					line.append("\r\n");
				}
				ORIGINAL_MESSAGE.append(line.toString());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// This method logs the start time and calls on a helper method to compress the file. Also calls 
	// on a method to display the statistics. 
	public static void compressFile() {
		CodingTree myTree = new CodingTree(ORIGINAL_MESSAGE.toString());
		long startTime = System.nanoTime(); 
		Map<Character, String> codes = myTree.codes();
		createCodesFile(codes);
		String encodedMessage = myTree.bits();
		createCompressedFile(encodedMessage);
		displayStats(startTime);
	}	
	
	// This method creates an external file called codes.txt that contains the codes for each 
	// character in the original message
	private static void createCodesFile(Map<Character, String> codes) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("codes.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		writer.println(codes);
		writer.close();
	}
	
	// A private helper method for properly compressing the file. 
	private static void createCompressedFile(String bits) {
		FileOutputStream out = null;
	
		String temp;
		int parsed;
		int split = bits.length() / 8;
		
		try {
			out = new FileOutputStream("compressed.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for(int i = 0; i < split * 8; i+= 8) {
			temp = bits.substring(i, i + 8);
			parsed = Integer.parseInt(temp, 2);
			Byte b = (byte)parsed; 
			
			try {
				out.write(b);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		temp = bits.substring(split * 8, bits.length()); 
		parsed = Integer.parseInt(temp, 2);
		Byte b = (byte)parsed; 
		try {
			out.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// This method displays the statistics 
	public static void displayStats(long startTime) {
		File original = new File("WarAndPeace.txt");
		File compressed = new File("compressed.txt");
		System.out.println("Original size: " + original.length() / 1000 + " KB");
		System.out.println("Compressed size: " + compressed.length() / 1000 + " KB");
		double ratio = (double) (compressed.length() / 1000) / (double) (original.length() / 1000) * 100;
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("Compression ratio: " +  df.format(ratio) + "%");
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("Elapsed time: " + duration + "ms");
	}
	
	// This method is for testing the coding tree. 
	public static void testCodingTree() {
		CodingTree tree = new CodingTree("HELLO WORLD THIS IS NOT A TEST. THIS IS THE BEGINNING OF THE END.");
		Map<Character, String> codes = tree.codes();
		String encodedMessage = tree.bits();
		
		System.out.println("Contents of the tree: " + codes);
		System.out.println("Encoded message: " + encodedMessage);	
	}
}