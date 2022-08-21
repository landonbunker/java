/* LandonBunker, 
CS 1450,
section T and TR, 
Assignment 2, 
due: 9/16/21, 
read a file and than place the birds into an array and than determine whether the bird can run, swim, and fly and than create a race 
*/


import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class BunkerLandonAssignment3 {

	public static void main(String[] args) throws IOException {
				
		// open the file and put it into a variable as well as create a scanner variable
		File inputFileName = new File("Birds.txt");
		Scanner inputFile = new Scanner (inputFileName);
		
		int numberOfBirds = inputFile.nextInt();
		//creating the array list of birds
		ArrayList<Bird> birds = new ArrayList<Bird>(numberOfBirds);
		
		
			
		//while loop to enter the values into variables
		while (inputFile.hasNext()) {
					
			Bird bird = null; 
			
			// read next into variables in order to create objects
			String type = inputFile.next();
			String name = inputFile.next();
			int runSpeed = inputFile.nextInt();
			int swimSpeed = inputFile.nextInt();
			int flyAltitude = inputFile.nextInt();
			
			
			
			switch(type) {
			case "penguin":
				bird = new Penguin(name, runSpeed, swimSpeed);
			break;
			
			case "ostrich" :
				bird  = new Ostrich(name, runSpeed, flyAltitude);
			break;
			
			case "sootytern":
				bird  = new SootyTern(name, runSpeed, flyAltitude);
				break;
				
			case "loon":
				bird  = new Loon(name, swimSpeed, flyAltitude);
				break;
				
			}
			
			birds.add(bird); 
			
		
		}
		
		displayBirds(birds);
		ArrayList<Bird> swimmingBirds = findSwimmers(birds);
		swimmingRace(swimmingBirds);
		
		//close file
		inputFile.close();
	}



public static void displayBirds(ArrayList<Bird> birds) {
	
	System.out.println(" Birds and Abilities");
	System.out.println("------------------------------------------------------------------");
	
	for(int i = 0; i < birds.size(); i++) {
		
		System.out.println(birds.get(i).getName() + " is a " + birds.get(i).getType());
		System.out.println(birds.get(i).strangeFact());
		
		int swimSpeed = 0;
		int runSpeed = 0;
		int flyAltitude = 0;
		if (birds.get(i) instanceof Swimmer) {
			
			swimSpeed = ((Swimmer) birds.get(i)).swim();
		}
		if (birds.get(i) instanceof Runner) {
			runSpeed = ((Runner) birds.get(i)).run();
		}
		if (birds.get(i) instanceof Flyer) {
			flyAltitude = ((Flyer) birds.get(i)).fly();
		}
		
		System.out.println("Swim Speed: " + swimSpeed + "  Run Speed " + runSpeed + "  Fly altitude: " + flyAltitude + "\n");
	}
	
}


public static ArrayList<Bird> findSwimmers(ArrayList<Bird> birds) {
	System.out.println("------------------------------------------------------------------");
	System.out.println(" BIRDS THAT CAN SWIM");
	System.out.println("------------------------------------------------------------------");
	System.out.println("Bird\t\t Type\t\t Swimming Speed");
	System.out.println("------------------------------------------------------------------");
	
	
	ArrayList<Bird> swimmers = new ArrayList<Bird>();	
	
	for (int i = 0; i < birds.size(); i++) {
		if (birds.get(i) instanceof Swimmer) {
			swimmers.add(birds.get(i));
		}
	
	}
	
	for(int i = 0; i < swimmers.size(); i++) {
		System.out.println(swimmers.get(i).getName() + "\t\t" + swimmers.get(i).getType() + "\t\t" + ((Swimmer)swimmers.get(i)).swim());
	
	}
	return swimmers;
	}


public static Bird swimmingRace(ArrayList<Bird> swimmingBirds) {
	
	System.out.println("------------------------------------------------------------------");
	System.out.println("SWIMMING RACE");
	System.out.println("------------------------------------------------------------------");
	System.out.println("Swimming birds get ready ... get set ... go!");
	
	Bird bestSwimmer = swimmingBirds.get(0);
	for (int i =0; i < swimmingBirds.size(); i++) {
		if (((Swimmer) swimmingBirds.get(i)).swim() > (((Swimmer) bestSwimmer).swim())) {
			bestSwimmer = swimmingBirds.get(i);
		}
	}
	
	System.out.println("The winner is " + bestSwimmer.getName() + " the " + bestSwimmer.getType() + " swimming at " + (((Swimmer) bestSwimmer).swim())+ " mph");
	return bestSwimmer;
	
	}
	
	
	
}




