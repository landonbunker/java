/* LandonBunker, 
CS 1450,
section T and TR, 
Assignment 2, 
due: 11/4/21, 
Read from a file and decrypt a message from the three files that are given
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BunkerLandonAssignment8 {

	public static void main(String[] args) throws FileNotFoundException {
		
		//create arraylists
		ArrayList<Character> arrayMessage1 = new ArrayList<Character>();
		ArrayList<Integer> arrayMessage2 = new ArrayList<Integer>();
		ArrayList<Integer> arrayKey = new ArrayList<Integer>();
		
		//open the file and scanner objects for array lists
		File inputFileName = new File("arrayMessage1.txt");
		Scanner inputFile = new Scanner (inputFileName);
		
		File inputFilearrayMessage2 = new File("arrayMessage2.txt");
		Scanner inputFile2 = new Scanner (inputFilearrayMessage2);
		
		File inputFileArrayKey= new File("arrayKey.txt");
		Scanner inputFile3 = new Scanner (inputFileArrayKey);
		
		
		//read the input into the arraylists from various files
		while (inputFile.hasNext()) {
			
			String tempString = inputFile.next();
			
			char charArray[] = tempString.toCharArray();
	
			for(int i =0; i < charArray.length; i++) {
					arrayMessage1.add(charArray[i]);
			}
		}
		
		while (inputFile2.hasNextInt()) {
			int tempInt = inputFile2.nextInt();
			
			arrayMessage2.add(tempInt);
		}
		
		while (inputFile3.hasNextInt()) {
			int tempInt = inputFile3.nextInt();
			
			arrayKey.add(tempInt);
		}
		
		//create the iterators for the arraylists 
		Iterator<Character> msg1Iterator = arrayMessage1.iterator();
		Iterator<Integer> meg2Iterator = arrayMessage2.iterator();
		Iterator<Integer> keyIterator = arrayKey.iterator();
		
		//create the secret translator instance
		SecretTranslator sl = new SecretTranslator();
		
		//call the decoder method 
		String printString = sl.decode(msg1Iterator, meg2Iterator, keyIterator);
		
		//print the return value of the decoder method
		System.out.println("The First Secret String is - " + printString);
		
		//close the arraylist files
		inputFile.close();
		inputFile2.close();
		inputFile3.close();
		
		
		
		//create queues
		Queue<Character> queueMessage1 = new LinkedList<Character>();
		Queue<Integer> queueMessage2 = new LinkedList<Integer>();
		Queue<Integer> queueKey = new LinkedList<Integer>();
		
		//opne the files and scanners for the queue messages
		inputFileName = new File("queueMessage1.txt");
		inputFile = new Scanner (inputFileName);
		
		inputFilearrayMessage2 = new File("queueMessage2.txt");
		inputFile2 = new Scanner (inputFilearrayMessage2);
		
		inputFileArrayKey= new File("queueKey.txt");
		inputFile3 = new Scanner (inputFileArrayKey);
		
		//read the input from the files into the queues
		while (inputFile.hasNext()) {
				
			String tempString = inputFile.next();
				
			char charArray[] = tempString.toCharArray();
			
			for(int i =0; i < charArray.length; i++) {
					queueMessage1.add(charArray[i]);
				}
			}
			
			while (inputFile2.hasNextInt()) {
				int tempInt = inputFile2.nextInt();
				
				queueMessage2.add(tempInt);
			}
			
			while (inputFile3.hasNextInt()) {
				int tempInt = inputFile3.nextInt();
				
				queueKey.add(tempInt);
			}
			
			//create the iterators for the queues
			Iterator<Character> que1Iterator = queueMessage1.iterator();
			Iterator<Integer> que2Iterator = queueMessage2.iterator();
			Iterator<Integer> queKeyIterator = queueKey.iterator();
			
			//call the decode method with the iterators and set the return value to a string
			printString = sl.decode(que1Iterator, que2Iterator, queKeyIterator);
			
			//print the string from the decode method
			System.out.println("The Second Secret String is - " + printString);
			
			//close the queue files
			inputFile.close();
			inputFile2.close();
			inputFile3.close();
	}

}

//secret translator class
class SecretTranslator {
	private Stack stack;
	
	public SecretTranslator() {
		stack = new Stack();
	}
	
	//decode method that passes three iterators
	public String decode (Iterator<Character> msg1Iterator, Iterator<Integer> meg2Iterator, Iterator<Integer>keyIterator){
		String returnString = "";
		
		
		while(keyIterator.hasNext()) {
			int tempKey = keyIterator.next();
			if (tempKey == 0) {
				stack.push(msg1Iterator.next());
			}
			
			else if (tempKey == 1) {
				int tempInt = 0;
				
				tempInt = meg2Iterator.next();
				char tempChar = (char)tempInt;
				stack.push(tempChar);
				
			}
		}
		
		while(!stack.isEmpty()) {
			char tempChar = stack.pop();
			
			returnString = returnString + tempChar; 
			
		}
		
		
		return returnString;
		
	}
}

//stack method
class Stack {
	private ArrayList<Character> list;
	
	public Stack() {
		
		list = new ArrayList<Character>();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public int getSize() {
		return list.size();
	}
	
	public void push(char value) {
		list.add(value);
	}
	
	public char pop() {
		char returnChar = list.remove(list.size()-1);
		return returnChar;
	}
}