package com.github.masahitojp.nineteen;

import java.util.List;

public class Song {
    final List<Node> nodes;
    final boolean exactly;
    List<List<Node>> phrases;

    public Song(List<Node> nodes, boolean exactly) {
        this.nodes = nodes;
        this.exactly = exactly;
    }

    public boolean valid() {
        return this.getPhrases() != null;
    }

    public synchronized List<List<Node>> getPhrases() {
        if (this.phrases == null){
            this.phrases = new Scanner(this.nodes, this.exactly).scan();
        }
        return this.phrases;
    }
}
