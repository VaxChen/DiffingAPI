package com.vaxchen.app.diff.Models;

import javax.persistence.*;

@Entity
public class Data {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String content;
	
	
	public Data(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
