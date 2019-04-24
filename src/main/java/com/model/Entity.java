package com.model;

public class Entity {

	private String items;
	private double quantity;
	private double spend;
	private double spentPerDay;
	private double totalPerMonth;

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getSpend() {
		return spend;
	}

	public void setSpend(double spend) {
		this.spend = spend;
	}

	public double getSpentPerDay() {
		return spentPerDay;
	}

	public void setSpentPerDay(double spentPerDay) {
		this.spentPerDay = spentPerDay;
	}

	public double getTotalPerMonth() {
		return totalPerMonth;
	}

	public void setTotalPerMonth(double totalPerMonth) {
		this.totalPerMonth = totalPerMonth;
	}

}
