
public class Pantry extends Organization {

	/**
	 * A string containing all the types of food a shelter currently has
	 */
	private String foodType;
	
	/**
	 * the amount food a pantry has
	 */
	private int numFoods;
	
	/**
	 * creates a pantry with no name, address, phone number, website, description, food types, or amount of food
	 */
	public Pantry() {
		super();
		foodType = "";
		numFoods = 0;
	}
	
	/**
	 * creates a pantry with a name, address, phone number, website, description, food types, and amount of food
	 * @param name the name of the pantry
	 * @param address the address of the pantry
	 * @param phoneNum the phone number of the pantry
	 * @param website the link to the website of the pantry
	 * @param description a brief description of the pantry
	 * @param numFoods the amount of food a pantry has
	 * @param foodType the types of food a pantry has
	 */
	public Pantry (String name, String address, int phoneNum, String website, String description, 
			int numFoods, String foodType) {
		super(name, address, phoneNum, website, description);
		this.numFoods = numFoods;
		this.foodType = foodType;
	}

	/**
	 * gets the types of food a pantry holds
	 * @return the types of food a pantry holds
	 */
	public String getFoodType() {
		return foodType;
	}

	/**
	 * sets the types of food a pantry has to the these types of food
	 * @param foodType the types of food to set
	 */
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	/**
	 * gets the amount of food a pantry has
	 * @return the amount of food a pantry has
	 */
	public int getNumFoods() {
		return numFoods;
	}

	/**
	 * sets the amount of food to this amount
	 * @param numFoods the amount of food to be set
	 */
	public void setNumFoods(int numFoods) {
		this.numFoods = numFoods;
	}
	
}
