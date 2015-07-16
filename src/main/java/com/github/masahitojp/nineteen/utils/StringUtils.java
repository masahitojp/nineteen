package com.github.masahitojp.nineteen.utils;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class StringUtils {

    static CharsetEncoder asciiEncoder =
            Charset.forName("US-ASCII").newEncoder(); // or "ISO-8859-1" for ISO Latin 1

    public static boolean isPureAscii(String v) {
        return asciiEncoder.canEncode(v);
    }
}