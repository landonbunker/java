/* LandonBunker, 
CS 1150,
section T and TR, 
Assignment 11, 
due: 4/23/21, 
create race car drivers and simulate a race using objects and methods with help from arrays
*/

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class BunkerLandonAssignment11 {

	public static void main(String[] args) throws IOException {
		
		final int NUM_DRIVERS = 6;


		RaceCar[] cars = new RaceCar[NUM_DRIVERS];
		
		createRaceCars(cars);
		
		System.out.println("Race cars at start of race");
		System.out.println("---------------------------------------------------------");
		printRaceCars(cars);
		
		double minutesRaced = 0.0;
		int iteration = 1;
		boolean raceComplete = false;
		
		System.out.println("All cars are ready - let the race begin in 5..4..3..2..1");
		
		while (raceComplete == false ) {
			
			for (int i = 0; i < cars.length; i++) {
				
				double averageSpeed = cars[i].getAverageSpeed(); 
				double distanceTraveled = (averageSpeed * (15/ 60.0));
				cars[i].incrementMiles(distanceTraveled);
				
				if (cars[i].getOdometer() > 500 ) {
					raceComplete = true;
				
				}
				
			}
			
			System.out.println("Racing time ..." + minutesRaced/60.0 + "hours");
			
			minutesRaced += 15;
			iteration++;
		}
	
	
	
		int winner = findWinner(cars);
		
		System.out.println("Race cars at end of race");
		System.out.println("---------------------------------------------------------");
		printRaceCars(cars);
		System.out.println("The winner is: " + cars[winner].getDriver() + " with " + cars[winner].getOdometer() + " miles");
		
		writeCarDetailsToFile(cars);
		
	
	}
	
	
	
	public static void writeCarDetailsToFile(RaceCar[] raceCars) throws IOException{ 
		 File fileName = new File("Assignment11.txt");
		 PrintWriter resultsFile = new PrintWriter (fileName);
		 
		 resultsFile.println("Race Car Details");
		 
		 for (int i = 0; i < raceCars.length; i ++) { 
			 resultsFile.println(raceCars[i].getCarNumber());  
			 resultsFile.println(raceCars[i].getDriver());  
			 resultsFile.println(raceCars[i].getOdometer()); 
			 resultsFile.println();
			 
			 }
		 
		 resultsFile.close();
		 System.out.println("Find the file here " + fileName.getAbsolutePath());
		 System.out.println("");
		
	}	
	
	public static void createRaceCars(RaceCar[] racecars) {
		
		RaceCar car1 = new RaceCar("Shrek", 18, 77);
		RaceCar car2 = new RaceCar("Fiona", 8, 85);
		RaceCar car3 = new RaceCar("Donkey", 17, 117);
		RaceCar car4 = new RaceCar("Dragon", 42, 81);
		RaceCar car5 = new RaceCar("Farquaad", 14, 109);
		RaceCar car6 = new RaceCar("Pinocchio", 19, 75);
		
		racecars[0] = car1;
		racecars[1] = car2;
		racecars[2] = car3;
		racecars[3] = car4;
		racecars[4] = car5;
		racecars[5] = car6;
	}
	
	
	public static void printRaceCars(RaceCar[] drivers) {
		
		System.out.printf("Driver\t\tRace Car\tAverage\t\tMiles\n");
		System.out.printf("      \t\tNumber  \tSpeed  \t\tCompleted\n");
		System.out.println("---------------------------------------------------------");
		for (int i = 0; i < drivers.length; i++ ) { 
			System.out.printf(drivers[i].getDriver() + "\t\t" + drivers[i].getCarNumber() + "\t\t" + drivers[i].getAverageSpeed() + "\t\t" + drivers[i].getOdometer() + "\n");
		}
	}


public static int findWinner(RaceCar[] raceCars) { 
	int highestRankingIndex = 0;
	double largest = 0.0;
	for (int i = 0; i < raceCars.length; i++) {
		
		if (largest < raceCars[i].getOdometer()) {
			largest = raceCars[i].getOdometer();
			highestRankingIndex = i;
		}
	}
	
	return highestRankingIndex; 
}


static class RaceCar {
	private String driver;
	private int carNumber;
	private double averageSpeed;
	private Odometer odometer;
	
	public RaceCar(String driver, int carNumber, double averageSpeed) {
		this.driver = driver;
		this.carNumber = carNumber;
		this.averageSpeed = averageSpeed;
		this.odometer = new Odometer(0.0);
		
		}
	
	public String getDriver() {
		return driver;
	}
	
	public int getCarNumber() {
		return carNumber;
	}
	
	public double getAverageSpeed() {
		return averageSpeed;
	}
	
	public double getOdometer() {
		return odometer.getMiles();
	}
	public void incrementMiles(double milesTraveled) {
		odometer.incrementMiles(milesTraveled);
}
}

static class Odometer {
	private double miles = 0.0;
	
	public Odometer(double miles) {
		this.miles = miles;
	}
	
	
	public double getMiles() {
		return miles;
	}
	
		public void incrementMiles(double milesTraveled) {
			miles = milesTraveled + miles;
	}
		
	}
}