package com.github.sailarize.url;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.sailarize.servlet.RequestHolder;

/**
 * Unit test for {@link Domain}.
 * 
 * @author agusmunioz
 *
 */
public class DomainTest {

    /**
     * Test for a cross domain.
     */
    @Test
    public void crossdomain() {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        Mockito.when(request.getServerName()).thenReturn("www.thelocaldomain.com");

        RequestHolder.set(request);

        Assert.assertTrue("A cross domain is not considered cross",
                Domain.cross("http://www.adomain.com/api/resource"));
    }

    /**
     * Test for a not cross domain absolute url
     */
    @Test
    public void notcrossdomain() {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        Mockito.when(request.getServerName()).thenReturn("www.thelocaldomain.com");

        RequestHolder.set(request);

        Assert.assertFalse("A not cross domain is considered cross",
                Domain.cross("http://www.thelocaldomain.com/api/resource"));
    }

    /**
     * Test a relative url is not considered cross domain.
     */
    @Test
    public void relative() {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        Mockito.when(request.getServerName()).thenReturn("www.thelocaldomain.com");

        RequestHolder.set(request);

        Assert.assertFalse("A relative url is considered cross", Domain.cross("/api/resource"));
    }

    /**
     * Test the double slash for protocol relative is supported for not cross
     * domain determination.
     */
    @Test
    public void protocolRelativeNotCrossdomain() {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        Mockito.when(request.getServerName()).thenReturn("www.thelocaldomain.com");

        RequestHolder.set(request);

        Assert.assertFalse("A protocol relative cross domain url is not considered cross",
                Domain.cross("//www.thelocaldomain.com/api/resource"));
    }

    /**
     * Test the double slash for protocol relative is supported for cross domain
     * determination.
     */
    @Test
    public void protocolRelativeCrossdomain() {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        Mockito.when(request.getServerName()).thenReturn("www.thelocaldomain.com");

        RequestHolder.set(request);

        Assert.assertTrue("A protocol relative not cross domain url is considered cross",
                Domain.cross("//anotherdomain.io/api/resource"));
    }
}
