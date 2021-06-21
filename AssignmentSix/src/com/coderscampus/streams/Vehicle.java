package com.coderscampus.streams;

public class Vehicle {

	private String date;
	private Integer sale;

	public Vehicle(String date, Integer sale) {
		this.date = date;
		this.sale = sale;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getSale() {
		return sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
	}
	
	@Override
	public String toString() {
		return "Vehicle [date=" + date + ", sale=" + sale + "]";
	}

}
