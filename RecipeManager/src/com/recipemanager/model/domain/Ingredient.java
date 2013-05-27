package com.recipemanager.model.domain;

import java.io.Serializable;

public class Ingredient  extends RMObject implements Serializable{

	private double amount;
	private Measurements measurement;
	private String name;
	
	public Ingredient(){
		
	}
	
	public Ingredient(double amt, Measurements measure, String name){
		amount = amt;
		measurement = measure;
		this.name = name;
	}
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Measurements getMeasurement() {
		return measurement;
	}
	public void setMeasurement(Measurements measurement) {
		this.measurement = measurement;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean validate(){
		if(amount == 0) return false;
		if(measurement == null) return false;
		if(name == "") return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((measurement == null) ? 0 : measurement.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		if (Double.doubleToLongBits(amount) != Double
				.doubleToLongBits(other.amount))
			return false;
		if (measurement != other.measurement)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}	
	
	@Override
	public String toString() {
		return amount + " " + measurement + "of " + name;
	}
}
