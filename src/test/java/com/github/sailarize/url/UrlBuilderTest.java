package com.github.sailarize.url;

import org.junit.Assert;
import org.junit.Test;

import com.github.sailarize.mock.AnotherMockResource;
import com.github.sailarize.mock.EmptyUrlResource;
import com.github.sailarize.mock.NotAnnotatedResource;
import com.github.sailarize.mock.SailMockResource;
import com.github.sailarize.mock.SailTemplateMockResource;
import com.github.sailarize.resource.Path;

/**
 * Unit test for {@link UrlBuilder}.
 * 
 * @author agusmunioz
 * 
 */
public class UrlBuilderTest {

    /**
     * Test the URL build of a resource class.
     */
    @Test
    public void resourceClass() {

        String expected = "/mocks";

        String url = UrlBuilder.url(SailMockResource.class);

        Assert.assertEquals("Unexpected url for resource class", expected, url);

    }

    /**
     * Test the URL build of a resource class with a template URL.
     */
    @Test
    public void resourceClassTemplate() {

        String expected = "/mocks/1/othermocks/2/somemocks";

        String url = UrlBuilder.url(SailTemplateMockResource.class,
                new Object[] { 1, 2 });

        Assert.assertEquals(
                "Unexpected url for resource class with tempalte URL",
                expected, url);

    }

    /**
     * Test the URL build of a specific resource instance.
     */
    @Test
    public void resourceInstance() {

        String expected = "/mocks/1";

        String url = UrlBuilder.url(new SailMockResource("1"));

        Assert.assertEquals("Unexpected url for specific resource", expected,
                url);
    }

    /**
     * Test the URL build of a specific resource instance.
     */
    @Test
    public void resourceInstanceTemplate() {

        String expected = "/mocks/1/othermocks/2/somemocks/3";

        String url = UrlBuilder.url(new SailTemplateMockResource("3"),
                new Object[] { 1, 2 });

        Assert.assertEquals(
                "Unexpected url for specific resource with a template URL",
                expected, url);
    }

    /**
     * Test the URL build for a list resource relation.
     */
    @Test
    public void listRelation() {

        String expected = "/mocks/1/anothers";

        String url = UrlBuilder.url(new SailMockResource("1"),
                AnotherMockResource.class);

        Assert.assertEquals("Unexpected url for list resource relation",
                expected, url);
    }

    /**
     * Test the URL build for a resource relation with a specific resource.
     */
    @Test
    public void specificRelation() {

        String expected = "/mocks/1/anothers/3";

        String url = UrlBuilder.url(new SailMockResource("1"),
                new AnotherMockResource("3"));

        Assert.assertEquals("Unexpected url for resource relation", expected,
                url);
    }

    /**
     * Test when a resource is defined with no {@link Path} annotation.
     */
    @Test(expected = UrlBuildException.class)
    public void noUrlAnnotation() {

        UrlBuilder.url(NotAnnotatedResource.class);

    }

    /**
     * Test when a resource is defined with {@link Path} but whith an empty
     * value.
     */
    @Test(expected = UrlBuildException.class)
    public void urlEmpty() {

        UrlBuilder.url(EmptyUrlResource.class);

    }

    /**
     * Test the URL build when {@link PathHolder} is set with the API path.
     */
    @Test
    public void pathSet() {

        String path = "http://my.site.com/api";

        PathHolder.set(path);

        String expected = path + "/mocks";

        String url = UrlBuilder.url(SailMockResource.class);

        Assert.assertEquals(
                "Unexpected url for resource class when using PathHolder",
                expected, url);

        expected = path + "/mocks/1";

        url = UrlBuilder.url(new SailMockResource("1"));

        Assert.assertEquals(
                "Unexpected url for resource when using PathHolder", expected,
                url);

        expected = path + "/mocks/1/mocks/2";

        url = UrlBuilder.url(new SailMockResource("1"), new SailMockResource(
                "2"));

        Assert.assertEquals(
                "Unexpected url for resources relation when using PathHolder",
                expected, url);

        expected = path + "/mocks/1/mocks";

        url = UrlBuilder.url(new SailMockResource("1"), SailMockResource.class);

        Assert.assertEquals(
                "Unexpected url for resources relation with a list when using PathHolder",
                expected, url);

        PathHolder.clean();
    }

    /**
     * Test the host replacement in a URL.
     */
    @Test
    public void hostReplacement() {

        String url = "http://www.old.host.com/api/resource/2";

        String host = "new.host.com";

        String expected = "http://new.host.com/api/resource/2";

        Assert.assertEquals("Unexpected host replacement in URL.", expected,
                UrlBuilder.host(url, host));

    }

    /**
     * Test the host replacement in a URL that has a port number.
     */
    @Test
    public void hostReplacementWithPort() {

        String url = "http://www.old.host.com:8080/api/resource/2";

        String host = "new.host.com";

        String expected = "http://new.host.com/api/resource/2";

        Assert.assertEquals("Unexpected host replacement in URL.", expected,
                UrlBuilder.host(url, host));

    }

    /**
     * Test the host append in a URL path.
     */
    @Test
    public void hostSet() {

        String url = "/api/resource/2";

        String host = "new.host.com";

        String expected = "new.host.com/api/resource/2";

        Assert.assertEquals("Unexpected host append in URL path", expected,
                UrlBuilder.host(url, host));

    }

    /**
     * Test the protocol replacement in a URL.
     */
    @Test
    public void protocolReplacement() {

        String url = "http://www.old.host.com/api/resource/2";

        String protocol = "https";

        String expected = "https://www.old.host.com/api/resource/2";

        Assert.assertEquals("Unexpected protocol replacement.", expected,
                UrlBuilder.protocol(url, protocol));
    }

    /**
     * Test the protocol replacement in a URL.
     */
    @Test
    public void protocolSet() {

        String url = "/api/resource/2";

        String protocol = "https";

        String expected = "";

        Assert.assertEquals("Unexpected protocol replacement.", expected,
                UrlBuilder.protocol(url, protocol));
    }
}
