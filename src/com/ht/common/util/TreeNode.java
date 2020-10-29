package com.ht.common.util;

public class TreeNode {
	private String id = null;
	private String parent = null;
	private String name = null;
	private Integer print = 0;

	public TreeNode(String id, String name, String parent) {
		this.id = id;
		this.name = name;
		this.parent = parent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrint() {
		return print;
	}

	public void setPrint(Integer print) {
		this.print = print;
	}
}
