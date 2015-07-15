package com.github.masahitojp.nineteen;

import org.atilika.kuromoji.Tokenizer;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    protected static Tokenizer tokenizer = Tokenizer.builder().build();

    public static List<Node> parse(final String text) {
        return tokenizer.tokenize(text).stream().map(Node::new)
                .collect(Collectors.toList());
    }
}
