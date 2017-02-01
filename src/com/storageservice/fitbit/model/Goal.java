package com.storageservice.fitbit.model;

public class Goal {

	private int caloriesOut;
	private double distance;
	private int steps;
	private int missingSteps;
	private double missingDistance;
	private int missingCalories;
	
	public int getCaloriesOut() {
		return this.caloriesOut;
	}

	public void setCaloriesOut(int caloriesOut) {
		this.caloriesOut = caloriesOut;
	}
	public int getSteps() {
		return this.steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}	
	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	

	public int getMissingSteps() {
		return this.missingSteps;
	}

	public void setMissingSteps(int missingSteps) {
		this.missingSteps = missingSteps;
	}	
	public double getMissingDistance() {
		return this.missingDistance;
	}

	public void setMissingDistance(double missingDistance) {
		this.missingDistance = missingDistance;
	}	
	public int getMissingCalories() {
		return this.missingSteps;
	}

	public void setMissingCalories(int missingCalories) {
		this.missingCalories = missingCalories;
	}	



}
