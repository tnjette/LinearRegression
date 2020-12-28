package com.tylerjette;

import java.util.*;

public class LinearRegression {
    /***/

    /**
     *
     * This class provides linear regression analysis of a given HashMap representing a data set
     * with two parameters (observed x and observed y) using gradient descent, and has a method to
     * return the optimal values for hypothesized parameters theta0 and theta1, representing m and b,
     * in slope-intercept form y = mx + b.
     *
     * **/

    /**methods:
     *
     *      1. perform analysis
     *
     *      2. set Learning Rate
     *
     *      3. perform gradient Descent
     *
     *      4. retrieve optimal values for theta 0 and theta 1
     *
     * **/

    /**member variables**/
    private HashMap<Double, Double> dataSet;
    private double learningRate;
    private Double hypothesisSlope = null;
    private Double hypothesisIntercept = null;

    /**constructor**/
    public LinearRegression(HashMap<Double, Double> dataSet){
        this.dataSet = dataSet;
        this.learningRate = 0.01;
    }

    /**
     * @param - double instance - to update the hypothesis intercept (Theta-0)
     * @return - void
     * **/
    public void setHypothesisIntercept(double updatedIntercept){
        this.hypothesisIntercept = updatedIntercept;
    }
    /**
     * @param - double instance - to update the hypothesis slope (Theta-1)
     * @return - void
     * **/
    public void setHypothesisSlope(double updatedSlope){
        this.hypothesisSlope = updatedSlope;
    }

    /**
     * @param - double instance - new learning rate (Alpha variable)
     * @return - void
     * **/
    public void setLearningRate(double update){
        this.learningRate = update;
    }

    /**
     * @param - none
     * @return - double instance of this.learning rate
     * **/
    public double getLearningRate(){
        return this.learningRate;
    }

    /**
     * @param - none
     * @return - true if the analysis takes place, otherwise false, if the parameters haven't yet been initialized
     * **/
    public boolean analyze(){

        if((this.hypothesisSlope != null) && (this.hypothesisIntercept != null)){
            //run the analysis

            //given the existing parameters of theta 0 (intercept) and theta 1 (slope)
            //calculate the error

            //double cost = evaluateCost();

            //gradient descent
            //repeat until convergence of the cost based on param 0 and param 1
            //while(/**thetas are not at 0*/this.hypothesisSlope!= 0.0  && this.hypothesisIntercept )
            double d1 = evaluteFirstPartialDerivative();
            double d2 = evaluteSecondPartialDerivative();
            System.out.println("d1 : " + d1);
            System.out.println("d2 : " + d2);

            for(int i = 0; i < 10000; i++){
                double temp0 = this.hypothesisIntercept - (this.learningRate * evaluteFirstPartialDerivative());
                double temp1 = this.hypothesisSlope - (this.learningRate * evaluteSecondPartialDerivative());
                System.out.println("slope : " + this.hypothesisSlope);
                System.out.println("intercept : " + this.hypothesisIntercept);

                setHypothesisIntercept(temp0);
                setHypothesisSlope(temp1);


                System.out.println("d1 : " + evaluteFirstPartialDerivative());
                System.out.println("d2 : " + evaluteSecondPartialDerivative());
            }

            //while((d1 != 0.0) && (d2 != 0.0)){
//            for(int i = 0; i < 500; i++) {
//                double temp0 = this.hypothesisIntercept - (this.learningRate * evaluteFirstPartialDerivative());
//                double temp1 = this.hypothesisSlope - (this.learningRate * evaluteSecondPartialDerivative());
//
//                System.out.println("theta0 : " + temp0);
//                System.out.println("theta1 : " + temp1);
//
//                setHypothesisIntercept(temp0);
//                setHypothesisSlope(temp1);
//
//                //reset thetas
//                d1 = evaluteFirstPartialDerivative();
//                d2 = evaluteSecondPartialDerivative();
//
//                System.out.println("d1 : " + d1);
//                System.out.println("d2 : " + d2);
//            }

            //}

            return true;
        }else{
            return false;
        }
    }

    public double evaluteFirstPartialDerivative(){
        double sumCost = 0.0;
        Iterator it = this.dataSet.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            double x = (double)entry.getKey();

            /**first partial derivative is calculated as
             * (the Sum of all hypothesized output values(i) - observed values(i))
             * **/
            double d = (this.hypothesisIntercept + (this.hypothesisSlope * x)) - (double) entry.getValue();
            sumCost += d;
        }

        double derivative = (1.0 / this.dataSet.size()) * sumCost;

        return derivative;
    }

    public double evaluteSecondPartialDerivative(){

        double sumCost = 0.0;
        Iterator it = this.dataSet.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            double x = (double)entry.getKey();

            /**second partial derivative is calculated as
             * (the Sum of all hypothesized output values(i) - observed values(i)) * x(i)
             * **/
            double d = ((this.hypothesisIntercept + (this.hypothesisSlope * x)) - (double) entry.getValue()) * x;
            sumCost += d;
        }

        double derivative = (1.0 / this.dataSet.size()) * sumCost;

        return derivative;
    }

    public double evaluateCost(){
        //return the sum of the squared differences between the hypothesized output
        // (provided parameters (theta 0 and theta 1)), and the observed data
        double sumCost = 0.0;
        //iterate across the dataset
        Iterator it = this.dataSet.entrySet().iterator();

        /**loop through this.dataSet, and per entry, calculates the cost (difference) of the
         * hypothesized value, relative to the range (Y-value) of the data entry**/
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            double x = (double)entry.getKey();

            /**calculate the difference
             * Hypothesis function of parameters (theta0 and theta1) - Training set y value
             * (Hypotesized-intercept + hypothesized-slope * x) - dataset entry y-value
             */
            double diff = (this.hypothesisIntercept + (this.hypothesisSlope * x)) - (double) entry.getValue();
            /**difference-squared**/
            double diffSquared = Math.pow(diff, 2);

            /**add to the sum of differences-squared**/
            sumCost+= diffSquared;
        }

        double cost = (1.0 / (2.0 * this.dataSet.size())) * sumCost;

        return cost;

    }

    /**
     * @param - none
     * @return - AbstractMap.SimpleEntry<Double, Double> instance containing this.optimalSlope,
     *           and this.optimalIntercept.
     * **/
    public AbstractMap.SimpleEntry<Double, Double> getOptimum(){
        if((this.hypothesisIntercept != null) && (this.hypothesisSlope != null)){
            return new AbstractMap.SimpleEntry<>(this.hypothesisSlope, this.hypothesisIntercept);
        }else{
            //throw new NullPointerException(); ?
            return null;
        }
    }

    public HashMap<Double, Double> getDataSet(){
        return this.dataSet;
    }

}
