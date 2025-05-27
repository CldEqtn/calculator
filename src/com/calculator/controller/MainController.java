package com.calculator.controller;

import com.calculator.view.CalculatorView;
import com.calculator.model.MathLogic;

import javax.swing.*;

public class MainController {
    private final CalculatorView view;
    private final MathLogic mathLogic = new MathLogic();

    JButton[][] buttons;
    char lastChar = 0;

    public MainController(CalculatorView view) {
        this.view = view;
        initializeListeners();
    }

    public String getCurrentDisplayText() {
        return view.getDisplay().getText();
    }
    public JTextField getCurrentDisplay() {
        return view.getDisplay();
    }


    public void initializeListeners(){
        // gets all buttons labels and calls "handleButtons" with its labels
        buttons = view.getButtons();

        // normal for   =====> for (   int    i = 0            :          array)
        // enhanced for =====> for (DataType variable (will walk through) array)
        for (JButton[] button : buttons) {
            for (int j = 0; j < 4; j++) {
                String label = button[j].getText();

                button[j].addActionListener(e -> {
                    handleButtons(label);
                });
            }
        }
    }

    // writes on the display and calls the "equals" method, that gives the result
    public void handleButtons(String label){
        String displayText = getCurrentDisplayText();
        JTextField display = getCurrentDisplay();

        if (!displayText.isEmpty())
            lastChar = displayText.charAt(displayText.length() - 1);



        System.out.print(label + " "); // for testing

        // START OF SWITCH============
        switch (label){
            case "C": if (!displayText.isEmpty()) eraseAll(); break;

            case "<-": if (!displayText.isEmpty()) backspace(); break;

            case "+": case "-": case "*": case "/": case ".":
                if (!lastCharIsNumber()) break; // if the last char is a number, break
                display.setText(displayText + label); break; // if not, add it to the display, then break

            case "=":
                System.out.println(displayText); // testing purposes
                display.setText(mathLogic.equalsTo(displayText)); break;


            default:
                display.setText(displayText + label);

            // END OF SWITCH============
        }

    }

    //
    // erases the display
    public void eraseAll(){
        getCurrentDisplay().setText("");
        for (int i = 0; i < mathLogic.getOnGoingExpression().size(); i++)
            mathLogic.eraseLast();
    }
    // erases the last character
    public void backspace(){
        getCurrentDisplay().setText(
                getCurrentDisplayText().substring(0, getCurrentDisplayText().length() - 1)
        );
        mathLogic.eraseLast();
    }

    //
    // identifies the last pressed number, so the user don't add more than 1 operator or dot
    public boolean lastCharIsNumber(){

        char[] unrepeatable = {'+', '-', '*', '/', '.'};
        for (char c : unrepeatable) {
            if (lastChar == c) return false;
        }

        return true;
    }



}