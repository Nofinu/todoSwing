package org.example;

import org.example.layout.MainPageLayout;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame jFrame = new JFrame("Todo Application");
                jFrame.setSize(500,600);
                jFrame.setContentPane(new MainPageLayout().getMainPanel());
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setVisible(true);
            }
        });
    }
}