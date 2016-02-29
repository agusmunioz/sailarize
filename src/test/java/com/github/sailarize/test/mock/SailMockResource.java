package com.github.sailarize.test.mock;

import com.github.sailarize.resource.SailResource;
import com.github.sailarize.resource.Path;

/**
 * A resource mock class for testing purposes.
 * 
 * @author agusmunioz
 * 
 */
@Path("/mocks")
public class SailMockResource extends SailResource {

	public SailMockResource(String id) {
		super(id);
	}

}
