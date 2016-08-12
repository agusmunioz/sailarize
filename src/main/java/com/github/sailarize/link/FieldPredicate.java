package com.github.sailarize.link;

public class FieldPredicate implements SailPredicate {

	private static final String EXPRESION = "@%s %s %s";

	private String field;

	private String operation;

	private Object value;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {

		return String.format(EXPRESION, this.field, this.operation, this.value);
	}

}
