package com.github.masahitojp.nineteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scanner {

    private int count;
    private final List<Integer> rule = Arrays.asList(5, 7, 5);
    private final int ruleFullCount = rule.stream().mapToInt(s -> s).sum();

    final List<Token> tokens;
    final boolean exactly;

    public Scanner(List<Token> tokens, boolean exactly) {
        this.count = 0;
        this.tokens = tokens;
        this.exactly = exactly;
    }

    public List<List<Token>> scan() {
        final List<List<Token>> phrases = new ArrayList<>();
        if (hasValidFirstNode()) {
            for (final Token token : this.tokens) {
                if (consume(token, phrases)) {
                    if (!this.exactly) {
                        if (satisfied(phrases)) {
                            return phrases;
                        }
                    }
                } else {
                    return null;
                }
            }
            if (this.satisfied(phrases)) {
                return phrases;
            }
            return null;
        } else {
            return null;
        }
    }

    private boolean consume(final Token token, final List<List<Token>> phrases) {
        if (token.getPronunciationLength() > maxConsumableLength()) {
            return false;
        } else if (!token.elementOfIkku()) {
            return false;
        } else if (Arrays.asList(5, 12, 17).contains(this.count) && !token.firstOfPhrase()) {
            return false;
        } else if (token.getPronunciationLength() == maxConsumableLength() && !token.lastOfPhrase()) {
            return false;
        } else {
            if (phrases.size() <= this.phraseIndex()) {
                phrases.add(new ArrayList<>());
            }
            phrases.get(this.phraseIndex()).add(token);
            this.count += token.getPronunciationLength();
            return true;
        }
    }

    private int maxConsumableLength() {
        return rule.subList(0, this.phraseIndex() + 1).stream().mapToInt(z -> z).sum() - count;
    }

    private int phraseIndex() {
        int result = rule.size() - 1;
        for (int i = 0; i < rule.size(); i++) {
            if (this.count < rule.subList(0, i + 1).stream().mapToInt(z -> z).sum()) {
                result = i;
                break;
            }
        }

        return result;
    }

    boolean satisfied(final List<List<Token>> phrases) {
        return hasFullCount() && hasValidLastNode(phrases);
    }

    private boolean hasFullCount() {
        return this.count == ruleFullCount;
    }

    private boolean hasValidFirstNode() {
        return this.tokens.size() > 0 && this.tokens.get(0).firstOfIkku();
    }

    private boolean hasValidLastNode(final List<List<Token>> phrases) {
        if (phrases.size() > 0) {
            final int size = Math.max(0, phrases.size() - 1);
            final int last = Math.max(0, phrases.get(size).size() - 1);
            return phrases.get(size).get(last).lastOfIkku();
        } else {
            return false;
        }
    }

}
