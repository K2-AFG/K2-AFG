package com.example.k2_afg;

/**
 * This class constructs a food pantry and describes various aspects of it. It is a subclass of Organization.
 */
public class Pantry extends Organization {

	/**
	 * creates a pantry with a name, address, phone number, website, description, and food description
	 *
	 * @param name        the name of the pantry
	 * @param address     the address of the pantry
	 * @param phoneNum    the phone number of the pantry
	 * @param website     the link to the website of the pantry
	 * @param description a brief description of the pantry
	 * @param longitude   the longitude of the pantry
	 * @param latitude    the latitude of the pantry
	 */
	public Pantry(String name, String address, String phoneNum, String website, String description, double longitude, double latitude) {
		super(name, address, phoneNum, website, description, longitude, latitude);
	}

	/**
	 * creates a pantry with no inputs
	 */
	public Pantry() {
		super();
	}
}
