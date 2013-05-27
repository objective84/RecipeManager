package com.recipemanager.model.services;

import com.recipemanager.model.domain.RMObject;

public interface IDataSvc extends IService{

	public void create(RMObject obj);
	public void edit(RMObject obj);
	public RMObject find(int id);
	public void delete(RMObject obj);
}
