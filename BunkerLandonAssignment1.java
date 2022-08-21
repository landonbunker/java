import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


/* Landon Bunker
/  Data structures
/  (t/r)
/  9/2/21 
/  Assignment 1
/  Create two arrays and read the files into a new array as well as write to a file
*/

public class BunkerLandonAssignment1 {

	public static void main(String[] args) {
		
			//initializing the arrays
			int[] array1 = {13, 18, 1, 4, 8, 16, 11}; 
	        int[] array2 = {9, 10, 5, 12, 3, 7, 14}; 
	        
	        //printing titles
	        System.out.println("Array 1: Original");
	        System.out.println("-----------------");
	        
	        //for loop to print the original array1
	        for (int i = 0; i < array1.length; i++) {
	        	System.out.println("array1[" + i + "] = " + array1[i]);
	        	
	        }
	        
	        System.out.println();
	        System.out.println("Array 2: Original");
	        System.out.println("-----------------");
	        
	        //for loop to print the original array2
	        for (int i = 0; i < array2.length; i++) {
	        	System.out.println("array2[" + i + "] = " + array2[i]);
	        }
	        
	       
	        int arrayIndexCounter = 0;
	        
	        for (int i = 0; i < array1.length; i++) {
	        	
	        	if (array1[i] % 2 == 1) {
	        		for (int j = arrayIndexCounter; j < array2.length; j++) {
	        			if (array2[j] % 2 ==0) {
	        				
	        				int temp = 0; 
	        				temp = array1[i];
	        				
	        				array1[i] = array2[j];
	        				array2[j] = temp;
	        				break;
	        			}
	        			arrayIndexCounter++;
	        			
	        		}
	        	}  	
	        }
	      
	    
	        System.out.println();
	        System.out.println("Array 1: even numbers");
	        System.out.println("-----------------");
	        
	        Arrays.sort(array1);
	        //for loop to print the even array1
	        for (int i = 0; i < array1.length; i++) {
	        	System.out.println("array1[" + i + "] = " + array1[i]);
	        	
	        }
	        
	        System.out.println();
	        System.out.println("Array 2: Odd numbers");
	        System.out.println("-----------------");
	        
	        Arrays.sort(array2);
	        //for loop to print the odd array2
	        for (int i = 0; i < array2.length; i++) {
	        	System.out.println("array2[" + i + "] = " + array2[i]);
	        }
	        
	  
	        
	        File fileName = new File("assignment1.txt");
	        
	        int array1IndexCounter = 0;
	        int array2IndexCounter = 0;
	        
	        try {
				PrintWriter outputFile = new PrintWriter (fileName);
				
				System.out.println("File is in directory: " + fileName.getAbsolutePath());
				System.out.println();
				System.out.println("Show values being written to file");
				System.out.println("---------------------------------");
				
				 for (int i = 0; i < array1.length + array2.length; i++) {
					 int  valueToWrite = 0;
			        	if (array1.length == array1IndexCounter) {
			        		valueToWrite = array2[array2IndexCounter];
			        		array2IndexCounter++;
			        	}
			        	
			        	else if (array2.length == array2IndexCounter) {
			        		valueToWrite = array1[array1IndexCounter];
			        		array1IndexCounter++;
			        	}
			        	else if (array1[array1IndexCounter] < array2[array2IndexCounter]) {
			        		valueToWrite = array1[array1IndexCounter];
			        		array1IndexCounter++;
			        	}
			        	
			        	else {
			        		valueToWrite = array2[array2IndexCounter];
			        		array2IndexCounter++;
			        	}
			        	
			        	System.out.println("Writing to file:" + valueToWrite );
			        	outputFile.println(valueToWrite);
				 }
				 
				 outputFile.close();
				
				 
				 
			}
	        catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
	        
	        	
	        try {
				Scanner readFile = new Scanner (fileName);
				
				int finalArrayCounter = 0;
				
				int[] finalArray = new int[array1.length + array2.length];
				while (readFile.hasNext()) { 
					
					finalArray[finalArrayCounter] = readFile.nextInt();					
					finalArrayCounter++;
				}
				
				System.out.println();
		        System.out.println("Final Array");
		        System.out.println("-----------------");
		        
				for (int i = 0; i < finalArray.length; i++) {
					System.out.println("final array[" + i + "] = " + finalArray[i]);
			    }
			    
				
				readFile.close();
				
				
			} 
	        catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
	        
	}

}
