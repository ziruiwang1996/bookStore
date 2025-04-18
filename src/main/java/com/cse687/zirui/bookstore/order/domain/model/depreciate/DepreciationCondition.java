package com.cse687.zirui.bookstore.order.domain.model.depreciate;

public enum DepreciationCondition implements DepreciationStrategy {
    FAIR(0.7f),
    GOOD(0.8f),
    LIKE_NEW(0.9f);

    private final float rate;

    DepreciationCondition(float rate) {
        this.rate = rate;
    }

    @Override
    public float depreciationRate() {
        return rate;
    }
}