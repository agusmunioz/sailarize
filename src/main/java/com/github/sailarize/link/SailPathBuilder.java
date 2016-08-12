package com.github.sailarize.link;

import java.util.LinkedList;

/**
 * 
 * Builds a link for a SailPath expression.
 * 
 * @author agusmunioz
 *
 */
public class SailPathBuilder {

	private static final String PROTOCOL = "sailpath://";

	private static final String PARENT = "^";

	private static final String ROOT = "$";

	private String rel;

	private LinkedList<String> fields = new LinkedList<String>();

	/**
	 * Creates an initialized {@link SailPathBuilder}.
	 * 
	 * @param rel
	 *            the link rel.
	 * @return the builder.
	 */
	public static SailPathBuilder rel(String rel) {

		SailPathBuilder builder = new SailPathBuilder();
		builder.rel = rel;
		return builder;
	}

	/**
	 * Adds the root reference to the expression.
	 * 
	 * @return the builder.
	 */
	public SailPathBuilder root() {

		if (!this.fields.contains(ROOT)) {
			this.fields.addFirst(ROOT);
		}

		return this;
	}

	/**
	 * Adds a field in the expression.
	 * 
	 * @param name
	 *            the field name.
	 * 
	 * @return the builder.
	 */
	public SailPathBuilder field(String name) {

		this.fields.add(name);
		return this;
	}

	/**
	 * Adds the parent reference to the expression.
	 * 
	 * @return the builder.
	 */
	public SailPathBuilder parent() {
		this.fields.add(PARENT);
		return this;
	}

	/**
	 * Adds parent references iteratively.
	 * 
	 * @param amount
	 *            the amount of parent references.
	 * @return the builder.
	 */
	public SailPathBuilder parents(int amount) {

		for (int i = 0; i < amount; i++) {
			this.parent();
		}

		return this;
	}

	public SailPathBuilder filter(SailPathFilter filter) {
		this.fields.add(filter.toString());
		return this;
	}

	/**
	 * Builds the link.
	 * 
	 * @return the link.
	 */
	public HypermediaLink build() {

		HypermediaLink link = new HypermediaLink();
		link.setRel(this.rel);
		link.setHref(PROTOCOL + String.join(".", this.fields));
		return link;
	}
}
