package com.recipemanager.model.services.factory;

import com.recipemanager.model.services.IService;
import com.recipemanager.model.services.ServiceType;
import com.recipemanager.model.services.recipeservice.RecipeSvcSerializeImpl;


public class SerializeFactory extends Factory {

	@Override
	public IService getService(ServiceType type) {
		switch(type){
		case IRecipeService:
			return new RecipeSvcSerializeImpl();
		default:
			return null;
		}
	}
}
