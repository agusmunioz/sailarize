package com.github.sailarize.link;

public class SailPathFilter implements SailPredicate {

    private static String EXPRESSION = "%s[?%s]";

    private String field;

    private SailPredicate predicate;

    public SailPathFilter(String field, SailPredicate predicate) {
        this.field = field;
        this.predicate = predicate;
    }

    public SailPredicate getPredicate() {
        return predicate;
    }

    public void setPredicate(SailPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public String toString() {

        return String.format(EXPRESSION, this.field, this.predicate);
    }

}
