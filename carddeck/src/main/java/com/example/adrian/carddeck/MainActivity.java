package com.example.adrian.carddeck;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.adrian.carddeck.model.Card;
import com.example.adrian.carddeck.model.Deck;
import com.example.adrian.carddeck.util.DeckFactory;
import com.google.common.base.Optional;


public class MainActivity extends Activity {

    private DeckFactory mDeckFactory;
    private volatile Deck mDeck;

    private ProgressBar mWaitProgress;

    private class NewDeckTask extends AsyncTask<DeckFactory, Void, Deck> {

        @Override
        protected void onPreExecute() {
            mWaitProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Deck deck) {
            mWaitProgress.setVisibility(View.GONE);
            mDeck = deck;
        }

        @Override
        protected Deck doInBackground(DeckFactory... params) {
            return params[0].buildRandomShuffledDeck();
        }
    }

    private class ShuffleDeckTask extends AsyncTask<Deck, Void, Deck> {
        @Override
        protected void onPreExecute() {
            mWaitProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Deck deck) {
            mWaitProgress.setVisibility(View.GONE);
            mDeck = deck;
        }

        @Override
        protected Deck doInBackground(Deck... params) {
            return params[0].shuffle();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDeckFactory = new DeckFactory();
        mDeck = mDeckFactory.buildRandomShuffledDeck();

        mWaitProgress = (ProgressBar) findViewById(R.id.progressBar);
        final TextView outputTV = (TextView) findViewById(R.id.output);
        final ImageView cardImage = (ImageView) findViewById(R.id.card_image);
        View.OnClickListener nextCardClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pair<Optional<Card>, Deck> dealPair = mDeck.dealOneCard();
                mDeck = dealPair.second;
                if (dealPair.first.isPresent()) {
                    cardImage.setImageResource(getResources()
                            .getIdentifier(dealPair.first.get().toImgSource(), "drawable", getPackageName()));
                    outputTV.setText(dealPair.first.get().toString());
                } else {
                    cardImage.setImageResource(R.drawable.pomegranate);
                    outputTV.setText("No cards");
                }
            }
        };
        cardImage.setOnClickListener(nextCardClick);
        Button shuffleButton = (Button) findViewById(R.id.shuffle_button);
        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShuffleDeckTask().execute(mDeck);
                Log.d("CARDS", mDeck.getEntries().toString());
            }
        });
        final Button dealOneCardButton = (Button) findViewById(R.id.deal_one_card);
        dealOneCardButton.setOnClickListener(nextCardClick);
        Button newDeckButton = (Button) findViewById(R.id.new_deck);
        newDeckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new NewDeckTask().execute(mDeckFactory);
                cardImage.setImageResource(R.drawable.pomegranate);
                outputTV.setText("New deck");
            }
        });
    }

}