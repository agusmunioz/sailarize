package com.github.sailarize.mock;

import com.github.sailarize.resource.Path;
import com.github.sailarize.resource.SailResource;

@Path("/persons")
public class PersonMock extends SailResource {

    private String name;

    private Integer age;

    public PersonMock(String id, String name, Integer age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
