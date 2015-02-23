package com.example.adrian.carddeck.model;

public enum Suit {
    CLUBS(0, "_clubs"), DIAMONDS(1, "_diamonds"), HEARTS(2, "_hearts"), SPADES(3, "_spades");

    private int mIndex;
    private String mIconSuffix;
    private static final int DELIMETER = Value.values().length;

    private Suit(int index, String suffix) {
        mIndex = index;
        mIconSuffix = suffix;
    }

    public static Suit getSuitByIndex(int index) {
        int remains = index / DELIMETER;
        switch (remains) {
            case 0: return CLUBS;
            case 1: return DIAMONDS;
            case 2: return HEARTS;
            case 3: return SPADES;
            default: return CLUBS;
        }
    }

    public int getIndex() {
        return mIndex;
    }

    public String getIconSuffix() {
        return mIconSuffix;
    }
}
