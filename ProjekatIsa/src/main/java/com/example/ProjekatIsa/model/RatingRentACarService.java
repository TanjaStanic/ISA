package com.example.ProjekatIsa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "rating_rentacarservice")

public class RatingRentACarService implements Serializable {
	
	public static final long serialVersionUID = 765845;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingRoom_id", nullable = false, updatable = false)
    private Long id;
	
	@Column(name = "value", nullable = false)
    private int value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public RatingRentACarService(Long id, int value) {
		super();
		this.id = id;
		this.value = value;
	}

	public RatingRentACarService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
