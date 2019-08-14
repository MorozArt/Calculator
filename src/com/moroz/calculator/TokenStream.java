package com.moroz.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class TokenStream {

    private Deque<Token> tokens = new ArrayDeque<>();

    TokenStream (StringBuilder input) throws CalculateException {
        while (input.length() != 0) {
            Token token = Token.getToken(input);
            tokens.add(token);
        }
    }

    public Token getToken() {
        Token token;
        if(!tokens.isEmpty()) {
            token = tokens.poll();
            return token;
        } else {
            token = new Token('x',0);
            return token;
        }
    }

    public void putback(Token token) {
        tokens.addFirst(token);
    }

    public int getSize() { return tokens.size(); }
}

