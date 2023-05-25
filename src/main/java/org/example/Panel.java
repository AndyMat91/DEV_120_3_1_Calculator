package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class Panel extends JPanel implements ActionListener {
    private JButton numbers[] = new JButton[10];
    private Font font = new Font("Impact", Font.BOLD, 20);
    private JTextField out = new JTextField();
    private JButton backspace = new JButton("C"), equ = new JButton("="), point = new JButton(".");
    private JButton plus = new JButton("+"), minus = new JButton("-"), multi = new JButton("*"), div = new JButton("/");
    private int a, b;
    private char c;
    private String text = "";
    private float p, p2;
    private int z;

    public Panel() {
        setLayout(null);
        setFocusable(true);
        grabFocus();

        backspace.setBounds(10, 250, 50, 50);
        backspace.setFont(font);
        add(backspace);
        backspace.addActionListener(this);

        equ.setBounds(10, 310, 230, 50);
        equ.setFont(font);
        add(equ);
        equ.addActionListener(this);

        plus.setBounds(190, 70, 50, 50);
        plus.setFont(font);
        add(plus);
        plus.addActionListener(this);

        minus.setBounds(190, 130, 50, 50);
        minus.setFont(font);
        add(minus);
        minus.addActionListener(this);

        multi.setBounds(190, 190, 50, 50);
        multi.setFont(font);
        add(multi);
        multi.addActionListener(this);

        div.setBounds(190, 250, 50, 50);
        div.setFont(font);
        add(div);
        div.addActionListener(this);

        point.setBounds(130, 250, 50, 50);
        point.setFont(font);
        add(point);
        point.addActionListener(this);

        numbers[0] = new JButton("0");
        numbers[0].setBounds(70, 250, 50, 50);
        numbers[0].setFont(font);
        numbers[0].addActionListener(this);
        add(numbers[0]);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                numbers[x * 3 + y + 1] = new JButton((x * 3 + y + 1) + "");
                numbers[x * 3 + y + 1].setBounds(y * (50 + 10) + 10, x * (50 + 10) + 70, 50, 50);
                add(numbers[x * 3 + y + 1]).setFont(font);
                numbers[x * 3 + y + 1].addActionListener(this);
            }
        }
        out.setBounds(10, 10, 230, 50);
        out.setEditable(false);
        out.setFont(font);
        add(out);

        // Попытка сделать ввод с клавиатуры. Работает, но частично.

       /* addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char symbol = e.getKeyChar();

                if (!Character.isDigit(symbol))
                    return;
                text += symbol;
                out.setText(text);
            }
        });*/
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton number : numbers) {
            if (e.getSource() == number) {
                JButton b = (JButton) e.getSource();
                text += b.getText();
                out.setText(text);
            }
        }

        if (e.getSource() == backspace) {
            text = "";
            out.setText(text);
            c = ' ';
            a = b = 0;
            p = p2 = 0;
        }

        if (!Objects.equals(out.getText(), "")) {
            if (e.getSource() == plus) {
                if (out.getText().contains(".")) {
                    p = Float.parseFloat(out.getText());
                    z = 1;
                } else {
                    a = Integer.parseInt(out.getText());
                    z = 0;
                }
                out.setText("");
                text = ("");
                c = '+';
            }

            if (e.getSource() == minus) {
                if (out.getText().contains(".")) {
                    p = Float.parseFloat(out.getText());
                    z = 1;
                } else {
                    a = Integer.parseInt(out.getText());
                    z = 0;
                }
                out.setText("");
                text = ("");
                c = '-';
            }

            if (e.getSource() == multi) {
                if (out.getText().contains(".")) {
                    p = Float.parseFloat(out.getText());
                    z = 1;
                } else {
                    a = Integer.parseInt(out.getText());
                    z = 0;
                }
                out.setText("");
                text = ("");
                c = '*';
            }

            if (e.getSource() == div) {
                if (out.getText().contains(".")) {
                    p = Float.parseFloat(out.getText());
                    z = 1;
                } else {
                    a = Integer.parseInt(out.getText());
                    z = 0;
                }
                out.setText("");
                text = ("");
                c = '/';
            }

            if (e.getSource() == point) {
                text += point.getText();
                out.setText(text);
            }

            if (e.getSource() == equ) {
                if (c == '+') {
                    if (z == 1 || text.contains(".")) {
                        p2 = Float.parseFloat(text);
                        out.setText(String.format("%.1f", (z == 1 ? p : a) + p2).replace(',', '.'));
                    } else {
                        b = Integer.parseInt(text);
                        out.setText(String.valueOf(a + b));
                    }
                }
                if (c == '-') {
                    if (z == 1 || text.contains(".")) {
                        p2 = Float.parseFloat(text);
                        out.setText(String.format("%.1f", (z == 1 ? p : a) - p2).replace(',', '.'));
                    } else {
                        b = Integer.parseInt(text);
                        out.setText(String.valueOf(a - b));
                    }
                }
                if (c == '*') {
                    if (z == 1 || text.contains(".")) {
                        p2 = Float.parseFloat(text);
                        out.setText(String.format("%.1f", (z == 1 ? p : a) * p2).replace(',', '.'));
                    } else {
                        b = Integer.parseInt(text);
                        out.setText(String.valueOf(a * b));
                    }
                }

                if (c == '/') {
                    try {
                        if (z == 1 || text.contains(".")) {
                            p2 = Float.parseFloat(text);
                            out.setText(String.format("%.1f", (z == 1 ? p : a) / p2).replace(',', '.'));
                        } else {
                            b = Integer.parseInt(text);
                            out.setText(String.valueOf(a / b));
                        }
                    } catch (ArithmeticException r) {
                        out.setText("0000");
                    }
                }
            }
        }
    }
}

