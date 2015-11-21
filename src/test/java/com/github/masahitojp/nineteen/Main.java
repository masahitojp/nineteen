package com.github.masahitojp.nineteen;

import com.atilika.kuromoji.unidic.Tokenizer;

public class Main {
    public static void main(String[] args) {
        final Tokenizer tokenizer = new Tokenizer.Builder().build();
        for (final com.atilika.kuromoji.unidic.Token token : tokenizer.tokenize("二二二二二二二二二二二二二二二二ノ")) {
            System.out.println(token.getSurface() + "\t" + token.getAllFeatures());
        }
    }
}
