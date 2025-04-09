package com.cse687.zirui.bookstore.orderservice.model.depreciatestrategy;

public class FairDepreciate implements DepreciateStrategy{
    @Override
    public double depreciationRate() {
        return 0.7;
    }
}
