package com.github.masahitojp.nineteen;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Song {
    final List<Token> tokens;
    final boolean exactly;
    List<List<Token>> phrases;

    public Song(final List<Token> tokens, final boolean exactly) {
        this.tokens = tokens;
        this.exactly = exactly;
    }

    public final boolean valid() {
        return this.getPhrases() != null;
    }

    public final synchronized List<List<Token>> getPhrases() {
        if (!checkSame()) {
            if (this.phrases == null) {
                this.phrases = new Scanner(this.tokens, this.exactly).scan();
            }
        } else {
            return null;
        }

        return this.phrases;
    }
    private boolean checkSame() {
        if (this.tokens.size() > 0) {
            final Map<String, Long> counted =
                    this.tokens.subList(1, Math.min(5, Math.max(0, this.tokens.size() - 1)))
                    .stream()
                    .collect(Collectors.groupingBy(Token::getSpeechLevel, Collectors.counting()));
            return Collections.max(counted.values()) > 3;
        } else {
            return false;
        }
    }
}
