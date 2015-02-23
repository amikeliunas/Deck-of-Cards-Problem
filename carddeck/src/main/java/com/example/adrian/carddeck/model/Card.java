package com.example.adrian.carddeck.model;

import java.io.Serializable;

public class Card implements Serializable, Comparable<Card> {

    private final Suit mSuit;
    private final Value mValue;

    public Card(Suit suit, Value value) {
        this.mSuit = suit;
        this.mValue = value;
    }

    @Override
    public int compareTo(Card another) {
        int valueDiff = mValue.getIndex() - another.getValue().getIndex();
        int suitDiff = mSuit.getIndex() - another.getSuit().getIndex();
        return valueDiff != 0 ? valueDiff : suitDiff;
    }

    public Suit getSuit() {
        return mSuit;
    }

    public Value getValue() {
        return mValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (mSuit != card.mSuit) return false;
        if (mValue != card.mValue) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mSuit.hashCode();
        result = 31 * result + mValue.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return mValue.name() + " " + mSuit.name();
    }

    public String toImgSource() {
        return mValue.getIconPrefix()+ mSuit.getIconSuffix();
    }

    public Integer toIndex() {
        return mSuit.getIndex() * Value.values().length + mValue.getIndex();
    }
}
