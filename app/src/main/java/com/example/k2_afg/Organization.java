package com.example.k2_afg;

public class Organization {

	/**
	 * String value that holds the name of the organization
	 */
	private String name;

	/**
	 * String value that holds the address of the organization
	 */
	private String address;

	/**
	 * int value that holds the phone number of the organization
	 */
	private int phoneNum;

	/**
	 * String value that holds the link to the organization's website 
	 */
	private String website;

	/**
	 * String value that holds the description of the organization
	 */
	private String description;

	/**
	 * double value that holds the longitude of the location of the organization
	 */
	private double longitude;

	/**
	 * double value that holds the latitude of the location of the organization
	 */
	private double latitude;

	/**
	 * Creates an organization with no name, address, website, description, or phone number
	 */
	public Organization() {
		name = "";
		address ="";
		website = "";
		description = "";
		phoneNum = 0;
	}

	/**
	 * Creates an organization with a name, but no address, website, phone number, description, longitude, or latitude
	 * @param name the name of the organization
	 */
	public Organization(String name) {
		this.name = name;
		address ="";
		website = "";
		description = "";
		phoneNum = 0;
		longitude = 0;
		latitude = 0;
	}

	/**
	 * Creates a new organization with a given name, address, phone number, website, and description
	 * @param name the name of the organization
	 * @param address the address where the organization is located
	 * @param phoneNum the phone number of the organization
	 * @param website the link to the website of the organization
	 * @param description a brief description about the organization
	 * @param longitude the longitude where the organization is located
	 * @param latitude the latitude where the organization is located
	 */
	public Organization (String name, String address, int phoneNum,
						 String website, String description,
						 double longitude, double latitude) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.website = website;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	/**
	 * gets the name of this organization
	 * @return the name of this organization
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the name of the organization to this name
	 * @param name the name given to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gets the address of this organization
	 * @return the address of this organization
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * sets the address of the organization to this address
	 * @param address the address given to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * gets the phone number of this organization
	 * @return the phone number of the organization as a int
	 */
	public int getPhoneNum() {
		return phoneNum;
	}

	/**
	 * sets the phone number of the organization to this
	 * @param phoneNum the given phone number to set
	 */
	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * gets the website of this organization
	 * @return the website of the organization
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * sets the website of the organization to this
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * gets the description of this organization
	 * @return the description of the organization
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * sets the description of the organization to this
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * gets the longitude of this organization
	 * @return the longitude of the organization
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * sets the longitude of the organization
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Gets the latitude of the organization
	 * @return the latitude of the organization
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * sets the latitude of the organization to this value
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}



}
