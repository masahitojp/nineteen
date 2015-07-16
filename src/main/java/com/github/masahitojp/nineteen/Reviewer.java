package com.github.masahitojp.nineteen;

import java.util.List;
import java.util.Optional;

public class Reviewer {
    /**
     * check text
     * @param text check
     * @return [true, false]
     */
    public boolean judge(final String text) {
        final Song song = new Song(Parser.parse(text), true);
        return song.valid();
    }

    /**
     * find Senryu style
     * @param text check
     * @return Song
     */
    public Optional<Song> find(final String text) {

        List<Token> tokens = Parser.parse(text);
        final int nodeSize = tokens.size();
        for (int i = 0; i < nodeSize; i++) {
            final List<Token> current = tokens.subList(i, nodeSize);
            final Song song = new Song(current, false);
            if (song.valid()) {
                return Optional.of(song);
            }
        }
        return Optional.empty();
    }
}
