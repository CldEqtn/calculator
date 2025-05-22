package com.calculator.view;

import com.calculator.controller.MainController;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame  {
    private int width = 400;
    private int height = 600;

    private Font buttonsFont = new Font("Poppins",Font.PLAIN, 30);
    private Font displayFont = buttonsFont.deriveFont(45f);

    JButton[][] buttons;

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
        buttons = new JButton[5][4];


        // create the display
        JTextField display = new JTextField();
        panelDisplay.add(display, BorderLayout.CENTER);
        panelDisplay.setPreferredSize(new Dimension(frame.getWidth(), height / 5)); // changes txtfield size
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(displayFont);
        //TODO: make the display change its size dynamically

        // create buttons with the matrix's strings
        String[][] buttonText = {
                {"(", ")", "^", "+"},
                {"7","8","9","-"},
                {"4","5","6","*"},
                {"1","2","3","/"},
                {"0",".","C","="}};


        for(int i = 0; i < buttonText.length; i++){
            for (int j = 0; j < 4; j++) {
                JButton btn = createButton(buttonText[i][j]);
                buttons[i][j] = btn;
                btn.setFont(buttonsFont);

                panelButtons.add(btn);
            }
        }

        this.add(panelDisplay, BorderLayout.NORTH);
        this.add(panelButtons, BorderLayout.CENTER);
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    private JButton createButton(String label) {
        JButton btn = new JButton(label);
        return btn;
    }

}
