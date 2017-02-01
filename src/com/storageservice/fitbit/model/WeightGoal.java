package com.storageservice.fitbit.model;

public class WeightGoal {
	private String startDate;
	private double startWeight;
	private double goalWeight;
	
	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public double getStartWeight() {
		return this.startWeight;
	}

	public void setStartWeight(double startWeight) {
		this.startWeight = startWeight;
	}
	public double getGoalWeight() {
		return this.goalWeight;
	}

	public void setGoalWeight(double goalWeight) {
		this.goalWeight = goalWeight;
	}
}
