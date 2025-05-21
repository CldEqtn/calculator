package com.calculadora.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame  {
    private int width = 800;
    private int height = 600;

    public MainWindow(){
        JFrame frame = new JFrame("teste");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setTitle("Calculadora");
        this.setLayout(new GridLayout(4,5));

        this.initializeComponents();

        this.setVisible(true);
    }

    public void initializeComponents(){
        // create buttons with the matrix's names
        String[][] buttonNames = {
                {"(", ")", "^", "+"},
                {"7","8","9","-"},
                {"4","5","6","*"},
                {"1","2","3","/"},
                {"0",",","%","="}};

        for(int i = 0; i < buttonNames.length; i++){
            for (int j = 0; j < 4; j++) {
                createButton(buttonNames[i][j]);
                j++;
            }
            i++;
        }
    }

    private JButton createButton(String nome) {
        JButton btn = new JButton(nome);
        return btn;
    }
}
