package com.github.sailarize.facet;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.sailarize.url.Filter;

/**
 * Unit test for {@link ExclusiveFacetOption}
 * 
 * @author agusmunioz
 * 
 */
public class ExclusiveFacetOptionTest {

    /**
     * Tests the build of filter when no filter is already applied.
     */
    @Test
    public void noFilters() {

        ExclusiveFacetOption option = new ExclusiveFacetOption("facet", "value");

        Collection<Filter> filters = option
                .compatibles(new LinkedList<Filter>());

        Assert.assertTrue("Unexpected amount of filters", filters.isEmpty());
    }

    /**
     * Tests no filter is removed when all applied filters doesn't belong to the
     * same facet.
     */
    @Test
    public void compatible() {

        ExclusiveFacetOption option = new ExclusiveFacetOption("facet", "value");

        Filter filter = new Filter("aFilter", "aValue");

        List<Filter> applied = Arrays.asList(filter);

        Collection<Filter> compatibles = option.compatibles(applied);

        Assert.assertEquals("Unexpected amount of filters.", applied.size(),
                compatibles.size());

        Assert.assertTrue("Compatible filter has been removed.",
                compatibles.contains(filter));

    }

    /**
     * Tests an incompatible filter is removed when belongs to the same facet.
     */
    @Test
    public void incompatible() {

        ExclusiveFacetOption option = new ExclusiveFacetOption("facet", "value");

        Filter compatible = new Filter("aFilter", "aValue");

        Filter incompatible = new Filter("facet", "otherValue");

        Collection<Filter> applied = Arrays.asList(compatible, incompatible);

        Collection<Filter> compatibles = option.compatibles(applied);

        Assert.assertEquals("Unexpected amount of filters.",
                applied.size() - 1, compatibles.size());

        Assert.assertTrue("Compatible filters has been removed.",
                compatibles.contains(compatible));

        Assert.assertFalse("Incompatible filter has not been removed.",
                compatibles.contains(incompatible));

    }

    /**
     * Tests the facet option application in a list of filters adds the
     * associated filter.
     */
    @Test
    public void apply() {

        ExclusiveFacetOption option = new ExclusiveFacetOption("facet", "value");

        Collection<Filter> filters = new LinkedList<Filter>();

        option.apply(filters);

        Assert.assertEquals("Unexpected amount of filters after apply", 1,
                filters.size());

        Assert.assertTrue("A filter for the option was not added.", filters
                .contains(new Filter(option.getFacet(), option.getValue())));
    }
}
