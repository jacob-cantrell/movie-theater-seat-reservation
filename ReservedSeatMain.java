package com.cognixia.corejava.reservedseatprogram;

import java.util.Scanner;

/**
 * @author jacantrell
 *
 */
public class ReservedSeatMain {

	private static Seat[][] seats; //seat chart
	
	public static void main(String[] args) {
		seats = new Seat[5][5];
		initializeSeats(); //Initialize all Seats to empty available objects
		
		displaySeats(); //displays all seats and their availability
		Scanner scnr = new Scanner(System.in);
		
		mainMenu(scnr);
		
		scnr.close(); //Close scanner
	}
	

	/**
	 * Method to store information given by the user to the 
	 * Scanner passed in as a parameter
	 * (i.e., row, col, and name)
	 * 
	 * @param scnr
	 */
	private static void reserveSeat(Scanner scnr) {
		boolean finishedReserving = false; //variable to determine whether user is done reserving or not
		int row = -1;
		int col = -1;
		String name = "";
		
		//While still reserving seats
		while(!finishedReserving) {
			displaySeats();
			//Prompt user for which seat they would like to reserve
			System.out.println("Which seat would you like to reserve?");
			while(true) {
				System.out.println("Row:");
				row = scnr.nextInt();
			
				//Check if row is valid input
				if ( (row > 0) && (row <= 5) )
					break;
			}
			
			//Prompt for column
			while(true) {
				System.out.println("Col:");
				col = scnr.nextInt();
			
				//Check if column is valid input
				if ( (col > 0) && (col <= 5) )
					break;
			}
			
			//Prompt for name
			while(true) {
				System.out.println("Name of person sitting here:");
				name = scnr.next().trim();
			
				//Check if name is valid
				if ( name != null )
					break;
			}
			
			
			//Check if seat is reserved
			if ( !seats[row - 1][col - 1].isAvailable() ) {
				System.out.println("-- SEAT IS RESERVED --\n");
			} else {
				//Reserve the seat
				seats[row - 1][col - 1].setAvailability(false);
				seats[row - 1][col - 1].setName(name);
			}
			
			
			//Prompt if would like to keep reserving seats
			boolean done = false;
			displaySeats();
			while (!done) {
				System.out.println("Are you finished reserving seats? (Y/N)");
				String finished = scnr.next().trim().toLowerCase();
				if (finished.equals("y")) {
					finishedReserving = true;
					System.out.println("\nThank you for reserving seats with us today!\n");
					displaySeats();
					done = true;
				}
				else if (finished.equals("n"))
					done = true;
			}
		}
	}
	
	/*
	 * Method to display the seat information (just whether or not the seat
	 * is reserved) to the console
	 */
	private static void displaySeats() {
		System.out.println("=============");
		System.out.println("SEATS");
		System.out.println("=============\n");
		System.out.println("    1 2 3 4 5");
		System.out.println("   ----------");
		for (int i = 0; i < seats.length; i++) {
			System.out.print( (i + 1) + " | ");
			for (int j = 0; j < seats[i].length; j++) {

				if(seats[i][j].isAvailable()) {
					if (j == (seats[i].length -1) )
						System.out.print("o\n");
					else
						System.out.print("o ");
				} else {
					if (j == (seats[i].length -1) )
						System.out.print("x\n");
					else
						System.out.print("x ");
				}
			
			}
		}
		
		System.out.println();
	}
	
