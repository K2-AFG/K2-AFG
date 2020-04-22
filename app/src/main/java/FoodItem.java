
public class FoodItem {
	
	/**
	 * The name of the type of food this FoodItem tracks
	 */
	private String type;
	
	/**
	 * The amount of this type of food there is in single units or pounds
	 */
	private double amount;

	/**
	 * Creates a FoodItem with a given type and amount. 
	 * @param type the type of food given
	 * @param amount the amount of food this FoodItem contains 
	 */
	public FoodItem (String type, double amount) {
		this.type = type;
		this.amount = amount;
	}

	/**
	 * gets the type of food this food item contains
	 * @return the type of food this item is
	 */
	public String getType() {
		return type;
	}

	/**
	 * sets the type of food this item is 
	 * @param type the type of food to be set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * gets the amount of this food item there is 
	 * @return the amount of food in this food item
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * sets the amount of food to this amount
	 * @param amount the amount of food to be set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	/**
	 * changes the food item into a String with the type of food and the amount of the food
	 */
	public String toString() {
		return "FoodItem [type=" + type + ", amount=" + amount + "]";
	}
	
	
}
