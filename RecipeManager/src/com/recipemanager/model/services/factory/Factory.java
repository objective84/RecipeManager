package com.recipemanager.model.services.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.recipemanager.model.buisiness.exception.ServiceLoadFailException;
import com.recipemanager.model.services.IService;
import com.recipemanager.model.services.ServiceType;


public class Factory {
	
	
	public Factory(){
		
	}
	
	public IService getService(ServiceType type) throws ServiceLoadFailException{
		try 
		{
		   Class<?> c = Class.forName(getImplName(type));
		   return (IService)c.newInstance();
		} catch (Exception excp) 
		{
			String serviceName = type + " not loaded";
			throw new ServiceLoadFailException(serviceName, excp);
		}
	}
	
	public String getImplName(ServiceType type) throws Exception{
		java.util.Properties props = new java.util.Properties();
		String propertyFileLocation = System.getProperty("prop_location");
   	    
   	    System.out.println ("Property File Location passed : " + propertyFileLocation);
	    java.io.FileInputStream fis = new java.io.FileInputStream(propertyFileLocation);

	    props.load(fis);
	    fis.close();
	    return props.getProperty(type.toString());	
	}
	
	private void setSystemProperties() throws IOException{
		FileInputStream propFile =
	            new FileInputStream( "properties.txt");
	        Properties p =
	            new Properties(System.getProperties());
	        p.load(propFile);

	        System.setProperties(p);
	}

}
