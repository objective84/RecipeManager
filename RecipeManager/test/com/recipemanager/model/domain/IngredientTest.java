package com.recipemanager.model.domain;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.recipemanager.model.domain.Ingredient;
import com.recipemanager.model.domain.Measurements;


public class IngredientTest {

	private Ingredient testIngredient;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testIngredient = new Ingredient(.5, Measurements.tsp, "Sugar");
	}

	@After
	public void tearDown() throws Exception {
		testIngredient = null;
	}
	
	@Test
	public void testEqual(){
		Ingredient ingredient = new Ingredient(.5, Measurements.tsp, "Sugar");
		Assert.assertTrue(testIngredient.equals(ingredient));
	}
	
	@Test
	public void testNotEqual(){
		Ingredient ingredient = new Ingredient(1, Measurements.tsp, "Sugar");
		Assert.assertFalse(testIngredient.equals(ingredient));
	}
	

}
