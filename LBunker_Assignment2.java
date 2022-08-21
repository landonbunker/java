import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LBunker_Assignment2 {

	public static void main(String[] args) throws IOException {
		//game 1 from input file 1
		File inputFileName = new File("p2-1.txt");
		Scanner inputFile = new Scanner (inputFileName);
		
		//reading in the size and history of the file
		int size = readSize(inputFile);
		int history = readHistory(inputFile);
		inputFile.nextLine();
		
		//creating the game board
		char [][] gameBoard = createGameBoard(size);
		
		//creating a dynamic list of plays and the history
		ArrayList <Line> plays = acceptingInputFromFile(inputFile);
		ArrayList <Line> historyArray = new ArrayList<Line>();
		
		//conrol variable that runs the game
		control(gameBoard, plays, historyArray, history, "P1-1results");
		
		
		//game 2 from input file 2
		inputFileName = new File("p2-2.txt");
		inputFile = new Scanner (inputFileName);
				
		//reading in the size and history of the file
		size = readSize(inputFile);
		history = readHistory(inputFile);
		inputFile.nextLine();
				
		//creating the game board
		gameBoard = createGameBoard(size);
				
		//creating a dynamic list of plays and the history
		plays = acceptingInputFromFile(inputFile);
		historyArray = new ArrayList<Line>();
				
		//conrol variable that runs the game
		control(gameBoard, plays, historyArray, history, "P1-2results");
		
		
		//game 3 from input file 3
		inputFileName = new File("p2-3.txt");
		inputFile = new Scanner (inputFileName);
						
		//reading in the size and history of the file
		size = readSize(inputFile);
		history = readHistory(inputFile);
		inputFile.nextLine();
					
		//creating the game board
		gameBoard = createGameBoard(size);
						
		//creating a dynamic list of plays and the history
		plays = acceptingInputFromFile(inputFile);
		historyArray = new ArrayList<Line>();
						
		//conrol variable that runs the game
		control(gameBoard, plays, historyArray, history, "P1-3results");
		
		
		//game 4 from input file 4
		inputFileName = new File("p2-4.txt");
		inputFile = new Scanner (inputFileName);
						
		//reading in the size and history of the file
		size = readSize(inputFile);
		history = readHistory(inputFile);
		inputFile.nextLine();
					
		//creating the game board
		gameBoard = createGameBoard(size);
						
		//creating a dynamic list of plays and the history
		plays = acceptingInputFromFile(inputFile);
		historyArray = new ArrayList<Line>();
						
		//conrol variable that runs the game
		control(gameBoard, plays, historyArray, history, "P1-4results");
		
		
		//game 5 from input file 5
		inputFileName = new File("p2-5.txt");
		inputFile = new Scanner (inputFileName);
						
		//reading in the size and history of the file
		size = readSize(inputFile);
		history = readHistory(inputFile);
		inputFile.nextLine();
					
		//creating the game board
		gameBoard = createGameBoard(size);
						
		//creating a dynamic list of plays and the history
		plays = acceptingInputFromFile(inputFile);
		historyArray = new ArrayList<Line>();
						
		//conrol variable that runs the game
		control(gameBoard, plays, historyArray, history, "P1-5results");
		
	}
	//control method to play the game
	public static void control(char [][] gameBoard, ArrayList<Line> plays, ArrayList <Line> history, int K, String fileName) throws IOException {
		
		//initializing variables
		int xScore = 0;
		int oScore = 0;
		int turn = 1;
		int [] score = new int [2];
		boolean validTurn = true;
		boolean invalidTurnPlayerX = false;
		boolean invalidTurnPlayerO = false;
		boolean fullBoard = false;
		K = K*2;
		FileWriter writer = new FileWriter(fileName);
		String printString;
		
		//iterate over the list of plays
		for(int i=0; i< plays.size(); i++) {
			//getting one play from the array list
			Line play = plays.get(i);
			
			
			//validate the turn
			validTurn = validPlay(history, play);
			
			//if the turn was valid update the game baord and display the score and print to file
			if (validTurn == true) {
				updateBoard(gameBoard, play);
				score = displayScore(gameBoard, turn, writer);
				printString = printGameBoard(gameBoard);
				writer.write(printString);
			}
			
			//if turn wasnt valid give reason for invalidity and say which player was invalid
			else if (validTurn == false) {
				System.out.println("Player " + play.getPlayer() + "'s turn was invalid, it is now turn #" + turn + "\n");
				writer.write("Player " + play.getPlayer() + "'s turn was invalid, it is now turn #" + turn + "\n");
				if (play.getPlayer() == 'X') {
					invalidTurnPlayerX = true;
				}
				
				else if (play.getPlayer() == 'O') {
					invalidTurnPlayerO = true;
				}
			}
			
			//checking if both players were invalid
			if(invalidTurnPlayerO == true && invalidTurnPlayerX == true) {
				i = plays.size();
				System.out.println("Both players have committed invalid turns thus ending the game: \nGAME OVER");
				writer.write("Both players have committed invalid turns thus ending the game: \nGAME OVER");
			}
			
			//checking to see if board is full
			fullBoard = checkForFullBoard(gameBoard);
			
			if (fullBoard == true) {
				i = plays.size();
				System.out.println("The game Board is full thus ending the game: \nGAME OVER");
				writer.write("The game Board is full thus ending the game: \\nGAME OVER");
			}
			
			turn++;
			
			//add the current play to history
			history.add(play);
			
			//removing plays from history
			if (K != 0 ) {
				while(history.size() >= K) {
					history.remove(0);
				}
			}
			
		}
		
		//writing final score to file
		System.out.println("\nX Score: " + score[0] + "   O Score: " + score[1]);
		writer.write("\nX Score: " + score[0] + "   O Score: " + score[1]);
		writer.close();
	}
	
	//read in size as a int
	public static int readSize(Scanner inputFile) {
		int size = inputFile.nextInt();
		return size;
	}
	
	//read in history as a int
	public static int readHistory(Scanner inputFile) {
		int history = inputFile.nextInt();
		return history;
	}
	
	//create the game baord and initialize all spots to -
	public static char[][] createGameBoard(int n){
		char[][] gameBoard  = new char [n][n];
		
		for(int i = 0; i < gameBoard.length; i++){
			for(int j = 0; j < gameBoard.length; j++){
				gameBoard[i][j] = '-';
			}
		}
		
		return gameBoard;
	}
	
	//print each space on the game board
	public static String printGameBoard(char [][] gameBoard) {
		String finalString = "";

		for(int i = 0; i < gameBoard.length; i++){
		   for(int j = 0; j < gameBoard.length; j++){
			    char temp = gameBoard[i][j];
		        finalString  += temp;
		        finalString  += " ";
		    }
		   
		finalString += "\n";
		
		}

		System.out.println(finalString);
		return finalString;
	}
	
	//recieve input from the file and intialize the arraylist of lines
	public static ArrayList<Line> acceptingInputFromFile(Scanner inputFile) throws FileNotFoundException {
		int loopCounter = 0;
		
		ArrayList<Line> plays = new ArrayList <Line>();
	
		while(inputFile.hasNext()) {
			char player = 'O';
			
			String play = inputFile.nextLine();
			if(loopCounter % 2 == 0) {
				player = 'X';
			}
			
			Line line = new Line(play,player);
			plays.add(line);
			
			loopCounter++;
		}
		return plays;
		
	}
	
	//update the board and calculate the distance from each point to the line
	public static void updateBoard(char [][] gameBoard, Line play) {
		
		for(int i = 1; i <= gameBoard.length; i++) {
			for(int j = 1; j <= gameBoard.length; j++) { 
				float distance = play.getDistance(i, j);
				distance = Math.abs(distance);
				if(distance < .5) {
					gameBoard[mapRowToArrayIndex(i, gameBoard.length)][mapColumnToArrayIndex(j)] = play.getPlayer();
					
				}
			}
		}
	}
	
	//testing for each plays validity and if lines were copied or perpendicular
	public static boolean validPlay(ArrayList <Line> history, Line play) {
		boolean returnValue = true;
		for(int i = 0; i < history.size(); i++) {
			Line test = history.get(i);
			float slopeY1 = test.getEndColumn() - test.getStartColumn();
			float slopeX1 = test.getEndRow() - test.getStartRow();
			float slopeY2 = play.getEndColumn() - play.getStartColumn();
			float slopeX2 = play.getEndRow() - play.getStartRow();
			float slopeLine1 = slopeY1/slopeX1;
			float slopeLine2 = slopeY2/slopeX2;
			
			if(test.getEndColumn() == play.getEndColumn() && test.getEndRow() == play.getEndRow()) {
				returnValue = false;
				
			}
			
			else if(test.getStartColumn() == play.getStartColumn() && test.getStartRow() == play.getStartRow()) {
				returnValue = false;
				
			}
			
			else if(test.getEndColumn() == play.getStartColumn() && test.getEndRow() == play.getStartRow()) {
				returnValue = false;
				
			}
			
			else if(test.getStartColumn() == play.getEndColumn() && test.getStartRow() == play.getEndRow()) {
				returnValue = false;
				
			}
			
			else if (slopeLine1 == -slopeLine2) {
				returnValue = false;
				
			}
			
			
		}
		
		return returnValue;
		
		
	}
	
	//checking to see if there are empty spaces
	public static boolean checkForFullBoard(char [][] gameBoard) {
		boolean returnValue = true;
		for(int i = 0; i < gameBoard.length; i++) {
			for(int j = 0; j < gameBoard.length; j++) {
				if (gameBoard[i][j] == '-') {
					returnValue = false;
				}
			}
		}
		return returnValue;
	}
	
	//changing the mapping of the row to index
	public static int mapRowToArrayIndex(int row, int size) {
		 return size - row;
	}
	
	//changing the mapping of the column to index
	public static int mapColumnToArrayIndex(int column) {
		return column - 1;
	}
	
	//diplay the current score
	public static int [] displayScore(char [][] gameBoard, int turnCounter, FileWriter writer) throws IOException {
		int xCounter =0;
		int oCounter = 0;
		for(int i = 0; i < gameBoard.length; i++){
			for(int j = 0; j < gameBoard.length; j++){
				char temp = gameBoard[i][j];
				if (temp == 'X') {
					xCounter++;
				}
				
				if (temp == 'O') {
					oCounter++;
				}
			}
		}
		
		System.out.println("Turn " + turnCounter + ", Score: X = " + xCounter + ", O = " + oCounter);
		writer.write("Turn " + turnCounter + ", Score: X = " + xCounter + ", O = " + oCounter + "\n");
		int [] score = new int[2];
		score[0] = xCounter;
		score[1] = oCounter;
		return score;
	}


}

