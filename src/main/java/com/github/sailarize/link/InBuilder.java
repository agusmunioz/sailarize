package com.github.sailarize.link;

import java.util.Collection;
import java.util.LinkedList;

public class InBuilder {

	private static final String DELIMITER = ",";

	private static final String EXPRESION = "(%s)";

	private static final String OPERATION = "in";

	private String field;

	private Object value;

	public static InBuilder field(String name) {

		InBuilder builder = new InBuilder();
		builder.field = name;

		return builder;
	}

	public InBuilder strings(Collection<String> values) {

		Collection<String> strings = new LinkedList<String>();

		for (String value : values) {
			strings.add(new StringBuilder("'").append(value).append("'").toString());
		}

		this.value = String.format(EXPRESION, String.join(DELIMITER, strings));

		return this;
	}

	public FieldPredicate build() {

		FieldPredicate predicate = new FieldPredicate();
		predicate.setField(this.field);
		predicate.setOperation(OPERATION);
		predicate.setValue(this.value);

		return predicate;
	}

}
