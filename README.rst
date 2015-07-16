nineteen
========

Find a Senryu from text for Java SE 8.
Inspired by ruby/ikku.

Getting Started
---------------

Write simple java application
+++++++++++++++++++++++++++++

```java
import java.util.Optional;
import java.util.stream.Collectors;
import com.github.masahitojp.nineteen.*;

public class Main {
    public static String toSenryuString(final Optional<Song> songOpt) {
        return songOpt.map(song -> song.getPhrases().stream()
                .map(list -> list.stream().map(Token::toString).collect(Collectors.joining()))
                .collect(Collectors.joining(" "))).orElse("");
    }

    public static void main(String[] args) {
        final Reviewer reviewer = new Reviewer();
        final String haiku1 = "古池や蛙飛び込む水の音";

        System.out.println(toSenryuString(reviewer.find(haiku1)));
        // -> "古池や 蛙飛び込む 水の音"
    }
}
```

Add dependency to your build.gradle
+++++++++++++++++++++++++++++++++++

```groovy
apply plugin: 'java'

repositories.mavenCentral()

dependencies {
	compile 'com.github.masahitojp:nineteen:0.0.2.0'
}

sourceCompatibility = targetCompatibility = 1.8
```

License
-------

Apache License, Version 2.0

Inspired projects
-----------------

* https://github.com/r7kamura/ikku


Badges
======

.. image:: https://circleci.com/gh/masahitojp/nineteen.svg?circle-token=cb7eaa23c994dc2fc9a27fdf2996cd7ec7bd587c
   :alt: build status