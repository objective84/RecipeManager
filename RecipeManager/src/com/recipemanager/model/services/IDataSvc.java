package com.recipemanager.model.services;

import java.io.IOException;

import com.recipemanager.model.domain.RMObject;
import com.recipemanager.model.services.exceptions.IdAlreadyExistsException;

public interface IDataSvc extends IService{

	public void create(RMObject obj) throws IdAlreadyExistsException;
	public void edit(RMObject obj);
	public RMObject find(int id) throws IOException;
	public void delete(RMObject obj);
}
