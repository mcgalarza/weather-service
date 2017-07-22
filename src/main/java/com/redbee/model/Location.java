package com.redbee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Location {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@ManyToOne
	private Board board;
	@ManyToOne
	private Weather weather;
	
	public Location() {}
	
	public Location(Long id, String name, Board board) {
		this.id = id;
		this.name = name;
		this.board = board;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public Board getBoard() {
		return board;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

}
