package com.online.edu.entity;

public class Category {
	private Integer id;
	private String name;
	// 分类所属子分类
	private SuperCategory superCategory;
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
	public SuperCategory getSuperCategory() {
		return superCategory;
	}
	public void setSuperCategory(SuperCategory superCategory) {
		this.superCategory = superCategory;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", superCategory="
				+ superCategory + "]\n";
	}
	
	
}
