package com.github.sailarize.form;

import com.github.sailarize.utils.ToStringBuilder;

public class Option {

	private String title;

	private Object value;

	public Option(String title, Object value) {
		this.title = title;
		this.value = value;
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

	@Override
	public String toString() {

		return ToStringBuilder.toString(this);
	}
}
