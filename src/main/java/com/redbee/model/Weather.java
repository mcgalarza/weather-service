package com.redbee.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@JsonAutoDetect
public class Weather {
	@Id
	private Long woeid;
	private String date;
	private Integer temp;
	private String text;
	@OneToMany(mappedBy = "weather")
	@JsonIgnore
	private List<Location> locations;
	
	public Weather() {}
	
	public Weather(Long woeid, String date, Integer temp, String text) {
		this.woeid = woeid;
		this.date = date;
		this.temp = temp;
		this.text = text;
	}

	public Long getWoeid() {
		return woeid;
	}

	public void setWoeid(Long woeid) {
		this.woeid = woeid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
