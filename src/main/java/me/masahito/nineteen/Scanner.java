package me.masahito.nineteen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scanner {

    private int count;
    private final List<Integer> rule = Arrays.asList(5, 7, 5);

    final List<Node> nodes;
    final boolean exactly;

    public Scanner(List<Node> nodes, boolean exactly) {
        this.count = 0;
        this.nodes = nodes;
        this.exactly = exactly;
    }

    public List<List<Node>> scan() {
        final List<List<Node>> phrases = new ArrayList<>();
        if (hasValidFirstNode()) {
            for(final Node node: this.nodes) {
                if (consume(node, phrases)) {
                    if (satisfied(phrases)) {
                        if (!this.exactly) {
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

    private boolean consume(final Node node, final List<List<Node>> phrases) {
        if (node.getReadingLength() > maxConsumableLength()) {
            return false;
        } else {
            if (phrases.size() <=this.phraseIndex()) {
                phrases.add(new ArrayList<>());
            }
            phrases.get(this.phraseIndex()).add(node);
            this.count += node.getReadingLength();
            return true;
        }
    }

    private int maxConsumableLength() {
        return rule.subList(0, this.phraseIndex() + 1).stream().mapToInt(z -> z).sum() - count;
    }

    private int phraseIndex() {
        int result = rule.size() -1;
        for (int i = 0 ;i <rule.size(); i++) {
            if( this.count < rule.subList(0, i + 1).stream().mapToInt(z -> z).sum()) {
                result = i;
                break;
            }
        }

        return result;
    }

    boolean satisfied(final List<List<Node>> phrases) {
        return hasFullCount() && hasValidLastNode(phrases);
    }

    private  boolean hasFullCount() {
        return this.count == 17;
    }
    private boolean hasValidFirstNode() {
        if (this.nodes.size() > 0) {
            return this.nodes.get(0).firstOfIkku();
        } else {
            return false;
        }
    }

    private boolean  hasValidLastNode(final List<List<Node>> phrases) {
        if (phrases.size() > 0) {
            final int size = Math.max(0, phrases.size() - 1);
            final int last = Math.max(0, phrases.get(size).size() -1);
            return phrases.get(size).get(last).lastOfIkku();
        } else {
            return false;
        }
    }

}
