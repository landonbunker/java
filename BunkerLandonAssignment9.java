/* LandonBunker, 
CS 1450,
section T and TR, 
Assignment 2, 
due: 11/16/21, 
create your own linked lists so that you c an read in the destiantions from a file
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BunkerLandonAssignment9 {

	public static void main(String[] args) throws FileNotFoundException {

		//open new file
		File inputFileName = new File("JapanItinerary.txt");
		Scanner inputFile = new Scanner (inputFileName);
		
		//create instances of both linked lists
		ItineraryLinkedList linkedList = new ItineraryLinkedList(null, 0);
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList(null, null);
		
		
		
		while (inputFile.hasNext()) {
	
			
			 
			int stop = inputFile.nextInt();
			String type = inputFile.next();
			String name = inputFile.next();
			String activity = inputFile.nextLine();
			
			type = type.trim();
			name = name.trim();
			
			//create destinations from file and add them to the linked list
			Destination destination = new Destination(stop, type, name, activity);
			linkedList.addDestination(destination);
			
		}
		
		//print unsorted list
		System.out.println("Unsorted Japan Itinerary");
		linkedList.printList();
		
		//sort and print the list
		System.out.println("\n\nSorted Japan Itinerary");
		linkedList.bubbleSort();
		linkedList.printList();
		
		//read from second file
		inputFileName = new File("JapanItineraryUpdates.txt");
		inputFile = new Scanner (inputFileName);
		
		
		while(inputFile.hasNext()) {
			
			String destinationstr = inputFile.next();
			int stop = inputFile.nextInt();
			String type = inputFile.next();
			String name = inputFile.next();
			String activity = inputFile.nextLine();
			
			//create a new destination and update itinerary with adding destinations
			Destination destination = new Destination( stop, type, name, activity);
			linkedList.updateItinerary(destinationstr, destination);
			doubleLinkedList.addDestination(destination);
			
		}
		
		
		//print new list with updates
		System.out.println("\n\nUpdated Itinerary With Added Adventures");
		linkedList.printList();		
		
		//print the double linked list backwards
		System.out.println("\n\nAdventure Destinations in Doubly Linked List - Printed Backwards");
		doubleLinkedList.printListBackwards();
		
		
	}

}

//destination class
class Destination {
	private int stop;
	private String type;
	private String name;
	private String activity;
	
	public Destination(int stop, String type, String name, String activity) {
		this.stop = stop;
		this.type = type;
		this.name = name;
		this.activity = activity;
	}
	
	public int getStop() {
		return stop;
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getActivity() {
		return activity;
	}
	
	public String toString() {
		return name + "\t\t" + type + "\t" + activity;
	}
}

//linked list
class ItineraryLinkedList {
	private Node head;
	private int size;
	
	public ItineraryLinkedList(Node head, int size) {
		this.head = null;
		this.size = 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public void addDestination(Destination destinationToAdd) {
		if (size == 0 ) {
			Node tempNode = new Node(destinationToAdd, null);
			head = tempNode;
			size++;
		}
		
		else {
			Node tempNode = new Node(destinationToAdd, head);
			head = tempNode;
			size++;
		}
		
	}
	
	public void updateItinerary (String insertBeforeDestination, Destination adventureDestination) {
		Node currentNode= head;
		Node previousNode = head;
		boolean found  = false;
		if (insertBeforeDestination.equals(currentNode.destination.getName())) {
			Node tempNode = new Node(adventureDestination, currentNode);
			head = tempNode;
			found = true;
			
		}
		
		while(!found) {
			previousNode = currentNode;
			currentNode = currentNode.next;
				
			if(insertBeforeDestination.equals(currentNode.destination.getName()))  {
				Node tempNode = new Node(adventureDestination, previousNode.next);
				previousNode.next = tempNode;
				found = true;
				size++;
				}
				
			}
			
			
		}
	
	
	public void bubbleSort() {
		int n = size;  
	  
		
	    for(int i=0; i < n; i++){  
	    	Node currentNode= head;
	  		Node previousNode = head;
	    	
	    	
	    	for(int j=1; j < (n); j++) {
	    		currentNode = currentNode.next;
	    		if( currentNode.destination.getStop() < previousNode.destination.getStop()){			
	    			swapNodeData(currentNode, previousNode);
	    	} 
	    		
	    	previousNode = previousNode.next;
	    } 
	    	
	}
	     
	}
	
	public void swapNodeData (Node node1, Node node2) {
		Destination tempdestination;
		
		tempdestination = node1.destination;
		node1.destination = node2.destination;
		node2.destination = tempdestination;
		
	}
	
	public void printList() {
		
		Node currentNode = head;
		
		System.out.println("Destination\t\tNameType\tActivity");
		System.out.println("----------------------------------------------------------------------------------");
		for(int i =0; i < size; i++) {
			
			System.out.println(currentNode.destination.toString());
			
			currentNode = currentNode.next;
		}
	}
	
	//node class
	class Node {
		private Destination destination;
		private Node next;
		
		public Node(Destination destination, Node next) {
			this.destination = destination;
			this.next = next;
		}
	}
}

//double linked list class
class DoubleLinkedList {
	private Node head;
	private Node tail;
	
	public DoubleLinkedList(Node head, Node tail) {
		this.head = null;
		this.tail = null;
	}
	
	public void addDestination (Destination destination) {
		if (head == null) {
			Node tempNode = new Node(destination, null, null);
			head = tempNode;
			tail = tempNode;
		}
		 
		else {
			Node tempNode = new Node(destination, tail, null);
			tail = tempNode;
		}
	}
	
	public void printListBackwards() {
		Node currentNode = tail;
		
		System.out.println("Destination\t\tName\tType\tActivity");
		System.out.println("----------------------------------------------------------------------------------");
		
		while(currentNode != null) {
			
			System.out.println(currentNode.destination.toString());
			
			currentNode = currentNode.previous;
	}

	}
	//double linked list node class
	class Node {
		private Destination destination;
		private Node previous;
		private Node next;
		
		public Node (Destination destination, Node previous, Node next) {
			this.destination = destination;
			this.previous = previous;
			this.next = next;
		}
	}
	
}