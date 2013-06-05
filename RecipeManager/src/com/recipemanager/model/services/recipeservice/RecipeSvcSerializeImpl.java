package com.recipemanager.model.services.recipeservice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import com.recipemanager.model.domain.Ingredient;
import com.recipemanager.model.domain.RMObject;
import com.recipemanager.model.domain.Recipe;
import com.recipemanager.model.services.exceptions.IdAlreadyExistsException;
import com.recipemanager.model.services.exceptions.RecipeNotFoundException;



public class RecipeSvcSerializeImpl implements IRecipeSvc {

	public String getPath(){
		JFileChooser fr = new JFileChooser();
	     FileSystemView fw = fr.getFileSystemView();
	     String path = fw.getDefaultDirectory()+ "\\Recipe Manager\\data\\";
	     return path;
	}
	
	public List<Recipe> getRecipeList() {
		InputStream in;
		try {
			in = new FileInputStream( getPath() + "recipes.dat" );
			InputStream buffer = new BufferedInputStream( in );
			ObjectInput input = new ObjectInputStream ( buffer );
			return (List<Recipe>)input.readObject();
	    } catch (FileNotFoundException e) {
	    	File file = new File(getPath());
	    	file.mkdirs();
	    	file = new File(getPath() + "recipes.dat");
	    	try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		return new ArrayList<Recipe>();
	}
	
	public void writeToFile(List<Recipe> list){
		try{
		      OutputStream out = new FileOutputStream(getPath() + "recipes.dat");
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
	
	@Override
	public void create(RMObject obj) throws IdAlreadyExistsException{
		try{
			find(((Recipe)obj).getId());
			}
		catch(RecipeNotFoundException ex){
			List<Recipe> list = getRecipeList();
			list.add((Recipe)obj);
			writeToFile(list);
		} 
		
		throw new IdAlreadyExistsException();
	}

	@Override
	public void edit(RMObject obj) {
		List<Recipe> list = getRecipeList();
		for(Recipe recipe : list){
			if(recipe.getId() == ((Recipe)obj).getId()){
				list.remove(recipe);
				break;
			}
		}
		list.add((Recipe)obj);
		writeToFile(list);
	}

	@Override
	public RMObject find(int id) throws RecipeNotFoundException{
		List<Recipe> list = getRecipeList();
		for(Recipe recipe : list){
			if(recipe.getId() == id) {
				return recipe;
			}
		}
		throw new RecipeNotFoundException();
	}

	@Override
	public void delete(RMObject obj) {
		List<Recipe> list = getRecipeList();
		for(Recipe recipe: list){
			if(recipe.equals(obj)){
				list.remove(recipe);
				break;
			}
		}
		writeToFile(list);
	}

	@Override
	public void addIngredient(Recipe recipe, Ingredient ingredient) {
		Recipe add;
		try {
			add = (Recipe)find(recipe.getId());
			add.addIngredient(ingredient);
			edit(add);
		} catch (RecipeNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeIngredient(Recipe recipe, Ingredient ingredient) {
		Recipe remove;
		try {
			remove = (Recipe)find(recipe.getId());
			remove.removeIngredient(ingredient);
			edit(remove);	
		} catch (RecipeNotFoundException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<Ingredient> getIngredients(Recipe recipe) {
		Recipe get;
		try {
			get = (Recipe)find(recipe.getId());
			return get.getIngredients();
		} catch (RecipeNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addSide(Recipe recipe, Recipe side) {
		Recipe add;
		try {
			add = (Recipe)find(recipe.getId());
			add.addSide(side);
			edit(add);
		} catch (RecipeNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeSide(Recipe recipe, Recipe side) {
		Recipe remove;
		try {
			remove = (Recipe)find(recipe.getId());
			remove.removeSide(side);
			edit(remove);		
		} catch (RecipeNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Recipe> getSides(Recipe recipe){
		Recipe get = null;
		try {
			get = (Recipe)find(recipe.getId());
		} catch (RecipeNotFoundException e) {
			e.printStackTrace();
		}
		List<Recipe> sides = new ArrayList();
		for(int side : get.getSides()){
			try {
				sides.add((Recipe)find(side));
			} catch (RecipeNotFoundException e) {
				
				removeSide(recipe, new Recipe(side));
			}
		}
		return sides;
	}

	

	
}
