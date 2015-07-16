package com.github.masahitojp.nineteen;

import org.atilika.kuromoji.Tokenizer;

import java.util.Arrays;
import java.util.List;

public class Reviewer {

    public boolean judge(final String text) {
        final Song song = new Song(Parser.parse(text), true);
        return song.valid();
    }

    public Song find(final String text) {

        List<Node> nodes = Parser.parse(text);
        final int nodeSize = nodes.size();
        for (int i = 0;i < nodeSize ;i++) {

            final List<Node> current = nodes.subList(i, nodeSize);
            final Song song = new Song(current, false);
            if (song.valid()) {
                return song;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        final Tokenizer tokenizer = Tokenizer.builder().build();
        final String haiku = "牡蠣食えば金が鳴るなり法隆寺";
        tokenizer
                .tokenize(haiku)
                .stream()
                .forEach((token) -> {
                    String[] features = token.getAllFeaturesArray();
                    System.out.println("##########################################################");
                    System.out.println("===メソッドで取得できる値");
                    System.out.println("表記  　：" + token.getSurfaceForm());
                    System.out.println("品詞  　：" + token.getPartOfSpeech());
                    System.out.println("原型　　：" + token.getBaseForm());
                    System.out.println("読み　　："+token.getReading());
                    System.out.println("既知語　：" + token.isKnown());
                    System.out.println("未知語　：" + token.isUnknown());
                    System.out.println("ユーザ辞書？："+ token.isUser());
                    System.out.println("すべてのfeature：" +token.getAllFeatures());
                    System.out.println("===token.getAllFeaturesArray()で取得できる配列の中身");
                    System.out.println("fearures[0] 品詞１　：" + features[0] );
                    System.out.println("fearures[1] 品詞２　：" + features[1]);
                    System.out.println("fearures[0] 品詞３　：" + features[2]);
                    System.out.println("fearures[0] 品詞４　：" + features[3]);
                    System.out.println("fearures[0] 活用形１：" + features[4]);
                    System.out.println("fearures[0] 活用形２：" + features[5]);
                    System.out.println("fearures[0] 原型　　：" + features[6]);
                    if (features.length == 9) {
                        System.out.println("fearures[0] 読み　　：" + features[7]);
                        System.out.println("fearures[0] 発音　　：" + features[8]);
                    }
                } )
                ;

        if( Arrays.asList("あいう", "えおか").contains("えおか")) {
            System.out.println("suc");

        } else {
            System.out.println("bbb");
        }
        final List<Integer> a =Arrays.asList(1,2,3).subList(0,1);
        int i = 2;
    }
    }
