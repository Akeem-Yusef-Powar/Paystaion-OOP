package edu.temple.cis.paystation;

public class LinerRateTwo implements RateStrategy{
    @Override
    public int calculateTime(int inserted) {
        return inserted / 5;
    }
}
