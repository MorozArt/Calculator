package com.moroz.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JButton button1 = new JButton("1");
    private JButton button2 = new JButton("2");
    private JButton button3 = new JButton("3");
    private JButton button4 = new JButton("4");
    private JButton button5 = new JButton("5");
    private JButton button6 = new JButton("6");
    private JButton button7 = new JButton("7");
    private JButton button8 = new JButton("8");
    private JButton button9 = new JButton("9");
    private JButton button0 = new JButton("0");
    private JButton plusButton = new JButton("+");
    private JButton subButton = new JButton("-");
    private JButton multiButton = new JButton("*");
    private JButton divButton = new JButton("/");
    private JButton clearButton = new JButton("C");
    private JButton bOpenButton = new JButton("(");
    private JButton bCloseButton = new JButton(")");
    private JButton eqButton = new JButton("=");
    private JButton dotButton = new JButton(".");
    private JButton stubButton = new JButton("");
    private JScrollPane textScrollPane = new JScrollPane();
    private JTextArea textArea = new JTextArea(25,27);
    private JTextField textInput = new JTextField(27);
    private Calculater calculater = new Calculater();

    public MainWindow() {
        super("Simple Example");
        this.setBounds(100,100,640,480);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setupUI();
    }

    class ButtonEventListener implements ActionListener {
        private char symbol;

        ButtonEventListener(char symbol) {
            this.symbol = symbol;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (symbol) {
                case 'C':
                    textArea.setText("");
                    break;
                case '=':
                    StringBuilder sb = new StringBuilder(textInput.getText());
                    if(!sb.toString().equals("")) {
                        textInput.setText("");
                        textArea.append("> "+sb+"\n");
                        textArea.append("Результат: ");
                        try {
                            textArea.append(String.valueOf(calculater.calculate(sb))+"\n");
                        } catch (CalculateException err) {
                            textArea.append(err.getMessage()+"\n");
                        }
                        break;
                    } else {
                        break;
                    }
                default:
                    textInput.setText(textInput.getText()+symbol);
                    break;
            }
        }
    }

    private void setupUI() {
        Font font = new Font("TimesRoman", Font.PLAIN, 30);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(1,2));

        JPanel buttonGrid = new JPanel(new GridLayout(7, 3, 5, 0) );
        button1.addActionListener(new ButtonEventListener('1'));
        button2.addActionListener(new ButtonEventListener('2'));
        button3.addActionListener(new ButtonEventListener('3'));
        button4.addActionListener(new ButtonEventListener('4'));
        button5.addActionListener(new ButtonEventListener('5'));
        button6.addActionListener(new ButtonEventListener('6'));
        button7.addActionListener(new ButtonEventListener('7'));
        button8.addActionListener(new ButtonEventListener('8'));
        button9.addActionListener(new ButtonEventListener('9'));
        button0.addActionListener(new ButtonEventListener('0'));
        plusButton.addActionListener(new ButtonEventListener('+'));
        subButton.addActionListener(new ButtonEventListener('-'));
        multiButton.addActionListener(new ButtonEventListener('*'));
        divButton.addActionListener(new ButtonEventListener('/'));
        bOpenButton.addActionListener(new ButtonEventListener('('));
        bCloseButton.addActionListener(new ButtonEventListener(')'));
        clearButton.addActionListener(new ButtonEventListener('C'));
        eqButton.addActionListener(new ButtonEventListener('='));
        dotButton.addActionListener(new ButtonEventListener('.'));
        stubButton.setVisible(false);
        textInput.addActionListener(new ButtonEventListener('='));

        button1.setFont(font);
        button2.setFont(font);
        button3.setFont(font);
        button4.setFont(font);
        button5.setFont(font);
        button6.setFont(font);
        button7.setFont(font);
        button8.setFont(font);
        button9.setFont(font);
        button0.setFont(font);
        plusButton.setFont(font);
        subButton.setFont(font);
        multiButton.setFont(font);
        divButton.setFont(font);
        bOpenButton.setFont(font);
        bCloseButton.setFont(font);
        eqButton.setFont(font);
        clearButton.setFont(font);
        dotButton.setFont(font);

        buttonGrid.add(button1);
        buttonGrid.add(button2);
        buttonGrid.add(button3);
        buttonGrid.add(button4);
        buttonGrid.add(button5);
        buttonGrid.add(button6);
        buttonGrid.add(button7);
        buttonGrid.add(button8);
        buttonGrid.add(button9);
        buttonGrid.add(plusButton);
        buttonGrid.add(button0);
        buttonGrid.add(subButton);
        buttonGrid.add(multiButton);
        buttonGrid.add(bOpenButton);
        buttonGrid.add(divButton);
        buttonGrid.add(eqButton);
        buttonGrid.add(bCloseButton);
        buttonGrid.add(clearButton);
        buttonGrid.add(stubButton);
        buttonGrid.add(dotButton);

        JPanel textPanel = new JPanel();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textScrollPane.setViewportView(textArea);

        textPanel.setLayout (new FlowLayout(FlowLayout.CENTER));
        textPanel.add(textInput);
        textPanel.add(textScrollPane);

        container.add(textPanel);
        container.add(buttonGrid);
    }
}

