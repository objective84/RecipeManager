/**
 * 
 */
package com.recipemanager.model.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.recipemanager.model.domain.Ingredient;
import com.recipemanager.model.domain.Measurements;
import com.recipemanager.model.domain.Recipe;


/**
 * @author Peter's Desktop
 *
 */
public class RecipeTest {
	private Recipe testRecipe;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		List list = new ArrayList<Ingredient>();
		list.add(new Ingredient(.5, Measurements.tsp, "Sugar"));
		list.add(new Ingredient(1, Measurements.c, "Flour"));
		list.add(new Ingredient(.5, Measurements.tsp, "Salt"));		
		testRecipe = new Recipe(1, 1, "Test Recipe", 1, 100, false, list, "", null);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqual() {

		List list = new ArrayList<Ingredient>();
		list.add(new Ingredient(.5, Measurements.tsp, "Sugar"));
		list.add(new Ingredient(1, Measurements.c, "Flour"));
		list.add(new Ingredient(.5, Measurements.tsp, "Salt"));		
		Recipe recipe = new Recipe(1, 1, "Test Recipe", 1, 100, false, list, "", null);
		Assert.assertTrue(testRecipe.equals(recipe));
	}
	
	@Test
	public void testNotEqual() {

		List list = new ArrayList<Ingredient>();
		list.add(new Ingredient(.5, Measurements.tsp, "Sugar"));
		list.add(new Ingredient(1, Measurements.c, "Flour"));
		list.add(new Ingredient(1, Measurements.tsp, "Salt"));		
		Recipe recipe = new Recipe(1, 1, "Test Recipe", 1, 100, false, list, "", null);
		Assert.assertFalse(testRecipe.equals(recipe));
	}

	@Test
	public void testValidatePass(){
		Assert.assertTrue(testRecipe.validate());
	}

	@Test
	public void testValidateFail(){
		testRecipe.setId(0);
		Assert.assertFalse(testRecipe.validate());
	}

}
