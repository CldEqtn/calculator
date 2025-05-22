package com.calculator.view;


import javax.swing.*;
import java.awt.*;

public class CalculatorView extends JFrame  {
    private final int width = 400;
    private final int height = 600;

    private final Font buttonsFont = new Font("Poppins",Font.PLAIN, 30);
    private final Font displayFont = buttonsFont.deriveFont(45f);

    JTextField display;
    JButton[][] buttons;

    JFrame frame = new JFrame("Calculator");
    JPanel panelDisplay = new JPanel(new BorderLayout());
    JPanel panelButtons = new JPanel(new GridLayout(5, 4));

    public CalculatorView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setTitle("Calculator");
        this.setLayout(new BorderLayout());

        this.initializeComponents();

        this.setVisible(true);
    }



    public void initializeComponents(){
        buttons = new JButton[5][4];

        //
        // create the display
        display = new JTextField();
        panelDisplay.add(display, BorderLayout.CENTER);
        panelDisplay.setPreferredSize(new Dimension(frame.getWidth(), height / 5)); // changes textfield size
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(displayFont);
        //TODO: last - make the display change its size dynamically


        //
        // create buttons with the matrix's strings
        String[][] buttonText = {
                {"(", ")", "C", "<-"},
                {"7","8","9","+"},
                {"4","5","6","-"},
                {"1","2","3","/"},
                {"0",".","*","="}};

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

    // getters for MainController usage
    public JButton[][] getButtons() {
        return buttons;
    }
    public JTextField getDisplay() {
        return display;
    }

    // this method was made for legibility purposes
    private JButton createButton(String label) {
        return new JButton(label);

    }

}
