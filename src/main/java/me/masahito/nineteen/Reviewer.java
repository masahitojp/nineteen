package me.masahito.nineteen;

import org.atilika.kuromoji.Tokenizer;

public class Reviewer {

    public boolean judge(final String text) {
        final Song song = new Song(Parser.parse(text), true);
        return song.valid();
    }

    public static void main(String[] args) {
        final Tokenizer tokenizer = Tokenizer.builder().build();
        final String haiku = "ちょっとまてちょっとまっておにいさーん";
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

    }
}