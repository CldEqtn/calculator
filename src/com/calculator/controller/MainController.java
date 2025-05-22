package com.calculator.controller;

import com.calculator.view.MainWindow;

import javax.swing.*;

public class MainController {
    private final MainWindow window;

    public MainController(MainWindow window) {
        this.window = window;
        initializeListeners();
    }

    public void initializeListeners(){
        JButton[][] buttons = window.getButtons();

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < 4; j++) {
                String label = buttons[i][j].getText();
                buttons[i][j].addActionListener(e -> handleButtons(label));
            }
        }
    }
    public void handleButtons(String label){
        System.out.println("This is the button \""+ label +"\"");

    }



}
