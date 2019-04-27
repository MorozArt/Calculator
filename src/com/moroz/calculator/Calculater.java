package com.moroz.calculator;

public class Calculater {

    private TokenStream tokenStream;

    public double calculate(StringBuilder input) throws CalculateException {
        tokenStream = new TokenStream(input);
        double result = expression();
        if(tokenStream.getToken().getKind() == 'x') {
            return result;
        } else {
            throw new CalculateException("Ошибка!");
        }
    }

    private double expression() throws CalculateException {
        double left = term();
        Token token = tokenStream.getToken();
        while (true) {
            switch (token.getKind()) {
                case '+':
                    left += term();
                    token = tokenStream.getToken();
                    break;
                case '-':
                    left -= term();
                    token = tokenStream.getToken();
                    break;
                default:
                    tokenStream.putback(token);
                    return left;
            }
        }
    }

    private double term() throws CalculateException {
        double left = primary();
        Token token = tokenStream.getToken();
        while (true) {
            switch (token.getKind()) {
                case '*':
                    left *= primary();
                    token = tokenStream.getToken();
                    break;
                case '/':
                    double d = primary();
                    if (d == 0) {
                        throw new CalculateException("Ошибка! Деление на ноль.");
                    }
                    left /= d;
                    token = tokenStream.getToken();
                    break;
                default:
                    tokenStream.putback(token);
                    return left;
            }
        }
    }

    private double primary() throws CalculateException {
        Token token = tokenStream.getToken();
        switch (token.getKind()) {
            case '(':
                double d = expression();
                token = tokenStream.getToken();
                if(token.getKind() != ')') {
                    throw new CalculateException("Ошибка! Нет закрывающей ).");
                }
                return d;
            case 'd':
                return token.getValue();
            case '-':
                token = tokenStream.getToken();
                if(token.getKind() != 'd') {
                    throw new CalculateException("Ошибка!");
                }
                return -token.getValue();
            default:
                throw new CalculateException("Ошибка!");
        }
    }
}

