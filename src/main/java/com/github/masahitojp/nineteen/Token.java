package com.github.masahitojp.nineteen;

import com.github.masahitojp.nineteen.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Token {
    private final org.atilika.kuromoji.Token token;
    private final String[] feature;

    public Token(final org.atilika.kuromoji.Token token) {
        this.token = token;
        this.feature = token.getAllFeaturesArray();
    }

    private Integer pronunciationLength;

    public final synchronized int getPronunciationLength() {
        if (this.pronunciationLength == null) {
            String reading = pronunciation();
            if (reading.equals("")) {
                final String surfaceForm = token.getSurfaceForm();
                if (surfaceForm != null && !StringUtils.isPureAscii(surfaceForm)) {
                    reading = zenkakuHiraganaToZenkakuKatakana(token.getSurfaceForm());
                }
            }

            Pattern p = Pattern.compile("[^アイウエオカ-モヤユヨラ-ロワヲンヴー]");
            Matcher m = p.matcher(reading);
            this.pronunciationLength = m.replaceAll("").length();
        }
        return pronunciationLength;
    }

    private static List<String> types = Arrays.asList("助詞", "助動詞");
    private static List<String> subtype1s = Arrays.asList("非自立", "接尾");
    private static List<String> rootForms = Arrays.asList("する", "できる");

    boolean firstOfPhrase() {
        if (types.contains(this.type())) {
            return false;
        } else if (subtype1s.contains(this.subtype1())) {
            return false;
        } else if (this.subtype1().equals("自立") && rootForms.contains(this.rootForm()))
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

    boolean elementOfIkku() {
        return this.token.isKnown();
    }

    boolean notElementOfIkku() {
        return this.token.isUnknown();
    }

    boolean lastOfPhrase() {
        return !type().equals("接頭詞");
    }

    private List<String> lastOfIkkuTypes = Arrays.asList("名詞接続", "格助詞", "係助詞", "連体化", "接続助詞", "並立助詞", "副詞化", "数接続", "連体詞");

    boolean lastOfIkku() {
        if (lastOfIkkuTypes.contains(type())) {
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
        if (this.feature.length >= 9) {
            return this.feature[8];
        }
        return "";
    }

    // http://www7a.biglobe.ne.jp/~java-master/samples/string/ZenkakuHiraganaToZenkakuKatakana.html
    private String zenkakuHiraganaToZenkakuKatakana(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c >= 'ぁ' && c <= 'ん') {
                sb.setCharAt(i, (char) (c - 'ぁ' + 'ァ'));
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return token.getSurfaceForm();
    }
}
