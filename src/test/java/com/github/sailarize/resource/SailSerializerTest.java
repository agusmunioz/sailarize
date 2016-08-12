package com.github.sailarize.resource;

import org.junit.Test;

import com.github.sailarize.asserts.AssertSerialization;
import com.github.sailarize.form.FormBuilder;
import com.github.sailarize.link.LinkBuilder;
import com.github.sailarize.media.Image;
import com.github.sailarize.media.Video;
import com.github.sailarize.mock.PersonMock;

/**
 * Test that any serialization implementation must run (extend) in order to
 * ensure a valid serialization of a {@link SailResource}.
 * 
 * @author agusmunioz
 *
 */
public abstract class SailSerializerTest {

	/**
	 * Test the serialization of a base resource, that is to say, it doesn't
	 * define any hypermedia control.
	 */
	@Test
	public void base() {

		PersonMock person = new PersonMock("1", "Agus", 34);

		String resource = this.serialize(person);

		AssertSerialization.assertEquals("Unexpected base resource serialization", "person_base", resource);

	}

	/**
	 * Test the serialization of a base resource with a self link.
	 */
	@Test
	public void self() {

		PersonMock person = new PersonMock("1", "Agus", 34);
		person.self();
		String resource = this.serialize(person);

		AssertSerialization.assertEquals("Unexpected base resource serialization", "person_self", resource);

	}
	
	/**
	 * Test the serialization of a resource with several links.
	 */
	@Test
	public void links() {

		PersonMock person = new PersonMock("1", "Agus", 34);

		person.add(new LinkBuilder(new PersonMock("2", "Barby", 34), "wife").build());

		person.add(new LinkBuilder(PersonMock.class, "cousines").filter("cousines", "1").build(), "relatives");

		person.add(new LinkBuilder(PersonMock.class, "uncles").filter("uncles", "1").build(), "relatives");

		String resource = this.serialize(person);

		AssertSerialization.assertEquals("Unexpected resource with links serialization", "person_links", resource);
	}

	/**
	 * Test the serialization of a resource with several forms.
	 */
	@Test
	public void forms() {

		PersonMock person = new PersonMock("1", "Agus", 34);

		person.add(FormBuilder.update(person).number("age").build());

		person.add(FormBuilder.update(person).number("wife").build(), "relatives");

		String resource = this.serialize(person);

		AssertSerialization.assertEquals("Unexpected resource with forms serialization", "person_forms", resource);
	}

	/**
	 * Test the serialization of a resource with meta information.
	 */
	@Test
	public void meta() {

		PersonMock person = new PersonMock("1", "Agus", 34);

		person.meta("aMeta", "123");

		person.meta("anotherMeta", "456");

		String resource = this.serialize(person);

		AssertSerialization.assertEquals("Unexpected resource with meta serialization", "person_meta", resource);

	}

	/**
	 * Test the serialization of a resource with images.
	 */
	@Test
	public void images() {

		PersonMock person = new PersonMock("1", "Agus", 34);

		for (int i = 1; i <= 2; i++) {
			Image image = new Image("/images/" + i, "" + i);
			image.setType("png");
			person.add(image);
		}

		String resource = this.serialize(person);

		AssertSerialization.assertEquals("Unexpected resource with images serialization", "person_images", resource);

	}

	/**
	 * Test the serialization of a resource with videos.
	 */
	@Test
	public void videos() {

		PersonMock person = new PersonMock("1", "Agus", 34);

		for (int i = 1; i <= 2; i++) {
			Video video = new Video("" + i, "/videos/" + i);
			person.add(video);
		}

		String resource = this.serialize(person);

		AssertSerialization.assertEquals("Unexpected resource with vide serialization", "person_videos", resource);

	}

	/**
	 * Test the serialization of a resource with links, forms and meta.
	 */
	@Test
	public void full() {

		PersonMock person = new PersonMock("1", "Agus", 34);

		person.add(new LinkBuilder(new PersonMock("2", "Barby", 34), "wife").build());

		person.add(new LinkBuilder(PersonMock.class, "cousines").filter("cousines", "1").build(), "relatives");

		person.add(new LinkBuilder(PersonMock.class, "uncles").filter("uncles", "1").build(), "relatives");

		person.add(FormBuilder.update(person).number("age").build());

		person.add(FormBuilder.update(person).number("wife").build(), "relatives");

		person.meta("aMeta", "123");

		person.meta("anotherMeta", "456");

		for (int i = 1; i <= 2; i++) {
			Image image = new Image("/images/" + i, "" + i);
			image.setType("png");
			person.add(image);

			Video video = new Video("" + i, "/videos/" + i);
			person.add(video);
		}

		String resource = this.serialize(person);

		AssertSerialization.assertEquals("Unexpected resource with each type of hypermedia controls serialization",
				"person_full", resource);
	}

	/**
	 * Serializes a sail resource.
	 * 
	 * @param resource
	 *            the resource to serialize.
	 * 
	 * @return the json.
	 */
	protected abstract String serialize(SailResource resource);
}
