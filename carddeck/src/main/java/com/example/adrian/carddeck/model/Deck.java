package com.example.adrian.carddeck.model;

import android.util.Pair;

import com.example.adrian.carddeck.util.IShuffler;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Queues;

import java.util.Queue;
import java.util.Set;

public class Deck {
    private final Queue<Card> mEntries;
    private final IShuffler mShuffleStrategy;

    public Deck(IShuffler mShuffleStrategy) {
        this.mEntries = Queues.newLinkedBlockingQueue(mShuffleStrategy.shuffle());
        this.mShuffleStrategy = mShuffleStrategy;
    }

    private Deck(Set<Card> entries, IShuffler shuffleStrategy) {
        this.mEntries = Queues.newLinkedBlockingQueue(entries);
        this.mShuffleStrategy = shuffleStrategy;
    }

    public Deck shuffle() {
        return new Deck(mShuffleStrategy.shuffle(ImmutableSet.copyOf(mEntries)), mShuffleStrategy);
    }

    public Queue<Card> getEntries() {
        return Queues.newLinkedBlockingQueue(mEntries);
    }

    public Pair<Optional<Card>, Deck> dealOneCard() {
        Optional<Card> card = Optional.fromNullable(mEntries.poll());
        return Pair.create(card, new Deck(ImmutableSet.copyOf(mEntries), mShuffleStrategy));
    }
}
