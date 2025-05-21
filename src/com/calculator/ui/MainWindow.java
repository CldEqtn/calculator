package com.calculator.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame  {
    private int width = 800;
    private int height = 600;

    JFrame frame = new JFrame("Calculator");
    JPanel panelDisplay = new JPanel(new BorderLayout());
    JPanel panelButtons = new JPanel(new GridLayout(5, 4));

    public MainWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setTitle("Calculator");
        this.setLayout(new BorderLayout());

        this.initializeComponents();

        this.setVisible(true);
    }

    public void initializeComponents(){
        // create the display
        JTextField display = new JTextField();
        panelDisplay.add(display, BorderLayout.CENTER);
        panelDisplay.setPreferredSize(new Dimension(frame.getWidth(), height / 5));


        // create buttons with the matrix's strings
        String[][] buttonText = {
                {"(", ")", "^", "+"},
                {"7","8","9","-"},
                {"4","5","6","*"},
                {"1","2","3","/"},
                {"0",".","%","="}};

        JButton[][] buttons = {};

        for(int i = 0; i < buttonText.length; i++){
            for (int j = 0; j < 4; j++) {
                JButton btn = createButton(buttonText[i][j]);
                panelButtons.add(btn);
            }
        }


        this.add(panelDisplay, BorderLayout.NORTH);
        this.add(panelButtons, BorderLayout.CENTER);
    }


    private JButton createButton(String nome) {
        JButton btn = new JButton(nome);
        return btn;
    }
}
