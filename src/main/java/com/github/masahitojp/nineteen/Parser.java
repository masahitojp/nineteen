package com.github.masahitojp.nineteen;

import lombok.experimental.UtilityClass;
import org.atilika.kuromoji.Tokenizer;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class Parser {
    protected static Tokenizer tokenizer = Tokenizer.builder().build();

    public static List<Token> parse(final String text) {
        return tokenizer.tokenize(text).stream().map(Token::new)
                .collect(Collectors.toList());
    }
}
