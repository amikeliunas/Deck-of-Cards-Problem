package com.example.adrian.carddeck.util;

import com.example.adrian.carddeck.model.Deck;

public class DeckFactory {

    public Deck buildRandomShuffledDeck() {
        return new Deck(new RandomShuffler());
    }
}
