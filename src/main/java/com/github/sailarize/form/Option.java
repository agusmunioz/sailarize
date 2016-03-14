package com.github.sailarize.form;

import com.github.sailarize.utils.ToStringBuilder;

public class Option {

	private String title;

	private String value;

	public Option(String title, String value) {
		this.title = title;
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {

		return ToStringBuilder.toString(this);
	}
}
