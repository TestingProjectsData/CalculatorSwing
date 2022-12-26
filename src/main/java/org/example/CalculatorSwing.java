package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorSwing {
    private JLabel display;
    // użytkownik zaczyna pisać w kalkulatorze
    private boolean start;
    private String equalSign;
    private double result;

    public CalculatorSwing() {


        start = true;
        equalSign = "=";
        JFrame frame = new JFrame();
        Color dispColor = new Color(177, 244, 255);
        Color bordCol = new Color(6, 33, 48);

        Border border = BorderFactory.createLineBorder(bordCol, 4);
        display = new JLabel("0", JLabel.RIGHT);
        display.setOpaque(true);
        display.setBackground(dispColor);
        display.setBorder(border);
        display.setFont(new Font("Arial", Font.BOLD, 44));
        frame.add(display, BorderLayout.NORTH);
        frame.setLocationRelativeTo(null);
        frame.add(makeButtonsPanel(), BorderLayout.CENTER);
        frame.setSize(300, 300);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    private JPanel makeButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 5));

        String buttons[] = {
                "7", "8", "9", "/", "%",
                "4", "5", "6", "*", "^",
                "1", "2", "3", "-", "!",
                "0", ".", "=", "+", ""
        };

        for (int i = 0; i < buttons.length; i++) {
            panel.add(addButton(buttons[i]));
        }

        return panel;
    }

    private JButton addButton(String name) {
        JGradientButton b = new JGradientButton (name, Color.WHITE, Color.CYAN.darker());
        b.setFont(new Font("Arial", Font.BOLD, 24));
        b.addActionListener(calcListener);

        return b;
    }


    private ActionListener calcListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String v = ((JButton) e.getSource()).getText();
            if ("/+-*=%^!".indexOf(v) >= 0) {
                calculate(v);
            } else {
                insertNumber(v);
            }
        }
    };

    public void calculate(String s) {
        System.out.println("calculate: " + s);

        double num = Double.parseDouble(display.getText());
        if (equalSign.equals("=")) result = num;
        if (equalSign.equals("+")) result += num;
        if (equalSign.equals("*")) result *= num;
        if (equalSign.equals("-")) result -= num;
        if (equalSign.equals("/")) result /= num;
        if (equalSign.equals("^")) result = Math.sqrt(num);
        if (equalSign.equals("!")) result = Math.pow(num, 2D);
        if (equalSign.equals("%")) result = num / 100;

        display.setText("" + result);
        equalSign = s;
        start = true;
    }

    public void insertNumber(String s) {
        //sprawdzamy czy użytkownik zaczyna psiać cos w kalk. jeżeli tak to ustawiamy pusty string
        if (start) {
            display.setText("");
            // dajemy false, żeby użytkownik mógł wpisywać wiecej znaków
            start = false;
        }
        System.out.println("insertNumber: " + s);
        display.setText(display.getText() + s);
    }

    public static void main(String[] args) {
        CalculatorSwing calc = new CalculatorSwing();
        System.out.println(Math.sqrt(9));
    }
}