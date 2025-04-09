package com.cse687.zirui.bookstore.orderservice.model.depreciatestrategy;

public class LikeNewDepreciate implements DepreciateStrategy {
    @Override
    public double depreciationRate() {
        return 0.9;
    }
}
