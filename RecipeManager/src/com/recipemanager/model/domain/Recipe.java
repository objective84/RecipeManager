package com.recipemanager.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.lang.Integer;

public class Recipe extends RMObject implements Serializable{
	private int id;
	private int userId;
	private String name;
	private int servings;
	private int caloriesPerServing;
	private boolean isSideDish;
	private List<Ingredient> ingredients;
	private String instructions;
	private List<Integer> sides;
	
	public Recipe(){
		
	}
	
	public Recipe(int id, int userId, String name, int servings, int calories, 
			boolean sideDish, List<Ingredient> ingredients, String instructions, List<Integer> sides){
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.servings = servings;
		this.caloriesPerServing = calories;
		this.isSideDish = sideDish;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.sides = sides;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public int getCaloriesPerServing() {
		return caloriesPerServing;
	}

	public void setCaloriesPerServing(int caloriesPerServing) {
		this.caloriesPerServing = caloriesPerServing;
	}
	
	public boolean isSideDish() {
		return isSideDish;
	}

	public void setSideDish(boolean isSideDish) {
		this.isSideDish = isSideDish;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void addIngredient(Ingredient add){
		ingredients.add(add);
	}
	
	public void removeIngredient(Ingredient remove){
		ingredients.remove(remove);
	}
	
	
	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public List<Integer> getSides() {
		return sides;
	}

	public void setSides(List<Integer> sides) {
		this.sides = sides;
	}
	
	public void addSide(Recipe side){
		try{
			sides.add(side.getId());
		}catch(NullPointerException ex){
			sides = new ArrayList<Integer>();
			sides.add(side.getId());
		}
	}
	
	public void removeSide(Recipe side){
		for(int i = 0; i < sides.size(); i++){
			if(sides.get(i) == side.id){
				sides.remove(i);
			}
		}
	}

	public boolean validate(){
		if(id == 0)return false;
		if(userId == 0)return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + caloriesPerServing;
		result = prime * result + id;
		result = prime * result
				+ ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result
				+ ((instructions == null) ? 0 : instructions.hashCode());
		result = prime * result + (isSideDish ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + servings;
		result = prime * result + ((sides == null) ? 0 : sides.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (caloriesPerServing != other.caloriesPerServing)
			return false;
		if (id != other.id)
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (instructions == null) {
			if (other.instructions != null)
				return false;
		} else if (!instructions.equals(other.instructions))
			return false;
		if (isSideDish != other.isSideDish)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (servings != other.servings)
			return false;
		if (sides == null) {
			if (other.sides != null)
				return false;
		} else if (!sides.equals(other.sides))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}