//line class
class Line {
	private int startRow;
	private int startColumn;
	private int endRow;
	private int endColumn;
	private char player;
	
	public Line(String play, char player) {
		String [] playStr = play.split(" ");
		this.startRow = Integer.parseInt(playStr[0]);
		this.startColumn = Integer.parseInt(playStr[1]);
		this.endRow = Integer.parseInt(playStr[2]);
		this.endColumn = Integer.parseInt(playStr[3]);
		this.player = player;
	}
	
	public int getStartColumn() {
		return this.startColumn;
	}
	
	public int getEndColumn() {
		return this.endColumn;
	}
	public int getStartRow() {
		return this.startRow;
	}
	public int getEndRow() {
		return this.endRow;
	}
	
	public float getDistance(int xValue, int yValue) {
		//calculate the distance with the implicit form of a line
		int v2 = this.getEndColumn() - this.getStartColumn();
		int v1 = this.getEndRow() - this.getStartRow();
				
		int a1 = -v2;
		int a2 = v1; 
				
		float c = (-a1*this.getStartRow()) - (a2*this.getStartColumn());
		
		float distance =(float) ((a1*xValue + a2*yValue + c)/(Math.sqrt((a1*a1) + (a2*a2))));
		return distance;
	}
	
	public char getPlayer() {
		return this.player;
	}
}
