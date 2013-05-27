package com.recipemanager.model.services.userservice;

import com.recipemanager.model.domain.User;
import com.recipemanager.model.services.IDataSvc;

public interface IUserSvc extends IDataSvc {

	public User authenticate(String username, String password);
}
