package com.calculator.model;

import com.calculator.Main;
import com.calculator.controller.MainController;

import java.util.ArrayList;

public class MathLogic {
    private String operator;

    char lastChar = 0;
    private int operatorIndex;

    private String[] splitExpression;
    private ArrayList<String> onGoingExpression = new ArrayList<>();
    private ArrayList<String> expressionParentheses = new ArrayList<>();

    // TODO: (12)2 -> multiplication

    // TODO:

    public String equalsTo(String expression){
        String results = "";
        if (expression.isEmpty())
            return results;

        // separates everything around the operators
        splitExpression = expression.split("(?<=[+\\-*/()])|(?=[+\\-*/()])");
        // pass everything from the splitExpression to the onGoingExpression
        for (int i = 0; i < splitExpression.length; i++) {
            onGoingExpression.add(i, splitExpression[i]);
        }
        if (!isValidExpression()){
            System.out.println("Expression is valid");
            return expression;
        }
        while (onGoingExpression.contains("(") && onGoingExpression.contains(")"))
            calculateParentheses();



        while (onGoingExpression.contains("*") || onGoingExpression.contains("/"))
            calculate("*/", onGoingExpression);

        while (onGoingExpression.contains("+") || onGoingExpression.contains("-"))
            calculate("+-", onGoingExpression);



        while (onGoingExpression.size() > 1)
            eraseLast();


        results = onGoingExpression.getFirst();
        return results;
    }


    private boolean isValidExpression() {
        // first, it checks if there is an even quantity of parentheses.
        int parenthesesModulus = (countOcurrences("(") + countOcurrences(")")) % 2;
        if (parenthesesModulus != 0){
            return false;
        }

        // then, if the last operator is fulfilled (1+1, instead of 1+), the expression is valid
        String[] operators = {"+", "-", "*", "/", "."};
        for (String op : operators) {
            if (onGoingExpression.getLast().equals(op))
                return false;
        }

        return true;
    }


    public void calculateParentheses(){
        int parenthesesStart = onGoingExpression.indexOf("(");
        int parenthesesEnd = onGoingExpression.indexOf(")");

        int i = 0;
        for (i = parenthesesStart; i <= parenthesesEnd; i++) {
            expressionParentheses.add(onGoingExpression.get(i));
        }



        // checks if there is a number before and after the parentheses without operators, (...)**
        boolean numberBeforeParentheses = false;
        if (!onGoingExpression.getFirst().equals("(") && parenthesesStart != 0)
            numberBeforeParentheses = onGoingExpression.get(parenthesesStart - 1).matches("[0-9]");

        boolean numberAfterParentheses = false;
        if (onGoingExpression.size() > expressionParentheses.size() && parenthesesEnd != onGoingExpression.size() - 1)
            numberAfterParentheses = onGoingExpression.get(parenthesesEnd + 1).matches("[0-9]");


        for (i = parenthesesEnd; i >= parenthesesStart; i--) {
            onGoingExpression.remove(i);
        }

        expressionParentheses.removeLast();
        expressionParentheses.removeFirst();

        calculate("*/", expressionParentheses);
        calculate("+-", expressionParentheses);

        onGoingExpression.add(parenthesesStart, expressionParentheses.getFirst());


        // **(...) and manually adds the multiplication operator, in case there is need.
        if (onGoingExpression.size() - 1 > parenthesesEnd && numberAfterParentheses){
            onGoingExpression.add(parenthesesEnd + 1, "*"); // TODO: FIX ERROR HERE (not removing parenthesis)
        }

        if (numberBeforeParentheses){
            onGoingExpression.add(parenthesesStart, "*"); // TODO: FIX ERROR HERE (not removing parenthesis)
        }

        expressionParentheses.clear();
    }


    public void calculate(String operators, ArrayList<String> expression){


        int calculationMiddle = locateCalc(operators, expression);

        if (calculationMiddle == -1){
            return;
        }
        int beforeOperatorIndex = calculationMiddle - 1;

        int afterOperatorIndex = calculationMiddle + 1;

        // finds the values around the chosen operator
        double value1 = Double.parseDouble(expression.get(beforeOperatorIndex));
        double value2 = Double.parseDouble(expression.get(afterOperatorIndex));
        double calculation = 0;

        // calculate
        switch (expression.get(operatorIndex)) {
            case "+":
                calculation = value1 + value2;
                break;
            case "-":
                calculation = value1 - value2;
                break;
            case "*":
                calculation = value1 * value2;
                break;
            case "/":
                calculation = value1 / value2;
                break;
        }



        // removes the operator and operands
        expression.remove(afterOperatorIndex);
        expression.remove(operatorIndex);
        expression.remove(beforeOperatorIndex);

        // and adds the value that has been calculated
        expression.add(beforeOperatorIndex, String.valueOf(calculation));
    }




    public void eraseLast(){
        for (int i = 0; i < onGoingExpression.size(); i++) {
            onGoingExpression.removeLast();
        }
    }


    public int countOcurrences(String operator){
        int count = 0;
        int i = 0;
        for (i = 0; i < onGoingExpression.size(); i++) {
            if (onGoingExpression.get(i).equals(operator)) {
                count++;
//                System.out.println("occurrence n "+ count +" for the character "+ operator +" at the position "+ i);
            }
        }
        return count;
    }


    public int locateCalc(String character, ArrayList<String> expression){
        String character1 = "";
        String character2 = "";

        switch (character){
            case "*/":
                character1 = "*";
                character2 = "/";
                break;
            case "+-":
                character1 = "+";
                character2 = "-";
                break;
        }

        operatorIndex = -1;
        // will find the INDEX of the number before and after, so it can calculate later.
        for (int i = 0; i < expression.size(); i++){
            if (expression.get(i).equals(character1) || expression.get(i).equals(character2)){
                operatorIndex = i;
                return operatorIndex;
            }
        }
        return operatorIndex;
    }

    public ArrayList<String> getOnGoingExpression() {
        return onGoingExpression;
    }

}
