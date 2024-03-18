package com.wcobq.ankibot.Anki.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Data
@SuperBuilder
public class Quiz {
    private String ruWord;
    private List<String> engWords;

    public void addRandomWord(List<String> words) {
        engWords.addAll(words.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Random random1 = new Random();
                Random random2 = new Random();
                return random2.nextInt() - random1.nextInt();
            }
        }).toList());
    }
}
