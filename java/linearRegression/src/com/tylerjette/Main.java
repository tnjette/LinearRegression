package com.tylerjette;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        /**test hashmap**/

        HashMap<Double, Double> testData = new HashMap<>();
        testData.put(1.0,1.0);
        testData.put(2.0,2.0);
        testData.put(3.0,3.0);
        testData.put(4.0,4.0);
        testData.put(5.0,5.0);

        LinearRegression l0 = new LinearRegression(testData);

        l0.setHypothesisSlope(0);
        l0.setHypothesisIntercept(0);
        System.out.println(l0.analyze());

        System.out.println(l0.getOptimum());


    }
}
