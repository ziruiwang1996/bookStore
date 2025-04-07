package com.cse687.zirui.bookstore.services.order.model.depreciatestrategy;

public class GoodDepreciate implements DepreciateStrategy{
    @Override
    public double depreciationRate() {
        return 0.8;
    }
}
