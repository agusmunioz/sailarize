package com.github.sailarize.resource;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import com.github.sailarize.form.Form;
import com.github.sailarize.link.HypermediaLink;
import com.github.sailarize.link.LinkBuilder;
import com.github.sailarize.media.Image;
import com.github.sailarize.url.UrlBuilder;
import com.github.sailarize.utils.ToStringBuilder;

/**
 * Models a Sail Resource.
 * 
 * @author agusmunioz
 * 
 */
public abstract class SailResource {

	private static final String SELF = "self";

	private String id;

	private Map<String, Object> meta;

	private Map<String, Collection<HypermediaLink>> links;

	private Map<String, Collection<Form>> forms;

	/**
	 * Creates a {@link SailResource}.
	 * 
	 */
	protected SailResource() {

		this.links = new LinkedHashMap<String, Collection<HypermediaLink>>();
	}

	/**
	 * Creates a {@link SailResource} which URL is a template.
	 * 
	 * @param id
	 *            the resource id.
	 * 
	 * @param values
	 *            if the resource's URL is a template, each variable is replaced
	 *            with the a value.
	 */
	public SailResource(String id, Object... values) {

		this();
		this.id = id;
		this.add((new LinkBuilder(this, SELF, values)).build());
	}

	/**
	 * Adds a value in meta section.
	 * 
	 * @param field
	 *            the field under the value is stored. It cannot use # as it is
	 *            a reserved key.
	 * @param value
	 *            the value.
	 */
	public void meta(String field, Object value) {

		if (this.meta == null) {
			this.meta = new LinkedHashMap<String, Object>();
		}

		this.meta.put(field, value);
	}

	/**
	 * Gets the meta information.
	 * 
	 * @return a not empty map or null if no meta is configured.
	 */
	public Map<String, Object> getMeta() {
		return this.meta;
	}

	/**
	 * Adds a link under a group.
	 * 
	 * @param link
	 *            the link.
	 * 
	 * @param groups
	 *            the groups names. Cannot use the character '#' as it is a Sail
	 *            reserved character.
	 */
	public void add(HypermediaLink link, String... groups) {

		this.group(this.links, link, SailTags.LINKS, groups);
	}

	/**
	 * The links and their groups.
	 * 
	 * @return a not null map of links.
	 */
	public final Map<String, Collection<HypermediaLink>> getLinks() {

		return this.links;
	}

	/**
	 * Adds a form under a group.
	 * 
	 * @param form
	 *            the form.
	 * 
	 * @param groups
	 *            the group names. Cannot use the character '#' as it is a Sail
	 *            reserved character.
	 */
	public void add(Form form, String... groups) {

		if (this.forms == null) {
			this.forms = new LinkedHashMap<String, Collection<Form>>();
		}

		this.group(this.forms, form, SailTags.FORMS, groups);
	}

	/**
	 * The forms under each group.
	 * 
	 * @return the map of forms or null if no form is added.
	 */
	public final Map<String, Collection<Form>> getForms() {

		return this.forms;
	}

	/**
	 * Adds an image link to the resource.
	 * 
	 * @param image
	 *            the image to link to.
	 * 
	 * @param groups
	 *            the groups the link must be added to.
	 */
	public void add(Image image, String... groups) {

		this.group(this.links, image.getLink(), SailTags.IMG, groups);
	}

	/**
	 * The resource id.
	 * 
	 * @return the id.
	 */
	public final String getId() {

		return id;
	}

	/**
	 * Sets the resource id.
	 * 
	 * @param id
	 */
	protected final void setId(String id) {

		this.id = id;
	}

	/**
	 * Configures the resource's path by overriding the default. The self link
	 * is updated.
	 * 
	 * @param path
	 *            a URL path that could contain variables.
	 * 
	 * @param values
	 *            path variable values
	 */
	public void overridePath(String path, Object... values) {

		// TODO: revisar el override.
		for (HypermediaLink link : this.getLinks().get(SailTags.LINKS)) {

			if (SELF.equals(link.getRel())) {
				link.setHref(UrlBuilder.url(path, values));
				return;
			}
		}
	}

	/**
	 * Adds an object to a group.
	 * 
	 * @param object
	 *            the object to group.
	 * 
	 * @param prefix
	 *            the group prefix or starting name.
	 * 
	 * @param names
	 *            the list of names for building the group key by concatenating
	 *            the names with {@link SailResource#KEY}.
	 */
	private <T> void group(Map<String, Collection<T>> groups, T object, String prefix, String... names) {

		StringBuilder builder = new StringBuilder(prefix);

		for (String name : names) {
			builder.append(SailTags.KEY).append(name);
		}

		String key = builder.toString();

		if (!groups.containsKey(key)) {
			groups.put(key, new LinkedList<T>());
		}

		groups.get(key).add(object);
	}

	@Override
	public String toString() {

		return ToStringBuilder.toString(this);
	}

}
