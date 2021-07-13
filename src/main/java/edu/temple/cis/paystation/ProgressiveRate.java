package edu.temple.cis.paystation;

public class ProgressiveRate implements RateStrategy{
    @Override
    public int calculateTime(int inserted) {
        double forCalc;
        if (inserted < 150) {
            return (inserted * 2) / 5;
        } else if (inserted > 150 && inserted < 350) {
            forCalc = (double) (inserted - 150) * (3.0 / 10.0) + 60.0;
            return Math.round((float) forCalc);
        } else {
            return (inserted - 350) / 5 + 120;
        }
    }
}
