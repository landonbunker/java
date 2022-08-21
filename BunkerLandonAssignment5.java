/* LandonBunker, 
CS 1450,
section T and TR, 
Assignment 2, 
due: 10/9/21,
use the stack methods to find the average of the stack and then create a stack from an arraylist and manipulate and sort the stack 

*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class BunkerLandonAssignment5 {

	public static void main(String[] args) throws IOException {
		
		//initialize the array
		int[] values = {8, 17, 62, 4, 6, 2, 42, 10, 3, 7};
		
		//create the stack
		Stack<Integer> stack = new Stack <Integer>();
		
		//put the arrays values in the stack
		for (int i =0; i < values.length; i++) {
			int tempValue = values[i];
			stack.push(tempValue);
		}
		
		//call the find average and store it into a variable
		double average = findAverage(stack);
		
		System.out.println("Stack Values After Compute Average");
		System.out.println("---------------------------------------------------");
		
		// print the stack after finding average
		printStack(stack);
		
		//print the average
		System.out.println("The average is " + average + "\n");
		//end of part 1
		
		
		
		
		//beginning of part 2
		
		
		// open the file and put it into a variable as well as create a scanner variable
		File inputFileName = new File("numbers.txt");
		Scanner inputFile = new Scanner (inputFileName);
		
		// create a generic stack
		GenericStack<Integer> genericStack1 = new GenericStack <Integer>();
		
		//while loop to initialize values in the stack
		while (inputFile.hasNextInt()) {
			 int tempValue = inputFile.nextInt();
			 genericStack1.push(tempValue);
		}
		
		System.out.println("Numbers from file placed on number stack (unsorted)");
		System.out.println("---------------------------------------------------");
		
		//print the stack
		GenericStack.printStack(genericStack1);
		
		//create a second stack to go into the soring function
		GenericStack<Integer> genericSortedStack1 = new GenericStack <Integer>();
		
		System.out.println();
		System.out.println("Numbers from file placed on number stack (sorted)");
		System.out.println("---------------------------------------------------");
		
		//call sort function
		GenericStack.sortStack(genericStack1, genericSortedStack1);
		
		//print sorted stack
		GenericStack.printStack(genericSortedStack1);
		
		System.out.println();
		System.out.println("Numbers of times each value appears");
		System.out.println("---------------------------------------------------");
		
		//call display appearance count
		GenericStack.displayAppearanceCounts(genericSortedStack1);

		
		
		//end of integer generic stack
		
		
		
		
		// start of strings generic stack
		inputFileName = new File("strings.txt");
		inputFile = new Scanner (inputFileName);
		
		//create the second stack
		GenericStack<String> genericStack2 = new GenericStack <String>();
		
		// while loop to intialize values
		while (inputFile.hasNext()) {
			 String tempValue = inputFile.next();
			 genericStack2.push(tempValue);
		}
		
		// create second stack to sort
		GenericStack<String> genericSortedStack2 = new GenericStack <String>();
		
		System.out.println();
		System.out.println("Numbers from file placed on String stack (unsorted)");
		System.out.println("---------------------------------------------------");
		
		// print and sort the stacks
		GenericStack.printStack(genericStack2);
		GenericStack.sortStack(genericStack2, genericSortedStack2);
		
		System.out.println();
		System.out.println("Numbers from file placed on String stack (sorted)");
		System.out.println("---------------------------------------------------");
		
		//print sorted stack
		GenericStack.printStack(genericSortedStack2);
		
		System.out.println();
		System.out.println("Number of times each value appears");
		System.out.println("---------------------------------------------------");
		
		//call display appearance count
		GenericStack.displayAppearanceCounts(genericSortedStack2);
		
		inputFile.close();
		
	}
	//find average function
	public static double findAverage (Stack<Integer> stack) {
		Stack<Integer> tempStack = new Stack<>();
		
		double average = 0.0;
		double total = 0;
		
		double size = stack.size();
		
		for (int i = 0; i < size; i++) {
			int tempValue = stack.pop();
			total = total + tempValue;
			tempStack.push(tempValue);
		}
		
		for (int i =0; i < size; i++ ) {
			stack.push(tempStack.pop());
		}
		
		average = total / size;
		
		return average;
	}
	
	//print stack function for stack class
	public static void printStack (Stack<Integer> stack) {
		
		Stack<Integer> tempStack = new Stack<>();
		
		int size = stack.size();
		
		for (int i = 0; i < size; i++) {
			int tempValue = stack.pop();
			System.out.println(tempValue);
			tempStack.push(tempValue);
			
		}
		
		for (int i =0; i < size; i++ ) {
				stack.push(tempStack.pop());	
		}
	
		System.out.println();
	}
	
}

// generic stack class
class GenericStack<E> {
	private ArrayList<E> list;
	
	public GenericStack() {
		list = new ArrayList<E>();
	}
	
	public boolean isEmpty() {
		if (list.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public E peek() {
		if (list.isEmpty()) {
			return null;
		}
		
		else {
			return list.get(list.size() - 1);
		}
		
	}
	
	public E pop() {
		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.remove(list.size() - 1 );
		}
		
	}
	
	public void push(E value) {

		list.add(value);
	}
	
	// print stack function for generic classes
	public static <E> void printStack (GenericStack<E> stack) {
		
		Stack<E> tempStack = new Stack<E>();
		
		
		
		while (!stack.isEmpty()) {
			E tempValue = stack.pop();
			System.out.println(tempValue);
			tempStack.push(tempValue);
			
		}
		
		while (!tempStack.isEmpty()) {
				stack.push(tempStack.pop());	
		}
	
}
	//comparable function to sort the array
	public static <E extends Comparable<E>> void sortStack (GenericStack<E> unsortedStack, GenericStack<E> sortedStack) {
		
		while (!unsortedStack.isEmpty()) {
			if (!sortedStack.isEmpty()) {
				int tempComp = unsortedStack.peek().compareTo((sortedStack).peek());
			}
			if (sortedStack.isEmpty()){
				sortedStack.push(unsortedStack.pop());
				
			}
			
			else if ((unsortedStack.peek()).compareTo((sortedStack).peek()) >= 1)  {
				
				E tempValue = unsortedStack.pop();
				
				
				while (sortedStack.peek().compareTo((tempValue)) < 0) {
					
					unsortedStack.push(sortedStack.pop());
					
				}
				
				sortedStack.push(tempValue);
			}
			
			else if ((unsortedStack.peek()).compareTo((sortedStack).peek())  <= 0) {
				sortedStack.push(unsortedStack.pop());
			}
			
			}
		}
	//display the number of appearances functions
	 public static <E extends Comparable<E>>void displayAppearanceCounts(GenericStack<E> stack) {
		 GenericStack<E> genericStack = new GenericStack <E>();
		 
		 
		 
		 while(!stack.isEmpty()) {
			 int appearanceCounter = 1;
			 boolean done = true;
			 E currentValue = stack.pop();
			 genericStack.push(currentValue);
			 
			 
			 while(!stack.isEmpty() && done != false) {
				 E nextValue = stack.pop();
				 genericStack.push(nextValue);
				 
				 if ((currentValue).compareTo(nextValue) == 0){
					 appearanceCounter++;
				 }
				 
				 else {
					 done = false;
				 }
			 }
			 
			 while (!genericStack.isEmpty()) {
				E tempValue =  genericStack.pop();
				if ((tempValue).compareTo(currentValue) != 0) {
					 stack.push(tempValue);
				 }
				 
			 }
			 
			 System.out.println(currentValue + " appeared " + appearanceCounter + " times on the stack" );
			 
		 }
	 }
}
  
