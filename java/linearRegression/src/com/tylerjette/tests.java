package com.tylerjette;

import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;

public class tests {

    @Test
    public void constructorTests(){

        HashMap<Double, Double> testData = new HashMap<>();
        testData.put(1.0,5.3);
        testData.put(2.0,4.2);
        testData.put(3.0,6.9);
        testData.put(4.0,8.1);
        testData.put(5.0,6.6);

        LinearRegression l = new LinearRegression(testData);

        Assert.assertTrue(testData.size() == 5);
        Assert.assertTrue(l.getDataSet().size() == 5);

    }

    @Test
    public void evaluateCostTest0(){
        HashMap<Double, Double> testData = new HashMap<>();
        testData.put(1.0,1.0);
        testData.put(2.0,2.0);
        testData.put(3.0,3.0);
        testData.put(4.0,4.0);
        testData.put(5.0,5.0);

        LinearRegression l = new LinearRegression(testData);

        l.setHypothesisIntercept(0.0);
        l.setHypothesisSlope(1.0);

        /**tests that the evaluateCost() function returns the expected value (0.0), given the above data set**/
        Assert.assertTrue(l.evaluateCost() == 0.0);

    }

    @Test
    public void evaluateCostTest1(){
        HashMap<Double, Double> testData = new HashMap<>();
        testData.put(1.0,2.0);
        testData.put(2.0,3.0);
        testData.put(3.0,4.0);
        testData.put(4.0,5.0);
        testData.put(5.0,6.0);

        LinearRegression l = new LinearRegression(testData);

        l.setHypothesisIntercept(0.0);
        l.setHypothesisSlope(1.0);

        /**tests that the evaluateCost() function returns the expected value (0.5), given the above data set**/
        Assert.assertTrue(l.evaluateCost() == 0.5);
    }

    @Test
    public void evaluateCostTest2(){
        HashMap<Double, Double> testData = new HashMap<>();
        testData.put(0.0,3.0);
        testData.put(5.0,2.5);
        testData.put(15.0,1.75);
        testData.put(25.0,4.0);
        testData.put(30.0,4.5);

        LinearRegression l = new LinearRegression(testData);

        l.setHypothesisIntercept(0.0);
        l.setHypothesisSlope(1.0);

        /**tests that the evaluateCost() function returns the expected value (128.20625), given the above data set**/
        Assert.assertTrue(l.evaluateCost() == 128.20625);
    }
}
