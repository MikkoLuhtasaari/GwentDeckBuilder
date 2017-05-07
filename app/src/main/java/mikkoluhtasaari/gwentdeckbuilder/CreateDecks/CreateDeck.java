package mikkoluhtasaari.gwentdeckbuilder.CreateDecks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import mikkoluhtasaari.gwentdeckbuilder.Card;
import mikkoluhtasaari.gwentdeckbuilder.CardAdapter;
import mikkoluhtasaari.gwentdeckbuilder.R;
import mikkoluhtasaari.gwentdeckbuilder.SelectFactionActivity;

public class CreateDeck extends AppCompatActivity {

    ArrayList<Card> neutralCards;
    ArrayList<Card> avaibleCards;
    ArrayList<Card> deckCards;
    private RecyclerView avaibleCardsView;
    private CardAdapter avaibleCardsAdapter;

    private RecyclerView deckCardsView;
    private CardAdapter deckCardsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deck);

        neutralCards = new ArrayList<>();
        deckCards = new ArrayList<>();
        avaibleCards = new ArrayList<>();

        // Find decks from intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getSerializable("neutralCards") != null) {
                neutralCards = (ArrayList<Card>) extras.getSerializable("neutralCards");
                avaibleCards = (ArrayList<Card>) extras.getSerializable("neutralCards");
            }
            if(extras.getSerializable("otherCards") != null) {
                avaibleCards.addAll((ArrayList<Card>) extras.getSerializable("otherCards"));
            }
        }
        System.out.println(avaibleCards.size());

        // Create view for avaible cards
        avaibleCardsView = (RecyclerView) findViewById(R.id.avaibleCards);
        avaibleCardsAdapter = new CardAdapter(avaibleCards);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        avaibleCardsView.setLayoutManager(mLayoutManager);
        avaibleCardsView.setItemAnimator(new DefaultItemAnimator());

        // Create and add divider to avaibleCardsView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(avaibleCardsView.getContext(), DividerItemDecoration.VERTICAL);
        avaibleCardsView.addItemDecoration(dividerItemDecoration);
        avaibleCardsView.setAdapter(avaibleCardsAdapter);

        deckCardsView = (RecyclerView) findViewById(R.id.deck);
        deckCardsAdapter = new CardAdapter(deckCards);
        RecyclerView.LayoutManager deckLayoutManager = new LinearLayoutManager(getApplicationContext());
        deckCardsView.setLayoutManager(deckLayoutManager);
        deckCardsView.setItemAnimator(new DefaultItemAnimator());

        // Create and add divider to avaibleCardsView
        DividerItemDecoration deckDividerItemDecoration = new DividerItemDecoration(deckCardsView.getContext(), DividerItemDecoration.VERTICAL);
        deckCardsView.addItemDecoration(deckDividerItemDecoration);
        deckCardsView.setAdapter(deckCardsAdapter);
    }

    // Return to SelectFactionActivity and send neutralCards with intent
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this.getApplicationContext(), SelectFactionActivity.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(intent);
    }
}
