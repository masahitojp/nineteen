package me.masahito.nineteen;

import java.util.List;

public class Song {
    final List<Node> nodes;
    final boolean exactly;


    public Song(List<Node> nodes, boolean exactly) {
        this.nodes = nodes;
        this.exactly = exactly;
    }

    public boolean valid() {
        return nodes.stream().mapToInt(Node::getReadingLength).sum() ==17;
        //return new Scanner(this.nodes, this.exactly).scan() != null;
    }
}
