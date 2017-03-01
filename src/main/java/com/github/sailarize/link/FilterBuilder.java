package com.github.sailarize.link;

public class FilterBuilder {

    private String field;

    private SailPredicate predicate;

    public static FilterBuilder field(String field) {

        FilterBuilder builder = new FilterBuilder();
        builder.field = field;
        return builder;
    }

    public FilterBuilder filter(FieldPredicate predicate) {
        this.predicate = predicate;
        return this;
    }

    public SailPathFilter build() {

        return new SailPathFilter(this.field, this.predicate);
    }

}
