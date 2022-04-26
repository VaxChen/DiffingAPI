package com.vaxchen.app.diff.Models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Data {
	
	@Id
	private int id;
	
	@Column
	private String data;
	
	
	public Data(int id, String data) {
		this.id = id;
		this.data = data;
	}
	
	public Data(String data) {
		this.data = data;
	}
	
	public Data() {

	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getdata() {
		return data;
	}
	public void setdata(String data) {
		this.data = data;
	}

}
