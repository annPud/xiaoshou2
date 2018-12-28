package com.gxwsxx.xiaoshou.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuModel {
	String id;
	@JsonProperty("text")
	String name;
	Boolean group;
	String link;
	String icon;
	String parent;

	List<MenuModel> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public List<MenuModel> getChildren() {
		return children;
	}

	public void setChildren(List<MenuModel> children) {
		this.children = children;
	}

	public Boolean getGroup() {
		return group;
	}

	public void setGroup(Boolean group) {
		this.group = group;
	}

}
