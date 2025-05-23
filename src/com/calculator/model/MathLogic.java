package com.calculator.model;

import java.util.ArrayList;

public class MathLogic {
    private String operator;

    private int beforeOperatorIndex;
    private int operatorIndex;
    private int afterOperatorIndex;

    private String[] expressionArray;
    private ArrayList<String> onGoingExpressionArrayList = new ArrayList<>();



    public String equalsTo(String expression){
        String results = "";
        //TODO: check for parenthesis first, then multiplication or division

        // separates everything around the operators
        expressionArray = expression.split("(?<=[+\\-*/()])|(?=[+\\-*/()])");

        for (int i = 0; i < expressionArray.length; i++) {
            onGoingExpressionArrayList.add(i, expressionArray[i]);
        }

        while (onGoingExpressionArrayList.contains("*") || onGoingExpressionArrayList.contains("/")){
            calculate("*/");
        }
        while (onGoingExpressionArrayList.contains("+") || onGoingExpressionArrayList.contains("-")){
            calculate("+-");
        }
        System.out.println("size of arraylist is " + onGoingExpressionArrayList.size());


        for (int i = 0; i < onGoingExpressionArrayList.size(); i++) {
            System.out.println(onGoingExpressionArrayList.get(i));
        }


        return results;
    }

    public double calculate(String operators){
        double calculus = 0;


        // finds the values around the chosen operator
        double value1 = Double.parseDouble(onGoingExpressionArrayList.get(locateCalc(operators) - 1));
        double value2 = Double.parseDouble(onGoingExpressionArrayList.get(locateCalc(operators) + 1));

        // calculate
        switch (operator) {
            case "+":
                calculus = value1 + value2;
                break;
            case "-":
                calculus = value1 - value2;
                break;
            case "*":
                calculus = value1 * value2;
                break;
            case "/":
                calculus = value1 / value2;
                break;
        }


        onGoingExpressionArrayList.remove(afterOperatorIndex);
        onGoingExpressionArrayList.remove(operatorIndex);
        onGoingExpressionArrayList.remove(beforeOperatorIndex);

        onGoingExpressionArrayList.add(beforeOperatorIndex, String.valueOf(calculus));

        // TODO: reset when creating a new expression

        return calculus;
    }


    public int locateCalc(String operators){
        String operator1 = "";
        String operator2 = "";
        if (operators.equals("*/")){
            operator1 = "*";
            operator2 = "/";
        }

        if (operators.equals("+-")){
            operator1 = "+";
            operator2 = "-";
        }

        // will find the INDEX of the number before and after, so it can calculate later.
        for (int i = 0; i < onGoingExpressionArrayList.size(); i++){
            if (onGoingExpressionArrayList.get(i).equals(operator1) || onGoingExpressionArrayList.get(i).equals(operator2)){
                operator = onGoingExpressionArrayList.get(i);

                operatorIndex = i;

                beforeOperatorIndex = i - 1;
                afterOperatorIndex = i + 1;
            }
        }
        return operatorIndex;
        //TODO: a loop that will search for more of the operator, until it is done
    }
    public int locateCalc(String operator1, String operator2){
        // will find the INDEX of the number before and after, so it can calculate later.
        for (int i = 0; i < expressionArray.length; i++){
            if (expressionArray[i].equals(operator1) || expressionArray[i].equals(operator2)){
                operatorIndex = i;

                beforeOperatorIndex = i - 1;
                afterOperatorIndex = i + 1;
            }
        }
        return operatorIndex;
        //TODO: a loop that will search for more of the operator, until it is done
    }



//    public int countOcurrences(String[] expressionArray, String operator){
//
//    }
}
