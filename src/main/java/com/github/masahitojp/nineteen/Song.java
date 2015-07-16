package com.github.masahitojp.nineteen;

import java.util.List;

public class Song {
    final List<Token> tokens;
    final boolean exactly;
    List<List<Token>> phrases;

    public Song(List<Token> tokens, boolean exactly) {
        this.tokens = tokens;
        this.exactly = exactly;
    }

    public boolean valid() {
        return this.getPhrases() != null;
    }

    public synchronized List<List<Token>> getPhrases() {
        if (this.phrases == null) {
            this.phrases = new Scanner(this.tokens, this.exactly).scan();
        }
        return this.phrases;
    }
}
