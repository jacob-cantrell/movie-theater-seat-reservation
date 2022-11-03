package com.cognixia.corejava.reservedseatprogram;

public class Seat {
	private String name;
	private int row;
	private int col;
	private boolean availability;
	
	/*
	 * Initializes a Seat instance with an empty name and invalid row and column
	 * Default constructor
	 */
	public Seat() {
		this.name = "";
		this.row = -1;
		this.col = -1;
		this.availability = true;
	}
	
	/**
	 * Initializes a Seat instance with an empty name and the row and column values
	 * passed into the two-parameter constructor
	 * 
	 * @param row Row to be assigned to this instance
	 * @param col Column to be assigned to this instance
	 */
	public Seat(int row, int col) {
		this.name = "";
		this.row = row;
		this.col = col;
		this.availability = true;
	}
	

	/**
	 * Initializes a Seat instance with the given name, row, and column 
	 * values passed into the four-parameter constructor
	 * 
	 * @param name Name to be assigned to this instance
	 * @param row Row to be assigned to this instance
	 * @param col Column to be assigned to this instance
	 * @param available Availability to be assigned to this instance
	 */
	public Seat(String name, int row, int col, boolean available) {
		this.name = name;
		this.row = row;
		this.col = col;
		this.availability = available;
	}
	
	/*
	 * Getter for the String name attribute
	 */
	public String getName() {
		return this.name;
	}
	
	/*
	 * Getter for the int row attribute 
	 */
	public int getRow() {
		return this.row;
	}
	
	/*
	 * Getter for the int column attribute
	 */
	public int getCol() {
		return this.col;
	}
	
	/*
	 * Getter for the boolean availability attribute
	 */
	public boolean isAvailable() {
		return this.availability;
	}
	

	/**
	 * Setter for the String name attribute
	 * 
	 * @param name Name to be assigned to this instance
	 */
	public void setName(String name) {
		this.name = name;
	}
	

	/**
	 * Setter for the int row attribute
	 * 
	 * @param row Row to be assigned to this instance
	 */
	public void setRow(int row) {
		this.row = row;
	}
	

	/**
	 * Setter for the int column attribute
	 * 
	 * @param col Column to be assigned to this instance
	 */
	public void setCol(int col) {
		this.col = col;
	}
	

	/**
	 * Setter for the boolean availability attribute
	 * 
	 * @param available Availability to be assigned to this instance
	 */
	public void setAvailability(boolean available) {
		this.availability = available;
	}
}
