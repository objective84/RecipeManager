package com.recipemanager.model.services.factory;

import com.recipemanager.model.services.ServiceType;
import com.recipemanager.model.services.factory.Factory;
import com.recipemanager.model.services.factory.SerializeFactory;
import com.recipemanager.model.services.recipeservice.RecipeSvcSerializeImpl;

import junit.framework.TestCase;

public class FactoryTests extends TestCase {

	private Factory factory;
	protected static void setUpBeforeClass() throws Exception {
	}

	protected static void tearDownAfterClass() throws Exception {
	}

	protected void setUp() throws Exception {
		super.setUp();
		factory = new SerializeFactory();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetService_Recipe() {
		RecipeSvcSerializeImpl actual = (RecipeSvcSerializeImpl) factory.getService(ServiceType.Recipe);		
		assertNotNull(actual);
	}
}
