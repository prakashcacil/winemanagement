package edu.tus.winemanager.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name="wines")
public class Wine {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private int year;
	private String name;
	private String grapes;
	private String country;
	private String region;
	@Enumerated(EnumType.STRING)
	private Rating rating;
	@Column(name="expiry_date", columnDefinition="DATE")
	private LocalDate expiryDate;
	
	public Wine() {

	}
	
	public Wine(Long id, String name, String grapes, String country, String region, Rating rating, LocalDate expiryDate) {
		this.id = id;
		this.name = name;
		this.grapes = grapes;
		this.country = country;
		this.region = region;
		this.rating = rating;
		this.expiryDate = expiryDate;
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
	public String getGrapes() {
		return grapes;
	}
	public void setGrages(String grapes) {
		this.grapes = grapes;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setGrapes(String grapes) {
		this.grapes = grapes;
	}

}
