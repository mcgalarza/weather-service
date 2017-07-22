package com.redbee.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Weather {
	@Id
	private Long woeid;
	private Date date;
	private Integer temp;
	private String text;
	
	public Weather() {}
	
	public Weather(Long woeid, Date date, Integer temp, String text) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
