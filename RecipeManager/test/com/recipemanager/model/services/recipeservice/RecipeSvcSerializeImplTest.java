package com.recipemanager.model.services.recipeservice;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.recipemanager.model.domain.Ingredient;
import com.recipemanager.model.domain.Measurements;
import com.recipemanager.model.domain.Recipe;
import com.recipemanager.model.services.ServiceType;
import com.recipemanager.model.services.exceptions.IdAlreadyExistsException;
import com.recipemanager.model.services.exceptions.RecipeNotFoundException;
import com.recipemanager.model.services.factory.Factory;
import com.recipemanager.model.services.factory.SerializeFactory;
import com.recipemanager.model.services.recipeservice.RecipeSvcSerializeImpl;



public class RecipeSvcSerializeImplTest {
	private Factory factory;
	private RecipeSvcSerializeImpl svc;
	private Recipe testRecipe;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	

	@Before
	public void setUp() throws Exception {
		factory = new Factory();
		svc = (RecipeSvcSerializeImpl)factory.getService(ServiceType.IRecipeService);
		List list = new ArrayList<Ingredient>();
		list.add(new Ingredient(.5, Measurements.tsp, "Sugar"));
		list.add(new Ingredient(1, Measurements.c, "Flour"));
		list.add(new Ingredient(.5, Measurements.tsp, "Salt"));		
		testRecipe = new Recipe(-1, 1, "Test Recipe", 1, 100, false, list, "", null);
	}

	@After
	public void tearDown() throws Exception {
		deleteTestRecipe();
	}

	@Test
	public void testCreate() {
		try {
			svc.create(testRecipe);
		} catch (IdAlreadyExistsException e) {
			System.out.println("Recipe ID already exists");
			fail();
		}
		Recipe actual = null;
		List<Recipe> list = svc.getRecipeList();
		for(Recipe recipe : list){
			if(recipe.getId() == testRecipe.getId()) {
				actual = recipe;
			}
		assertNotNull(actual);
		}
	}
	
	@Test
	public void testFind(){
		addTestRecipe();
		Recipe actual = null;
		try {
			actual = (Recipe) svc.find(testRecipe.getId());
		} catch (RecipeNotFoundException e) {
			fail();
		}
		assertEquals(testRecipe, actual);
		
	}
	
