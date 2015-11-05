package com.github.masahitojp.nineteen;

import com.atilika.kuromoji.unidic.Tokenizer;

import java.util.List;
import java.util.stream.Collectors;

public final class Parser {
    protected static Tokenizer tokenizer = new Tokenizer();

    public static List<Token> parse(final String text) {
        return tokenizer.tokenize(text).stream().map(Token::new)
                .collect(Collectors.toList());
    }
}
