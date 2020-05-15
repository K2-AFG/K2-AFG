package com.example.k2_afg;

import java.util.ArrayList;

public class Pantry extends Organization {

	/**
	 * an array list containing information about all the foods types a pantry holds
	 */
	private ArrayList<FoodItem> foods;

	/**
	 * the number of different types of foods a pantry has
	 */
	private int variety;

	/**
	 * creates a pantry with no name, address, phone number, website, description, food types, or amount of food
	 */
	public Pantry() {
		super();
		foods = new ArrayList<FoodItem>();
		variety = 0;
	}

	/**
	 * creates a pantry with a name, address, phone number, website, description, food types, and amount of food
	 * @param name the name of the pantry
	 * @param address the address of the pantry
	 * @param phoneNum the phone number of the pantry
	 * @param website the link to the website of the pantry
	 * @param description a brief description of the pantry
	 * @param longitude the longitude of the pantry
	 * @param latitude the latitude of the pantry
	 */
	public Pantry (String name, String address, String phoneNum, String website, String description, double longitude, double latitude) {
		super(name, address, phoneNum, website, description, longitude, latitude);
		foods = new ArrayList<FoodItem>();
		variety = 0;
	}

	/**
	 * adds a new amount of a food item if it already exists in the foods array list or adds a new food item if it isn't there
	 * @param foodName the name of the food item to add an amount to
	 * @param amount the amount of a food type to add
	 */
	public void add(String foodName, double amount) {
		boolean repeated = false;
		for (int i = 0; i < foods.size(); i++) {
			if (foodName.equals(foods.get(i).getType())) {
				foods.set(i, new FoodItem(foodName, foods.get(i).getAmount() + amount));
				repeated = true;
			}
		}

		if(!repeated) {
			foods.add(new FoodItem(foodName, amount));

		}

		variety = foods.size();
	}

	/**
	 * replaces the amount of a existing food item or adds it to the end of the list if it doesn't exist
	 * @param foodName the name of the food item
	 * @param amount the new amount of the food item to replace
	 */
	public void replace(String foodName, double amount) {
		boolean repeated = false;
		for (int i = 0; i < foods.size(); i++) {
			if (foodName.equals(foods.get(i).getType())) {
				foods.set(i, new FoodItem(foodName, amount));
				repeated = true;
			}
		}

		if(!repeated) {
			foods.add(new FoodItem(foodName, amount));

		}

		variety = foods.size();
	}


	/**
	 * gets the foods array list of the pantry
	 * @return the foods array list to get
	 */
	public ArrayList<FoodItem> getFoods() {
		return foods;
	}

	/**
	 * sets the foods array list to this array list of food items
	 * @param foods the array list of food items to set
	 */
	public void setFoods(ArrayList<FoodItem> foods) {
		this.foods = foods;
	}

	/**
	 * gets the number of types of foods a pantry has
	 * @return the number of variety of foods a pantry has
	 */
	public int getVariety() {
		return variety;
	}

	/**
	 * sets the number of varieties of foods a pantry has
	 * @param variety the number of different types of foods
	 */
	public void setVariety(int variety) {
		this.variety = variety;
	}

}
