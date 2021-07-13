/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */

package edu.temple.cis.paystation;

import java.util.HashMap;
import java.util.Map;

public class PayStationImpl implements PayStation {
    
    private int insertedSoFar, type5, type10, type25;
    private int timeBought = 0; // I think this gives NullPointerExceptions when not initialized and called repeatedly by readDisply();
    private RateStrategy currentRate = new LinerRateOne(); // arbitrary starting rate

    private final HashMap<Integer,Integer> coinsIn  = new HashMap <Integer, Integer>(){{
        put(5,0);
        put(10,0);
        put(25,0);
    }}; // map to add coins inserted, never null

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5:
                type5++;
                coinsIn.put(coinValue, type5);
                break;
            case 10:
                type10++;
                coinsIn.put(coinValue, type10);
                break;
            case 25:
                type25++;
                coinsIn.put(coinValue, type25);
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        timeBought += coinValue;
        insertedSoFar += coinValue;
    }
    
    @Override
    public int boughtSoFar(){
        return timeBought;
    }

    @Override
    public int readDisplay() {
        return currentRate.calculateTime(timeBought);
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        reset();
        return r;
    }
    @Override
    public Map<Integer, Integer> cancel() {
        HashMap<Integer, Integer> currentState = new HashMap<>(coinsIn);
        coinsIn.put(5, 0);
        coinsIn.put(10, 0);
        coinsIn.put(25, 0);
        timeBought = 0;
        return currentState;
    }

    @Override
    public void changeRates(int changeTo) {
        if (changeTo == 1){
                    currentRate = new LinerRateOne();
                    System.out.println("Pay Station rate changed to: Liner Rate 1\n");
        } else if(changeTo == 2){
                    currentRate = new LinerRateTwo();
                    System.out.println("Pay Station rate changed to: Liner Rate 2\n");
        } else if(changeTo == 3){
                    currentRate = new ProgressiveRate();
                    System.out.println("Pay Station rate changed to: Progressive Rate\n");
        } else if(changeTo == 4){
                    currentRate = new AltOne<>();
                    System.out.println("Pay Station rate changed to: Alternate Rate 1\n");
        } else if(changeTo == 5){
                    currentRate = new AltTwo<>();
                    System.out.println("Pay Station rate changed to: Alternate Rate 2\n");
        }

    }

    @Override
    public int empty() {
        int value_to_return = insertedSoFar;
        insertedSoFar = 0;
        return value_to_return;
    }

    private void reset() { // insertedSoFar is only cleared when machine is emptied
        timeBought = 0;
        coinsIn.put(5, 0);
        coinsIn.put(10, 0);
        coinsIn.put(25, 0);
    }
}



