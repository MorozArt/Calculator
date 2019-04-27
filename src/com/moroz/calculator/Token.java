package com.moroz.calculator;

public class Token {

    private char kind;
    private double value;

    public Token () {
        this.kind = ' ';
        this.value = 0;
    }

    public Token (char kind, double value) {
        this.kind = kind;
        this.value = value;
    }

    public static Token getToken(StringBuilder input) throws CalculateException {
        Token token = new Token();
        char symbol = input.charAt(0);
        switch (symbol) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '(':
            case ')':
                token.setKind(symbol);
                input.deleteCharAt(0);
                break;
            default:
                if(Character.isDigit(symbol)) {
                    boolean b = true;
                    StringBuilder sb = new StringBuilder();
                    sb.append(symbol);
                    int i = 1;
                    if(input.length()>1) {
                        symbol = input.charAt(1);
                        while (((Character.isDigit(symbol)) || (symbol == '.')) && i<input.length()) {
                            sb.append(symbol);
                            ++i;
                            if(i<input.length()) {
                                symbol = input.charAt(i);
                            } else {
                                break;
                            }
                        }
                    }

                    token.setKind('d');
                    token.setValue(Double.parseDouble(sb.toString()));
                    input.delete(0, i);
                } else {
                    throw new CalculateException("Ошибка! Неверный символ(ы).");
                }
                break;
        }
        return token;
    }

    public char getKind() {
        return kind;
    }

    public void setKind(char kind) {
        this.kind = kind;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