	@Test
	public void testEdit(){
		addTestRecipe();
		Recipe expected = testRecipe;
		expected.setName("Edited Recipe");
		svc.edit(expected);
		Recipe actual = null;
		List<Recipe> list = svc.getRecipeList();
		for(Recipe recipe : list){
			if(recipe.getId() == testRecipe.getId()) {
				actual = recipe;
			}
		}
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDelete(){
		addTestRecipe();
		svc.delete(testRecipe);
		Recipe actual = null;
		List<Recipe> list = svc.getRecipeList();
		for(Recipe recipe : list){
			if(recipe.getId() == testRecipe.getId()) {
				actual = recipe;
				break;
			}
		}
		assertNull(actual);
	}
	
	@Test
	public void testAddIngredient(){
		addTestRecipe();
		Ingredient add = new Ingredient(1, Measurements.tbsp, "Olive Oil");
		svc.addIngredient(testRecipe, add);
		testRecipe.addIngredient(add);
		Recipe actual = null;

		List<Recipe> list = svc.getRecipeList();
		for(Recipe recipe : list){
			if(recipe.getId() == testRecipe.getId()) {
				actual = recipe;
				break;
			}
		}
		assertEquals(testRecipe, actual);
		
	}
	
	@Test
	public void testRemoveIngredient(){
		addTestRecipe();
		Ingredient remove = new Ingredient(.5, Measurements.tsp, "Sugar");
		svc.removeIngredient(testRecipe, remove);
		testRecipe.removeIngredient(remove);
		Recipe actual = null;
		List<Recipe> list = svc.getRecipeList();
		for(Recipe recipe : list){
			if(recipe.getId() == testRecipe.getId()) {
				actual = recipe;
				break;
			}
		}
		assertEquals(actual, testRecipe);
	}
	
	@Test
	public void testGetIngredients(){
		addTestRecipe();
		assertEquals(svc.getIngredients(testRecipe), testRecipe.getIngredients());
	}
	
	@Test
	public void testAddSide(){
		addTestRecipe();
		Recipe side = new Recipe(2, 1, "Test Side", 4, 100, true, null, "", null);
		List<Recipe> list = svc.getRecipeList();
		
		
		svc.addSide(testRecipe, side);
		testRecipe.addSide(side);
		
		int actual = 0;
		list = svc.getRecipeList();
		for(Recipe recipe : list){
			if(recipe.getId() == testRecipe.getId()) {
				actual = recipe.getSides().get(0);
				break;
			}
		}
		
		assertEquals(side.getId(), actual);
		testRecipe.setSides(null);
	}
	
	@Test
	public void testRemoveSide(){
		Recipe side = new Recipe(2, 1, "Test Side", 4, 100, true, null, "", null);
		testRecipe.addSide(side);
		addTestRecipe();
		svc.removeSide(testRecipe, side);
		
		Recipe actual = null;
		List<Recipe> list = svc.getRecipeList();
		for(Recipe recipe : list){
			if(recipe.getId() == testRecipe.getId()) {
				actual = recipe;
				break;
			}
		}
		
		assertEquals(0, actual.getSides().size());
		testRecipe.setSides(null);
	}
	
	@Test
	public void testGetSides(){
		Recipe side = new Recipe(2, 1, "Test Side", 4, 100, true, null, "", null);
		List<Recipe> list = svc.getRecipeList();
		testRecipe.addSide(side);
		list.add(side);
		try{
		      OutputStream out = new FileOutputStream(svc.getPath() + "recipes.dat");
		      OutputStream buffer = new BufferedOutputStream( out );
		      ObjectOutput output = new ObjectOutputStream( buffer );
		      try{
		        output.writeObject(list);
		      }
		      finally{
		        output.close();
		      }
		    }  
		    catch(IOException ex){
		      ex.printStackTrace();
		    }
		addTestRecipe();
		
		List<Recipe> expected = new ArrayList();
		list = svc.getRecipeList();
		for(Recipe recipe : list){
			if(recipe.getId() == side.getId()){
				expected.add(recipe);
			}
		}
		List<Recipe> actual = svc.getSides(testRecipe);
		assertEquals(expected, actual);
		
		deleteRecipe(side);
		
	}
	
	@Test
	public void removeDeletedSideFromRecipe_Pass(){
		Recipe side = new Recipe(2, 1, "Test Side", 4, 100, true, null, "", null);
		List<Recipe> list = svc.getRecipeList();
		testRecipe.addSide(side);
		list.add(side);
		try{
		      OutputStream out = new FileOutputStream(svc.getPath() + "recipes.dat");
		      OutputStream buffer = new BufferedOutputStream( out );
		      ObjectOutput output = new ObjectOutputStream( buffer );
		      try{
		        output.writeObject(list);
		      }
		      finally{
		        output.close();
		      }
		    }  
		    catch(IOException ex){
		      ex.printStackTrace();
		    }
		addTestRecipe();
		deleteRecipe(side);
				
		int expected = 0;
		int actual = svc.getSides(testRecipe).size();
		
		assertEquals(expected, actual);
	}
	
	private void addTestRecipe(){
		List<Recipe> list = svc.getRecipeList();
		list.add(testRecipe);
		try{
		      OutputStream out = new FileOutputStream(svc.getPath() + "recipes.dat");
		      OutputStream buffer = new BufferedOutputStream( out );
		      ObjectOutput output = new ObjectOutputStream( buffer );
		      try{
		        output.writeObject(list);
		      }
		      finally{
		        output.close();
		      }
		    }  
		    catch(IOException ex){
		      ex.printStackTrace();
		    }
	}
	
	private void deleteTestRecipe(){
		List<Recipe> list = svc.getRecipeList();
		for(Recipe recipe: list){
			if(recipe.getId() == testRecipe.getId()){
				list.remove(recipe);
				break;
			}
		}
		svc.writeToFile(list);
	}
	
	private void deleteRecipe(Recipe recipe){
		List<Recipe> list = svc.getRecipeList();
		for(Recipe r: list){
			if(recipe.getId() == recipe.getId()){
				list.remove(r);
				break;
			}
		}
		svc.writeToFile(list);
	}

}
