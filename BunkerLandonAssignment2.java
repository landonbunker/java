/* LandonBunker, 
CS 1450,
section T and TR, 
Assignment 2, 
due: 9/10/21, 
read a file and than place the actors and names in an array and than print output for each actor and name
*/


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BunkerLandonAssignment2 {

	public static void main(String[] args) throws IOException {
		
		//initialize varaible in order to read the value for how many actors are in the file
		int NUM_ACTORS = 0;
		
		
		//initialize variable for while loop
		int arrayCounter = 0;
				
				
		// open the file and put it into a variable as well as create a scanner variable
		File inputFileName = new File("Actors.txt");
		Scanner inputFile = new Scanner (inputFileName);
		
		
		
		
		//intializng the length of the array to the int at the beginning of the file
		NUM_ACTORS = inputFile.nextInt();
		
		//creating the array of actors
		Actor [] actors = new Actor[NUM_ACTORS];
			
		//while loop to enter the values into variables
		while (inputFile.hasNext()) {
					
		// read next into variables in order to create objects
			String type = inputFile.next();
			String name = inputFile.nextLine();
			
			
			switch(type) {
			case "Hero":
				actors[arrayCounter] = new Hero(name);
			break;
			
			case "Villain" :
				actors[arrayCounter] = new Villain(name);
			break;
			
			case "Monster":
				actors[arrayCounter] = new Monster(name);
				break;
				
			case "Droid":
				actors[arrayCounter] = new Droid(name);
				break;
				
			}
			//increment arraycounter
			arrayCounter++;
		
			}
		// formatting and printing the actors type and motto
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("Actor Name\t\t Type\t\t Motto to Live by");
		System.out.println("----------------------------------------------------------------------------");
		for (int i = 0; i < actors.length; i++) {
			
			System.out.printf("%-20s\t%-20s\t%s\n",actors[i].getName() ,actors[i].getType(), actors[i].motto());
			}
		
		//creating instance of movie 
		Movie movie = new Movie();
		movie.selectCast(actors);
		movie.printMovieDetails();
	
		//close file
		inputFile.close();
	}

}

class Actor {
	private String name;
	private String type;
	
	
	// constructor 
	public Actor(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	
	// getters for name, type
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	// motto function to print the objects motto
	public String motto() {
		 return "Generic motto";
	}
	
}

//creating the villian Hero which is an extension of the actor
class Hero extends Actor {
	public Hero(String name) {
		super(name, "Hero");
	}
	
	//overiding the motto method so the object prints the specific motto
	@Override
	public String motto() {
		 return "To the rescue! KAPOW!! BAM!! POW!!";
	}
	
}

//creating the villian object which is an extension of the actor
class Villain extends Actor {
	public Villain(String name) {
		super(name, "Villain");
	}
	
	//overiding the motto method so the object prints the specific motto
	@Override
	public String motto() {
		 return "You’ll never stop me!  Haaaaaaa!";
	}
	
}
	
//creating the Monster object which is an extension of the actor
class Monster extends Actor {
	public Monster(String name) {
		super(name, "Monster");
	}
	
	//overiding the motto method so the object prints the specific motto
	@Override
	public String motto() {
		 return "RRAAAUUGGHH GRROWR!!!";
	}
	
}

//creating the Droid object which is an extension of the actor
class Droid extends Actor {
	public Droid(String name) {
		super(name, "Droid");
	}
	
	//overiding the motto method so the object prints the specific motto
	@Override
	public String motto() {
		 return "Beep Beep Bloop Boop Beep!";
	}
	
}

//create movie class
class Movie {
	// declare instance variables
	int numHeroes = 0;
	int numVillains = 0;
	Actor [] actorsInMovie;
	
	//selectcast function that will sort through the actor array and than create a new array with heroes and villains
	public void selectCast (Actor[] actors) {
		
		for (int i = 0; i < actors.length; i++ ) {
			if (actors[i] instanceof Hero){
				numHeroes++;
			}
			
			else if (actors[i] instanceof Villain){
				numVillains++;
			}
		}
		
		actorsInMovie = new Actor[numVillains + numHeroes];
			
			
		int currentHeroVillainIndex = 0;
		
		for (int i = 0; i < actors.length; i++ ) {
			if (actors[i] instanceof Hero){
				actorsInMovie[currentHeroVillainIndex] = actors[i];
				currentHeroVillainIndex++;
			}
			
			else if (actors[i] instanceof Villain){
				actorsInMovie[currentHeroVillainIndex] = actors[i];
				currentHeroVillainIndex++;
			}
			
		}
	}
	
	// print movei method that will print the heroes and villains 
	public void printMovieDetails() {
		System.out.println("\n--------------------------------------------");
		System.out.println("\tCS1450 Heroes and Villain Movie\t");
		System.out.println("--------------------------------------------");
		System.out.println("Number of Heroes:" + numHeroes);
		System.out.println("Number of Villains:" + numVillains + "\n");
		
		for (int i=0; i < actorsInMovie.length; i++) {
			System.out.println(actorsInMovie[i].getType() + "\t---\t" + actorsInMovie[i].getName());
		}
	}
}
