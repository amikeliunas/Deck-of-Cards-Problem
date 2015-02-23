package com.example.adrian.carddeck.util;

import com.example.adrian.carddeck.model.Card;

import java.util.Set;


public interface IShuffler {
    public static final int DECK_SIZE = 52;
    public Set<Card> shuffle();
    public Set<Card> shuffle(Set<Card> source);
}
