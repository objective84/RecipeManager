package com.recipemanager.model.services.factory;

import com.recipemanager.model.services.IService;
import com.recipemanager.model.services.ServiceType;


public abstract class Factory {
	
	
	public Factory(){
		
	}
	public abstract IService getService(ServiceType type);
	

}
