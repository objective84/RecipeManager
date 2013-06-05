package com.recipemanager.model.services.recipeservice;

import java.util.List;

import com.recipemanager.model.domain.*;
import com.recipemanager.model.services.IDataSvc;
import com.recipemanager.model.services.exceptions.RecipeNotFoundException;

public interface IRecipeSvc extends IDataSvc{

	public void addIngredient(Recipe recipe, Ingredient ingredient);
	public void removeIngredient(Recipe recipe, Ingredient ingredient);
	public List<Ingredient> getIngredients(Recipe recipe);
	public void addSide(Recipe recipe, Recipe side);
	public void removeSide(Recipe recipe, Recipe side);
	public List<Recipe> getSides(Recipe recipe);
}