	/**
	 * Initializes all of the seats with their row and column numbers
	 */
	private static void initializeSeats() {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				seats[i][j] = new Seat(i + 1, j + 1);
			}
		}
	}
	
	
	/**
	 * Clears ALL reservations
	 */
	public static void clearReservations() {
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				seats[i][j].setAvailability(false);
				seats[i][j].setName("");
			}
		}
	}
	
	
	/**
	 * Takes user input given by the Scanner parameter (row and column)
	 * and checks whether or not their is a reservation their. If there 
	 * is, then the reservation is deleted, if not, the method is recursively 
	 * called until a valid reservation is given.
	 * 
	 * @param scnr
	 */
	public static void deleteReservation(Scanner scnr) {
		int row = -1;
		int col = -1;
		
		System.out.println("\nYou chose to delete a reservation!");
		System.out.println("What seat is your reservation located at?");
		
		while(true) {
			System.out.println("Row:");
			row = scnr.nextInt();
		
			//Check if row is valid input
			if ( (row > 0) && (row <= 5) )
				break;
		}
		
		//Prompt for column
		while(true) {
			System.out.println("Col:");
			col = scnr.nextInt();
		
			//Check if column is valid input
			if ( (col > 0) && (col <= 5) )
				break;
		}
		
		//Check if seat is reserved
		if ( seats[row - 1][col - 1].isAvailable() ) {
			System.out.println("-- SEAT IS NOT RESERVED --\n");
			deleteReservation(scnr);
		} else {
			//Un-reserve the seat
			seats[row - 1][col - 1].setAvailability(true);
			seats[row - 1][col - 1].setName("");
		}
		
	}
	

	/**
	 * Moves the reservation from the old seat (row and column) given by user
	 * input to the Scanner to a new seat (row and column) given by the user
	 * via the Scanner. If no reservation exists in the old seat, then the method
	 * is recursively called until one is. If a reservation exists in the new seat,
	 * then the method is also called again until one is.
	 * 
	 * @param scnr
	 */
	public static void moveReservation(Scanner scnr) {
		int oldRow = -1, newRow = -1;
		int oldCol = -1, newCol = -1;
		
		System.out.println("\nYou chose to move your reservation!");
		System.out.println("What seat is your current reservation located at?");
		
		while(true) {
			System.out.println("Row:");
			oldRow = scnr.nextInt();
		
			//Check if row is valid input
			if ( (oldRow > 0) && (oldRow <= 5) )
				break;
		}
		
		//Prompt for column
		while(true) {
			System.out.println("Col:");
			oldCol = scnr.nextInt();
		
			//Check if column is valid input
			if ( (oldCol > 0) && (oldCol <= 5) )
				break;
		}
		
		//Check if seat is reserved
		if ( seats[oldRow - 1][oldCol - 1].isAvailable() ) {
			System.out.println("-- SEAT IS NOT RESERVED --\n");
			moveReservation(scnr);
		} 
		
		displaySeats();
		System.out.println("What seat would you like to move it to?");
		
		while(true) {
			System.out.println("Row:");
			newRow = scnr.nextInt();
		
			//Check if row is valid input
			if ( (newRow > 0) && (newRow <= 5) )
				break;
		}
		
		//Prompt for column
		while(true) {
			System.out.println("Col:");
			newCol = scnr.nextInt();
		
			//Check if column is valid input
			if ( (newCol > 0) && (newCol <= 5) )
				break;
		}
		
		//Check if seat is reserved
		if ( !seats[newRow - 1][newCol - 1].isAvailable() ) {
			System.out.println("-- SEAT IS RESERVED --\n");
			moveReservation(scnr);
		} else {
			seats[newRow - 1][newCol - 1].setName(seats[oldRow - 1][oldCol - 1].getName());
			seats[newRow - 1][newCol - 1].setAvailability(false);
			seats[oldRow - 1][oldCol - 1].setAvailability(true);
			seats[oldRow - 1][oldCol - 1].setName("");
			
			displaySeats();
		}
	}
	
	
	/**
	 * Displays the seat information (name) of a seat with a row and column
	 * number given by the user via input to the paramater Scanner
	 * 
	 * @param scnr
	 */
	public static void getSeatInfo(Scanner scnr) {
		int row = -1;
		int col = -1;
		
		System.out.println("\nYou chose to get information on a reservation!");
		System.out.println("What seat is your reservation located at?");
		
		while(true) {
			System.out.println("Row:");
			row = scnr.nextInt();
		
			//Check if row is valid input
			if ( (row > 0) && (row <= 5) )
				break;
		}
		
		//Prompt for column
		while(true) {
			System.out.println("Col:");
			col = scnr.nextInt();
		
			//Check if column is valid input
			if ( (col > 0) && (col <= 5) )
				break;
		}
		
		//Check if seat is reserved
		if ( seats[row - 1][col - 1].isAvailable() ) {
			System.out.println("-- SEAT IS NOT RESERVED --\n");
			deleteReservation(scnr);
		} else {
			//Un-reserve the seat
			System.out.println("\n" + seats[row - 1][col - 1].getName() + " has reserved this seat.\n");
		}
	}
	
	
	/**
	 * Displays all of the names with row and column numbers of all reserved
	 * seats.
	 */
	public static void getAllSeatInfo() {
		System.out.println("\nYou chose to get all information on reserved seats.\n");
		System.out.println("Seat Information:");
		
		for (int i = 0; i < seats.length; i++) {
			for (int j = 0; j < seats[i].length; j++) {
				if ( !seats[i][j].isAvailable() ) { //if reserved, print name of who reserved
					System.out.println(seats[i][j].getName() + " reserved the seat in row "
							+ seats[i][j].getRow() + " and column " 
							+ seats[i][j].getCol() + ".");
				}
			}
		}
		
		System.out.println();
	}
	
	/**
	 * Prompts the user for an option on what to do and whether
	 * they would like to continue or not
	 * 
	 * @param scnr
	 */
	public static void mainMenu(Scanner scnr) {
		//Prompt user what they would like to do
		boolean validInput = false;
		while(!validInput) {
			System.out.println("What would you like to do?");
			System.out.println("1 - Reserve an open seat.");
			System.out.println("2 - Get information on a reserved seat.");
			System.out.println("3 - Get information on ALL reserved seats.");
			System.out.println("4 - Move reservation to a different seat.");
			System.out.println("5 - Delete a reservation.");
			System.out.println("6 - Delete ALL reservations.");
			System.out.println("7 - Quit/Leave.");
			int input = scnr.nextInt();
			
			switch (input) {
			  case 1:
				 reserveSeat(scnr);
				 break;
			  case 2:
				  getSeatInfo(scnr);
				  break;
			  case 3:
				  getAllSeatInfo();
				  break;
			  case 4:
				  moveReservation(scnr);
				  break;
			  case 5:
				  deleteReservation(scnr);
				  break;
			  case 6:
				  clearReservations();
				  break;
			  case 7:
				  validInput = true;
				  break;
			  default: 
				  System.out.println("Not a valid input. Try again.");
			}
			
			System.out.println("Would you like to continue? (Y/N)");
			String next = scnr.next().trim().toLowerCase();
			if (next.equals("y"))
				continue;
			else if (next.equals("n"))
				validInput = true;
		}
	}
}
