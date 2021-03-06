package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Car;

public class CarDTO {
	
	private Long id;
	private String name;
	private String car_number;
	private int price;
	private int prod_year;
	private Double average_rating;
	private RentACarDTO rentacar;
	
	public CarDTO(Car car) {
		this.id = car.getId();
		this.name = car.getName();
		this.car_number = car.getCar_number();
		this.price = car.getPrice();
		this.prod_year = car.getProd_year();
		this.average_rating = car.getAverage_rating();
		this.rentacar = new RentACarDTO(car.getRentalcars());
		
		
		
	}
	
	public CarDTO(Long id, String name, String car_number, int price,int prod_year,Double average_rating,RentACarDTO rentacar,CategoryDTO category) {
		super();
		this.id = id;
		this.name = name;
		this.car_number = car_number;
		this.price = price;
		this.prod_year = prod_year;
		this.average_rating = average_rating;
		this.rentacar = rentacar;
		
		
		
	}
	

	public CarDTO() {
		
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getProd_year() {
		return prod_year;
	}

	public void setProd_year(int prod_year) {
		this.prod_year = prod_year;
	}

	public Double getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(Double average_rating) {
		this.average_rating = average_rating;
	}

	public RentACarDTO getRentacar() {
		return rentacar;
	}

	public void setRentacar(RentACarDTO rentacar) {
		this.rentacar = rentacar;
	}

	

	
	
	
	


	
	
	
	
	
	
	
	
	

}
