package me.masahito.nineteen;

import org.atilika.kuromoji.Token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Node {
    private final Token token;

    public Node(final Token token) {
        this.token = token;
    }

    public final int getReadingLength() {
        String reading = token.getReading();
        if(reading == null) {
            reading = zenkakuHiraganaToZenkakuKatakana(token.getSurfaceForm());
        }

        Pattern p = Pattern.compile("[^アイウエオカ-モヤユヨラ-ロワヲンヴー]");
        Matcher m = p.matcher(reading);
        return m.replaceAll("").length();
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
}
