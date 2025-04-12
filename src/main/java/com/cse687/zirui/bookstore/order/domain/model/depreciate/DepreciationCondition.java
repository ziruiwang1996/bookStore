package com.cse687.zirui.bookstore.order.domain.model.depreciate;

public enum DepreciationCondition implements DepreciationStrategy {
    FAIR(0.7),
    GOOD(0.8),
    LIKE_NEW(0.9);

    private final double rate;

    DepreciationCondition(double rate) {
        this.rate = rate;
    }

    @Override
    public double depreciationRate() {
        return rate;
    }
}