package com.github.masahitojp.nineteen;

import org.junit.Test;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class ReviewerTest {
    @Test
    public void judge() {
        final Reviewer reviewer = new Reviewer();
        final String haiku1 = "古池や蛙飛び込む水の音";
        final String haiku2 = "柿食えば鐘が鳴るなり法隆寺";
        final String notHaiku = "毎日が日曜日";
        assertThat(reviewer.judge(haiku1), is(true));
        assertThat(reviewer.judge(haiku2), is(true));
        assertThat(reviewer.judge(notHaiku), is(false));
    }

    @Test
    public void find() {
        final Reviewer reviewer = new Reviewer();
        final String haiku1 = "ああ古池や蛙飛び込む水の音";
        final Song song = reviewer.find(haiku1);

        final String result = song.getPhrases().stream().map(list -> list.stream().map(Node::toString).collect(Collectors.joining()))
                .collect(Collectors.joining(" "));

        assertThat(result, is("古池や 蛙飛び込む 水の音"));
    }

}