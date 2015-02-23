package com.example.adrian.carddeck.util;

import com.example.adrian.carddeck.model.Card;
import com.example.adrian.carddeck.model.Suit;
import com.example.adrian.carddeck.model.Value;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RandomShuffler implements IShuffler {

    @Override
    public Set<Card> shuffle() {
        List<Integer> indexList = cardRange();
        return makeShuffle(indexList);
    }

    private void shuffle(List<Integer> indexList) {
        SecureRandom rand = new SecureRandom(ByteBuffer
                .allocate(Long.SIZE / 8)
                .putLong(System.nanoTime())
                .array());
        for (int i = indexList.size() - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            swap(indexList, i, index);
        }
    }

    private List<Integer> cardRange() {
        List<Integer> indexList = new ArrayList<Integer>(DECK_SIZE);
        for (int i = 0; i < DECK_SIZE; i++) {
            indexList.add(i);
        }
        return indexList;
    }

    private void swap(List<Integer> indexList, int i, int index) {
        int tmp = indexList.get(index);
        indexList.add(index, indexList.get(i));
        indexList.add(i, tmp);
    }

    private Set<Card> makeShuffle(List<Integer> indexList) {
        for (int i = 0; i < 5; i++) shuffle(indexList);
        return FluentIterable
                .from(indexList)
                .transform(new Function<Integer, Card>() {
                    @Override
                    public Card apply(Integer input) {
                        return new Card(Suit.getSuitByIndex(input), Value.getValueByIndex(input));
                    }
                })
                .toSet();
    }

    @Override
    public Set<Card> shuffle(Set<Card> source) {
        List<Integer> indexList = Lists.newArrayList(Iterables.transform(source, new Function<Card, Integer>() {
            @Override
            public Integer apply(Card input) {
                return input.toIndex();
            }
        }));
        return makeShuffle(indexList);
    }
}
