package com.calculator;

import com.calculator.controller.MainController;
import com.calculator.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        SwingUtilities.invokeLater(() -> window.setVisible(true));
        MainController controller = new MainController(window);
    }
}