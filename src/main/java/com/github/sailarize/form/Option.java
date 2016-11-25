package com.github.sailarize.form;

import com.github.sailarize.utils.ToStringBuilder;

public class Option {

	private String title;

	private Object value;

	private Boolean selected;

	public Option(Object value) {
		this.value = value;
	}

	public Option(String title, Object value) {
		this(value);
		this.title = title;
	}

	public Option(Object value, boolean selected) {
		this(value);
		this.selected = selected;
	}

	public Option(String title, Object value, Boolean selected) {
		this(title, value);
		this.selected = selected;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {

		return ToStringBuilder.toString(this);
	}
}
