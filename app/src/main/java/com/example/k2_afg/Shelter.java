package com.example.k2_afg;

/**
 * This class constructs a homeless shelter and describes various aspects of it. It is a subclass of organization.
 */
public class Shelter extends Organization implements Comparable{

	/**
	 * the number of vacant rooms a shelter has
	 */
	private String vacancies1;

	/**
	 * Creates a new shelter with a name, address, phone number, website, description, longitude, latitude, vacancies, and shelter specifications
	 *
	 * @param name        the name of the shelter
	 * @param address     the address of the shelter
	 * @param phoneNum    the phone number of the shelter
	 * @param website     the website of the shelter
	 * @param description a brief description of the shelter
	 * @param longitude   the longitude that the shelter is located
	 * @param latitude    the latitude that the shelter is located
	 * @param vacancies   the number of vacancies in a shelter
	 */
	public Shelter(String name, String address, String phoneNum, String website, String description,
				   double latitude, double longitude,
				   String vacancies) {
		super(name, address, phoneNum, website, description, latitude, longitude);
		vacancies1 = vacancies;
	}

	/**
	 * Creates a shelter with no vacancies or specifications
	 */
	public Shelter() {
		super();
		vacancies1 = "";
	}

	/**
	 * gets the number of vacancies this shelter has
	 * @return the number of vacancies
	 */
	public String getVacancies() { return vacancies1; }

	/**
	 * sets the number of vacancies to the given number
	 * @param vacancies the number to set
	 */
	public void setVacancies(String vacancies) {
		this.vacancies1 = vacancies;
	}

	/**
	 *
	 * @param s the Object (shelter) whose vacancy numbers will be compared to others in the list
	 * @return the difference in number of vacancies between two shelter objects
	 */
	@Override
	public int compareTo(Object s) {
		if(((Shelter)s).getVacancies().equals("")){
			((Shelter)s).setVacancies("0");
		}
		if(this.vacancies1.equals("")){
			this.setVacancies("0");
		}
		String v = ((Shelter)s).getVacancies();
		return Integer.valueOf(v) - Integer.valueOf(this.vacancies1);
	}
}
