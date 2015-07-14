package me.masahito.nineteen;

import java.util.List;

/**
 * Created by masahito on 15/07/15.
 */
public class Scanner {
    final List<Node> nodes;
    final boolean exactly;

    public Scanner(List<Node> nodes, boolean exactly) {
        this.nodes = nodes;
        this.exactly = exactly;
    }

    public boolean scan() {
        return true;
    }
}
