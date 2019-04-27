package com.moroz.calculator;

import java.util.Vector;

public class TokenStream {

    private Vector<Token> tokens = new Vector<>();

    TokenStream (StringBuilder input) throws CalculateException {
        while (input.length() != 0) {
            Token token = Token.getToken(input);
            tokens.add(token);
        }
    }

    public Token getToken() {
        Token token;
        if(!tokens.isEmpty()) {
            token = tokens.firstElement();
            tokens.remove(0);
            return token;
        } else {
            token = new Token('x',0);
            return token;
        }
    }

    public void putback(Token token) {
        tokens.insertElementAt(token,0);
    }

    public int getSize() { return tokens.size(); }
}

