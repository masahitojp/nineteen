package com.github.masahitojp.nineteen;

import com.atilika.kuromoji.unidic.Tokenizer;

import java.util.List;
import java.util.stream.Collectors;

enum Parser {
    INSTANCE;
    private Tokenizer tokenizer = new Tokenizer();
    public List<Token> parse(final String text) {
        return tokenizer.tokenize(text).stream().map(Token::new)
                .collect(Collectors.toList());
    }
}
