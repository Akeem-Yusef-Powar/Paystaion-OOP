package edu.temple.cis.paystation;

public class LinerRateOne implements RateStrategy {

    public int calculateTime(int inserted) {
        return (inserted * 2) / 5;

    }

}

