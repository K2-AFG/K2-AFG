package com.example.k2_afg;

import java.util.ArrayList;

public class Pantry extends Organization{

	/**
	 * a String containing information about all the foods types a pantry holds
	 */
	private String foods1;

	/**
	 * creates a pantry with no name, address, phone number, website, description, food types, or amount of food
	 */
	public Pantry() {
		super();
		foods1 = "";
	}

	/**
	 * creates a pantry with a name, address, phone number, website, description, and food description
	 * @param name the name of the pantry
	 * @param address the address of the pantry
	 * @param phoneNum the phone number of the pantry
	 * @param website the link to the website of the pantry
	 * @param description a brief description of the pantry
	 * @param longitude the longitude of the pantry
	 * @param latitude the latitude of the pantry
	 */
	public Pantry(String name, String address, String phoneNum, String website, String description, double longitude, double latitude, String foods) {
		super(name, address, phoneNum, website, description, longitude, latitude);
		foods1 = foods;
	}

	/**
	 * gets the foods array list of the pantry
	 * @return the foods array list to get
	 */
	public String getFoods() {
		return foods1;
	}

	/**
	 * sets the foods String to this array list of food items
	 * @param foods the array list of food items to set
	 */
	public void setFoods(String foods) {
		this.foods1 = foods;
	}

}
