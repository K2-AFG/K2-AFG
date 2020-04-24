
public class Shelter extends Organization{


	/**
	 * the number of vacant rooms a shelter has
	 */
	private int vacancies;
	
	/**
	 * specific requirements a user must meet to be eligible for a shelter
	 */
	private String specifications;
	
	/**
	 * Creates a new shelter with a name, address, phone number, website, description, vacancies, and shelter specifications
	 * @param name the name of the shelter
	 * @param address the address of the shelter
	 * @param phoneNum the phone number of the shelter
	 * @param website the website of the shelter
	 * @param description a brief description of the shelter
	 * @param vacancies the number of vacancies in a shelter
	 * @param specifics requirements of the shelter
	 */
	public Shelter (String name, String address, int phoneNum, String website, String description, 
			int vacancies, String specifics) {
		super(name, address, phoneNum, website, description);
		this.vacancies = vacancies;
		specifications = specifics;
	}
	
	/**
	 * Creates a shelter with no vacancies or specifications
	 */
	public Shelter () {
		super();
		vacancies = 0;
		specifications = "";
	}
	
	
	/**
	 * gets the number of vacancies this shelter has
	 * @return the number of vacancies
	 */
	public int getVacancies() {
		return vacancies;
	}
	
	/**
	 * sets the number of vacancies to the given number
	 * @param vacancies the number to set
	 */
	public void setVacancies(int vacancies) {
		this.vacancies = vacancies;
	}

	/**
	 * gets the specifications of a shelter
	 * @return the shelter specifications
	 */
	public String getSpecifications() {
		return specifications;
	}

	/**
	 * sets the specifications of the shelter to this description
	 * @param specifications the specifications to set
	 */
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

}
