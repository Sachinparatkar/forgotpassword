package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String details;
	int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", details=" + details + ", status=" + status + "]";
	}
	public Task(int id, String details, int status) {
		super();
		this.id = id;
		this.details = details;
		this.status = status;
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}