import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class LBunker_p1 {

	public static void main(String[] args) {
		
	
		//intializing variables
		int firstNameCounter = 0;
		int lastNameCounter = 0;
			
		//creating scanner for input
		Scanner input = new Scanner(System.in);
			
		//asking for the first and last name for the first matrix and reading them into variables
		System.out.println("Please Enter your First Name for the first Matrix:");
		String firstName = input.next();
		System.out.println("Please Enter your Last Name for the first Matrix:");
		String lastName = input.next();
			
		//counting the names and making them into ints so the matrix knows what the dimensions are
		for(int i =0; i < firstName.length(); i++) {
			firstNameCounter++;
		}
			
		for(int i =0; i < lastName.length(); i++) {
			lastNameCounter++;
		}
			
		//initializing the rows and the columns from the names
		int numberOfRows = firstNameCounter;
		int numberOfColumns = lastNameCounter;
			
		//creating the first matrix object
		Matrix Mat1 = new Matrix(numberOfRows, numberOfColumns, 1 ,1);
			
		//calling the set up values method and setting up the matrix based on the step size and starting value
		Mat1.setUpValues();
			
		//printing the matrix and returning it in a string
		String returnString = Mat1.printMatrix();
		System.out.println(returnString);
		System.out.println();
		
		//creating and writing the first matrix to a file
		try {
			FileWriter writer = new FileWriter("LBunker_mat1.txt");
			writer.write(returnString);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		//resetting varaibles for the second matrix
		firstNameCounter = 0;
		lastNameCounter = 0;
			
		//asking for the last and first name for the second matrix and reading them into variables
		System.out.println("Please Enter your Last Name for the second Matrix:");
		lastName = input.next();
		System.out.println("Please Enter your First Name for the second Matrix:");
		firstName = input.next();
			
		//using variables to increment counters so the second matrix knows the dimensions 
		for(int i =0; i < firstName.length(); i++) {
			firstNameCounter++;
		}
			
		for(int i =0; i < lastName.length(); i++) {
			lastNameCounter++;
		}
			
			
		//initializing the rows and the columns from the names
		numberOfRows = firstNameCounter;
		numberOfColumns = lastNameCounter;
					
		//creating the second matrix object
		Matrix Mat2 = new Matrix(numberOfRows, numberOfColumns, 3 , 3);
					
		//calling the set up values method and setting up the matrix based on the step size and starting value
		Mat2.setUpValues();
					
		//printing the matrix and returning it in a string
		returnString = Mat2.printMatrix();
		System.out.println(returnString);
		System.out.println();
		
		
		//creating and writing the second matrix to a file
		try {
			FileWriter writer = new FileWriter("LBunker_mat2.txt");
			writer.write(returnString);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
					
		//resetting varaibles for the third matrix
		firstNameCounter = 0;
		lastNameCounter = 0;
			
		//asking for the last and first name for the third matrix and reading them into variables
		System.out.println("Please Enter your Last Name for the second Matrix:");
		lastName = input.next();
		System.out.println("Please Enter your First Name for the second Matrix:");
		firstName = input.next();
			
		//using variables to increment counters so the third matrix knows the dimensions 
		for(int i =0; i < firstName.length(); i++) {
			firstNameCounter++;
		}
			
		for(int i =0; i < lastName.length(); i++) {
			lastNameCounter++;
		}
			
			
		//initializing the rows and the columns from the names
		numberOfRows = firstNameCounter;
		numberOfColumns = lastNameCounter;
					
		//creating the third matrix object
		Matrix Mat3 = new Matrix(numberOfRows, numberOfColumns, 0.4 , 0.3);
					
		//calling the set up values method and setting up the matrix based on the step size and starting value
		Mat3.setUpValues();
					
		//printing the matrix and returning it in a string
		returnString = Mat3.printMatrix();
		System.out.println(returnString);
		System.out.println();
		
		
		//creating and writing the third matrix to a file
		try {
			FileWriter writer = new FileWriter("LBunker_mat3.txt");
			writer.write(returnString);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
			
			
		//creating the fourth matrix object
		Matrix Mat4 = new Matrix(6, 13, 2 , 2);
							
		//calling the set up values method and setting up the matrix based on the step size and starting value
		Mat4.setUpValues();
							
		//printing the matrix and returning it in a string
		returnString = Mat4.printMatrix();
		System.out.println(returnString);
					
		//creating and writing the fourth matrix to a file
		try {
			FileWriter writer = new FileWriter("LBunker_mat4.txt");
			writer.write(returnString);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
			
		//creating the fifth matrix object
		Matrix Mat5 = new Matrix(6, 13, 1 , -7);
									
		//calling the set up values method and setting up the matrix based on the step size and starting value
		Mat5.setUpValues();
									
		//printing the matrix and returning it in a string
		returnString = Mat5.printMatrix();
		System.out.println(returnString);
					
		
		//creating and writing the fifth matrix to a file
		try {
			FileWriter writer = new FileWriter("LBunker_mat5.txt");
			writer.write(returnString);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		//close scanner
		input.close();
	}

}

//matrix class
class Matrix{
	
	//private data fields
	private int rows;
	private int columns;
	private double stepSize;
	private double startingRowValue;
	private double[][] matrix;
		
	//first cosntructor for part 1
	public Matrix(int rows, int columns, double stepSize, double startingRowValue) {
		this.rows = rows;
		this.columns = columns;
		this.stepSize = stepSize;
		this.startingRowValue = startingRowValue;
			
			
		matrix = new double[rows][columns];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				matrix[i][j] = 0;
			}
		}
			 	
			 	
	}
	
	//second constructor for the second and third parts
	public Matrix(ArrayList<ArrayList<Double>> values)	
	{
		load(values);
    }
	
	//third constructor so that you can create a matrix and load values from the file
	public Matrix (String fileName) throws FileNotFoundException {
		this.load(fileName);
	}
	
	//load method that takes in a matrix with an arraylist and sets the values from the arraylist into a matrix
	public void load(ArrayList<ArrayList<Double>> values) {
		rows = values.size();
		columns = values.get(0).size();
		matrix = new double [rows][columns];
		for(int i = 0; i < values.size(); i++) {
			
			for (int j =0; j < values.get(i).size(); j++) {
				matrix[i][j] = values.get(i).get(j);
			}
		}	
	}
	
	//saves the matrix to a file 
	public void save (String fileName) {
		try {
			FileWriter writer = new FileWriter(fileName);
			String returnString = this.printMatrix();
			writer.write(returnString);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//method that will set up the values in the matrix with the starting row value and the step size
	public void setUpValues() {
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				matrix[i][j] = this.startingRowValue;
				Math.round(this.startingRowValue += this.stepSize);
		     }
		}
	}
	
	//printing the matrix with rounding for the one floating point matrix
	public String printMatrix(){
		String finalString = "";

		for(int i = 0; i < rows; i++){
		   for(int j = 0; j < columns; j++){
			    BigDecimal bd = new BigDecimal(matrix[i][j]);
			    bd.setScale(1,RoundingMode.HALF_UP);
		        finalString  += String.format("%.1f", bd.doubleValue());
		        finalString  += " ";
		    }
		   
		finalString += "\n";
		
		}
		
		System.out.println();
		return finalString;
	}
	
	//second load function that loads values from files and sets the matrix to those values
	public void load(String file) throws FileNotFoundException {
		
		ArrayList<ArrayList<Double>> rows = new ArrayList<ArrayList<Double>>();
		
		File inputFileName = new File(file);
		Scanner inputFile = new Scanner (inputFileName);
		
		while(inputFile.hasNext()) {
			String tempStr = inputFile.nextLine();
			String[] values = tempStr.split(" ");
			
			ArrayList<Double> row = new ArrayList<Double>();
			
			
			for(int i =0; i < values.length; i++) {
				row.add(Double.valueOf(values[i]));
				
			}
			
			rows.add(row);
		

		}
		load(rows);
		
		inputFile.close();
	
	}
	
	//adding functions that adds A and B
	public Matrix add(Matrix B) {
		Matrix matC = new Matrix(this.rows, this.columns, 1, 1);
		if (this.rows == B.rows && this.columns ==B.columns) {
			
			 for(int i = 0; i < this.rows; i++) {
                 for(int j = 0; j < this.columns; j++) {
                     double value = this.matrix[i][j] + B.matrix[i][j];
                     matC.matrix[i][j] = value;
                 }
             }
		}
		
		else {
			System.out.println("The matrices can not be added because the rows and columns for A were:  " + this.rows + " and " + this.columns +
					"\nThe rows and columns for B were:  " + B.rows + " and " + B.columns + "\nThe method will submit the original matrix to the file if they could not have been added.");
			return B;
		}
		
		return matC;
		}
	
	//multipying function that multiplies A and B
	public Matrix multiply (Matrix B) {
		Matrix matC = new Matrix(this.rows, this.columns, 1, 1);
		double index = 0;
		if (this.columns == B.rows) {
			
			 for(int i = 0; i < this.rows; i++) {
                 for(int j = 0; j < this.columns; j++) {
                	 double sums = 0;
                     for(int k = 0; k < this.rows; k++){
                             sums += this.matrix[i][k] * B.matrix[k][j];
                     }
                     index = sums;
                     matC.matrix[i][j] = index; 
                 }
			}
		}
		
		else {
			System.out.println("The matrices can not be multiplied because the rows and columns for A were:  " + this.rows + " and " + this.columns +
					"\nThe rows and columns for B were:  " + B.rows + " and " + B.columns + "\nThe method will submit the original matrix to the file if they could not have been multiplied.");
			return B;
		}
		
		return matC;
		}	
}



