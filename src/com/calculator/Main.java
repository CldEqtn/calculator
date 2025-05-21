package com.calculator;

import com.calculator.ui.MainWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow());
    }
}
