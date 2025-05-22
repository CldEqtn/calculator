package com.calculator;

import com.calculator.controller.MainController;
import com.calculator.view.CalculatorView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        CalculatorView view = new CalculatorView();
        SwingUtilities.invokeLater(() -> view.setVisible(true));
        MainController controller = new MainController(view);
    }
}