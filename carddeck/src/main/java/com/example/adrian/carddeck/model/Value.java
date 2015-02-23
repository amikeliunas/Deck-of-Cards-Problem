package com.example.adrian.carddeck.model;

public enum Value {
    TWO(0, "ic_2_of"), THREE(1, "ic_3_of"), FOUR(2, "ic_4_of"),FIVE(3, "ic_5_of"),
    SIX(4, "ic_6_of"), SEVEN(5, "ic_7_of"), EIGHT(6, "ic_8_of"), NINE(7, "ic_9_of"),
    TEN(8, "ic_10_of"), JACK(9, "jack_of"), QUEEN(10, "queen_of"), KING(11, "king_of"),
    ACE(12, "ace_of");

    private int mIndex;
    private String mIconPrefix;
    private static final int DELIMETER = values().length;

    private Value(int index, String iconPrefix) {
        this.mIndex = index;
        this.mIconPrefix = iconPrefix;
    }

    public int getIndex() {
        return mIndex;
    }

    public String getIconPrefix() {
        return mIconPrefix;
    }

    public static Value getValueByIndex(int index) {
        int remains = index % DELIMETER;
        switch (remains) {
            case 0: return TWO;
            case 1: return THREE;
            case 2: return FOUR;
            case 3: return FIVE;
            case 4: return SIX;
            case 5: return SEVEN;
            case 6: return EIGHT;
            case 7: return NINE;
            case 8: return TEN;
            case 9: return JACK;
            case 10: return QUEEN;
            case 11: return KING;
            case 12: return ACE;
            default: return ACE;
        }
    }
}
