package com.example.ProjekatIsa.DTO;

import com.example.ProjekatIsa.model.Hotel;

public class HotelDTO {
  
	private Long id;
    private String name;
    private String city;
    private String address;
    private String description;
	private Double average_rating;
    
    public HotelDTO() {
		
	}

	public HotelDTO(Long id, String name,String city, String address, String description, Double average_rating) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.description = description;
		this.average_rating = average_rating;
	}

	public HotelDTO(Hotel hotel) {
		this(hotel.getId(), hotel.getName(),hotel.getCity(), hotel.getAddress(), hotel.getDescription(),hotel.getAverage_rating());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getDescription() {
		return description;
	}

	public Double getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(Double average_rating) {
		this.average_rating = average_rating;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
 
    
}
