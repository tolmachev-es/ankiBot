package org.wcobq.AnkiServ.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Data
@SuperBuilder
public class Quiz {
    private String ruWord;
    private List<String> engWords;

    public void addRandomWord(List<String> words) {
        this.engWords = new ArrayList<>();
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
