import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LBunker_p3 {

	public static void main(String[] args) throws FileNotFoundException {
		
		// string array of input files 
		String [] inputFiles = {"Lbunker_mat1.txt","Lbunker_mat2.txt","Lbunker_mat3.txt","Lbunker_mat4.txt","Lbunker_mat5.txt"};
		
		//calling the test function
		test(inputFiles);
		
	}
	
	//test function that creates two matrices from the input files and then tests every combination for addition
	private static void test(String [] inputFiles) throws FileNotFoundException {
		for(int i =0; i < inputFiles.length; i++) {
			
			Matrix matA = new Matrix(inputFiles[i]);
			
			for(int j = 0; j < inputFiles.length; j++) {
				Matrix matB = new Matrix(inputFiles[j]);
				
				Matrix matAB = matA.multiply(matB);
				
				matAB.save("Lbunker_P3_out" + (i+1) + (j+1) + ".txt");
				
			}
		}
		
	}

}