//create runner interface
interface Runner{
	public abstract int run();
}


//create swimmer interface
interface Swimmer{
	public abstract int swim();
}

//create flyer interface
interface Flyer{
	public abstract int fly();
}



abstract class Bird  {
	private String name;
	private String type;
	
	// getters for name, type
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String setName(String name) {
		return this.name = name;
	}
	
	public String setType(String type) {
		return this.type = type;
	}
	
	abstract public String strangeFact();
	
}

//creating the penguin which is an extension of the bird
class Penguin extends Bird implements Runner, Swimmer{
	private int runSpeed;
	private int swimSpeed;
	
	
	public Penguin(String name, int runSpeed, int swimSpeed) {
		setType("Penguin");
		setName(name);
		this.runSpeed = runSpeed;
		this.swimSpeed = swimSpeed;
		
	}
	
	//overiding the strangefact method so the object prints the specific motto
	@Override
	public String strangeFact() {
		 return "I can’t fly but I’m the fastest swimmer and deepest diver and can stay underwater up to 20 minutes.";
	}
	
	@Override
	public int run() {
		return runSpeed;
	}
	
	@Override
	public int swim() {
		return swimSpeed;
	}
}

//creating the Ostrich which is an extension of the bird
class Ostrich extends Bird implements Runner, Swimmer{
	private int runSpeed;
	private int swimSpeed;

	public Ostrich(String name, int runSpeed, int swimSpeed) {
		setType("Ostrich");
		setName(name);
		this.runSpeed = runSpeed;
		this.swimSpeed = swimSpeed;
	}
	
	//overiding the strangefact method so the object prints the specific motto
	@Override
	public String strangeFact() {
		 return "Who needs flying when you’re the biggest bird on earth! I can be up to 9 feet tall and weigh up to 300 pounds – bring it on!";
	}
	
	@Override
	public int run() {
		return runSpeed;
	}
	
	@Override
	public int swim() {
		return swimSpeed;
	}
}

//creating the Sooty Tern which is an extension of the bird
class SootyTern extends Bird implements Runner, Flyer{
	private int runSpeed;
	private int flyAltitude;
	
	public SootyTern(String name, int runSpeed, int flyAltitude) {
		setType("SootyTern");
		setName(name);
		this.runSpeed = runSpeed;
		this.flyAltitude = flyAltitude;
	}
	
	//overiding the strangefact method so the object prints the specific motto
	@Override
	public String strangeFact() {
		 return "Strange as it may sound, I spend most of my life at sea and I can't swim but I can nap while flying! !";
	}
	
	@Override
	public int run() {
		return runSpeed;
	}
	@Override
	public int fly() {
		return flyAltitude;
	}
	
}

//creating the Loon which is an extension of the bird
class Loon extends Bird implements Swimmer, Flyer{

	private int swimSpeed;
	private int flyAltitude;

	public Loon(String name, int swimSpeed, int flyAltitude) {
		setType("Loon");
		setName(name);
		this.swimSpeed = swimSpeed;
		this.flyAltitude = flyAltitude;
	}
	
	//overiding the strangefact method so the object prints the specific motto
	@Override
	public String strangeFact() {
		 return "My legs are so far back on my body that I cannot walk on land, so I push myself along the ground on my chest.";
	}
	
	@Override
	public int swim() {
		return swimSpeed;
	}
	
	@Override
	public int fly() {
		return flyAltitude;
	}
	
	
}
