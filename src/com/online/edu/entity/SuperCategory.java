package com.online.edu.entity;

import java.util.List;

public class SuperCategory {
	private Integer id;
	private String name;
	private List<Category> subCategories;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Category> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}
	@Override
	public String toString() {
		return "SuperCategory [id=" + id + ", name=" + name
				+ ", subCategories=" + subCategories + "]\n";
	}
	
	
}
