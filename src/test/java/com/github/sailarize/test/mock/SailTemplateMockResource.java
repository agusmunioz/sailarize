package com.github.sailarize.test.mock;

import com.github.sailarize.resource.SailResource;
import com.github.sailarize.resource.Path;

/**
 * A {@link SailResource} mock class with a template URL.
 * 
 * @author agusmunioz
 * 
 */
@Path("/mocks/{anId}/othermocks/{otherId}/somemocks")
public class SailTemplateMockResource extends SailResource {

    public SailTemplateMockResource(String id) {

        super(id);
    }

}
