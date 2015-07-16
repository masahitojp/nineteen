package com.github.masahitojp.nineteen;

import org.atilika.kuromoji.Token;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Node {
    private final Token token;
    private final String[] feature;

    public Node(final Token token) {

        this.token = token;
        this.feature = token.getAllFeaturesArray();
    }

    public final int getReadingLength() {
        String reading = token.getReading();
        if (reading == null) {
            reading = zenkakuHiraganaToZenkakuKatakana(token.getSurfaceForm());
        }

        Pattern p = Pattern.compile("[^アイウエオカ-モヤユヨラ-ロワヲンヴー]");
        Matcher m = p.matcher(reading);
        return m.replaceAll("").length();
    }

    //
    private boolean firstOfPhrase() {
        if (Arrays.asList("助詞", "助動詞").contains(this.type())) {
            return false;
        } else if (Arrays.asList("非自立", "接尾").contains(this.subtype1())) {
            return false;
        } else if (this.subtype1().equals("自立") && Arrays.asList("する", "できる").contains(this.rootForm()))
            return false;
        else {
            return true;
        }
    }

    boolean firstOfIkku() {
        if (!firstOfPhrase()) {
            return false;
        } else if (this.type().equals("記号") && !Arrays.asList("括弧開", "括弧閉").contains(this.subtype1())) {
            return false;
        } else {
            return true;
        }
    }

    boolean lastOfPhrase() {
        return !type().equals("接頭詞");
    }

    boolean lastOfIkku() {
        if (Arrays.asList("名詞接続", "格助詞", "係助詞", "連体化", "接続助詞", "並立助詞", "副詞化", "数接続", "連体詞").contains(type())) {
            return false;
        } else if (conjugation2().equals("連用タ接続")) {
            return false;
        } else if (conjugation1().equals("サ変・スル") && conjugation2().equals("連用形")) {
            return false;
        } else if (type().equals("動詞") && Arrays.asList("仮定形", "未然形").contains(conjugation2())) {
            return false;
        } else if (type().equals("名詞") && subtype1().equals("非自立") && pronunciation().equals("ン")) {
            return false;
        } else {
            return true;
        }
    }

    private String type() {
        return this.feature[0];
    }

    private String subtype1() {
        return this.feature[1];
    }

    private String conjugation1() {
        return this.feature[4];
    }

    private String conjugation2() {
        return this.feature[5];
    }

    private String rootForm() {
        return this.feature[6];
    }

    private String pronunciation() {
        return this.feature[8];
    }

    // http://www7a.biglobe.ne.jp/~java-master/samples/string/ZenkakuHiraganaToZenkakuKatakana.html
    private String zenkakuHiraganaToZenkakuKatakana(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c >= 'ぁ' && c <= 'ん') {
                sb.setCharAt(i, (char)(c - 'ぁ' + 'ァ'));
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return token.getSurfaceForm();
    }
}
