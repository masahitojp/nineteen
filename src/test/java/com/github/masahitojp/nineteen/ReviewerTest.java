package com.github.masahitojp.nineteen;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.is;

public class ReviewerTest {

    public String toTestString(final Optional<Song> songOpt) {
        return songOpt.map(song -> song.getPhrases().stream()
                .map(list -> list.stream().map(Token::toString).collect(Collectors.joining()))
                .collect(Collectors.joining(" "))).orElse("");
    }

    @Test
    public void judge() {
        final Reviewer reviewer = new Reviewer();
        final String haiku1 = "古池や蛙飛び込む水の音";
        final String haiku2 = "柿食えば鐘が鳴るなり法隆寺";
        final String haiku3 = "カピバラのご飯はいつも魚だね";
        final String haiku4 = "かいせきをしてるのでじしょしだいです";

        // 判定されないもの
        final String haiku10 = "次世代かっぴはtypoを";
        final String haiku11 = "/zc?k=201511";
        final String notHaiku = "毎日が日曜日";
        final String haiku12 = "二二二二二二二二二二二二二二二二ノ";

        assertThat(reviewer.judge(haiku1), is(true));
        assertThat(reviewer.judge(haiku2), is(true));
        assertThat(reviewer.judge(haiku3), is(true));
        assertThat(reviewer.judge(haiku4), is(true));
        assertThat(reviewer.judge(haiku10), is(false));
        assertThat(reviewer.judge(haiku11), is(false));
        assertThat(reviewer.judge(notHaiku), is(false));
        assertThat(reviewer.judge(haiku12), is(false));
    }

    @Test
    public void find() {
        final Reviewer reviewer = new Reviewer();
        final String haiku1 = "ああ古池や蛙飛び込む水の音";

        assertThat(toTestString(reviewer.find(haiku1)), is("古池や 蛙飛び込む 水の音"));
        assertThat(toTestString(reviewer.find("コーヒーの飲み方いつも同じだな")), is("コーヒーの 飲み方いつも 同じだな"));
        assertThat(toTestString(reviewer.find("心配事があるとなにも手につかなくなるのどうしたらいい？ — 座禅でもしてみるとか")), is("なにも手に つかなくなるの どうしたら"));


    }

}