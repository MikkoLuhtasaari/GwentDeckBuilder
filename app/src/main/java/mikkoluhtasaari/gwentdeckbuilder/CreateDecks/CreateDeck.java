package mikkoluhtasaari.gwentdeckbuilder.CreateDecks;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mikkoluhtasaari.gwentdeckbuilder.Card;
import mikkoluhtasaari.gwentdeckbuilder.CardAdapter;
import mikkoluhtasaari.gwentdeckbuilder.ClickListener;
import mikkoluhtasaari.gwentdeckbuilder.R;
import mikkoluhtasaari.gwentdeckbuilder.RecyclerTouchListener;
import mikkoluhtasaari.gwentdeckbuilder.SelectFactionActivity;

public class CreateDeck extends AppCompatActivity {

    ArrayList<Card> neutralCards;
    ArrayList<Card> avaibleCards;
    ArrayList<Card> deckCards;

    private RecyclerView avaibleCardsView;
    private CardAdapter avaibleCardsAdapter;

    private RecyclerView deckCardsView;
    private CardAdapter deckCardsAdapter;


    /**
     * Gwent has certain restrictions when it comes to deck building.
     * Deck cannot have more than 40 cards. Silver cards are limited to 6
     * and gold cards to 4. Deck can have 3 copies of bronze cards but only
     * one copy of gold and silver cards.
     * Deck must contain minimum of 25 cards but
     * since this is going to be a rough prototype that limit isn't going
     * to be factored in.
     */
    private final int maxCards = 40;
    private final int maxSilvers = 6;
    private final int maxGolden = 4;
    private int silvers = 0;
    private int goldens = 0;


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
                ArrayList<Card> temp = new ArrayList<>();
                temp = (ArrayList<Card>) extras.getSerializable("neutralCards");
                for(Card card: temp) {
                    avaibleCards.add(card);
                    neutralCards.add(card);
                }
            }

            if(extras.getSerializable("otherCards") != null) {
                System.out.println("neutraalit size " + neutralCards.size());
                ArrayList<Card> temp = new ArrayList<>();
                temp = (ArrayList<Card>) extras.getSerializable("otherCards");
                for(Card card: temp) {
                    avaibleCards.add(card);
                }
            }
        }

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

        // Click listener to avaible cards
        avaibleCardsView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), avaibleCardsView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                addCard(avaibleCards.get(position), view);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        // Click listener to deck
        deckCardsView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), deckCardsView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                removeCard(deckCards.get(position));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    /**
     * Tries to add the card to users deck.
     * Checks if the card can be added to deck.
     * Removes card from avaible cards list if needed
     *
     * @param card Card to be processed
     */
    private void addCard(Card card, View view) {

        if (deckCards.size() < maxCards) {
            System.out.println("Under max cards");

            if (card.getGroup().getName().equalsIgnoreCase("gold")) {
                System.out.println("Gold");

                if (goldens < maxGolden) {
                    if (deckCards.size() > 0) {
                        boolean temp = true;
                        for (Card tempCard : deckCards) {
                            if (tempCard.getName().equalsIgnoreCase(card.getName())) {
                                temp = false;
                            }
                        }

                        if (temp) {
                            deckCards.add(card);
                            avaibleCards.remove(card);
                            goldens++;
                            avaibleCardsAdapter.notifyDataSetChanged();
                            deckCardsAdapter.notifyDataSetChanged();
                        }
                    } else {
                        deckCards.add(card);
                        avaibleCards.remove(card);
                        goldens++;
                        avaibleCardsAdapter.notifyDataSetChanged();
                        deckCardsAdapter.notifyDataSetChanged();
                    }
                } else {
                    displayAlert("Your deck already contains 4 gold cards! Please remove one before continuing", view);
                }
            } else if (card.getGroup().getName().equalsIgnoreCase("silver")) {
                System.out.println("silver");

                if (silvers < maxSilvers) {
                    if (deckCards.size() > 0) {
                        boolean temp = true;
                        for (Card tempCard : deckCards) {
                            if (tempCard.getName().equalsIgnoreCase(card.getName())) {
                                temp = false;
                            }
                        }

                        if (temp) {
                            deckCards.add(card);
                            avaibleCards.remove(card);
                            silvers++;
                            avaibleCardsAdapter.notifyDataSetChanged();
                            deckCardsAdapter.notifyDataSetChanged();
                        }
                    } else {
                        deckCards.add(card);
                        avaibleCards.remove(card);
                        silvers++;
                        avaibleCardsAdapter.notifyDataSetChanged();
                        deckCardsAdapter.notifyDataSetChanged();
                    }
                } else {
                    displayAlert("Your deck already contains 6 silver cards! Please remove one before continuing", view);
                }
            } else {
                System.out.println("bronze");

                if (deckCards.size() > 0) {
                    int amount = 0;
                    for (Card tempCard : deckCards) {
                        if (tempCard.getName().equalsIgnoreCase(card.getName())) {
                            amount++;
                        }
                    }

                    if (amount < 3) {
                        deckCards.add(card);
                        deckCardsAdapter.notifyDataSetChanged();

                        if(amount == 2) {
                            avaibleCards.remove(card);
                            avaibleCardsAdapter.notifyDataSetChanged();
                        }
                    }
                } else {
                    deckCards.add(card);
                    deckCardsAdapter.notifyDataSetChanged();
                }
            }
        } else {
            displayAlert("Your deck already contains max amount of cards! Please remove one before continuing", view);
        }
    }

    private void removeCard(Card card) {

        if (card.getGroup().getName().equalsIgnoreCase("gold")) {
            System.out.println("gold");
            avaibleCards.add(card);
            deckCards.remove(card);
            avaibleCardsAdapter.notifyDataSetChanged();
            deckCardsAdapter.notifyDataSetChanged();
            goldens--;
        } else if (card.getGroup().getName().equalsIgnoreCase("silver")) {
            avaibleCards.add(card);
            deckCards.remove(card);
            avaibleCardsAdapter.notifyDataSetChanged();
            deckCardsAdapter.notifyDataSetChanged();
            silvers--;
        } else {
            deckCards.remove(card);
            deckCardsAdapter.notifyDataSetChanged();

            boolean temp = true;
            for(Card tempCard: avaibleCards) {

                if(tempCard.getName().equalsIgnoreCase(card.getName())) {
                    temp = false;
                }
            }

            if (temp) {
                avaibleCards.add(card);
                avaibleCardsAdapter.notifyDataSetChanged();
            }
        }
    }

    public void displayAlert(String message, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    // Return to SelectFactionActivity and send neutralCards with intent
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this.getApplicationContext(), SelectFactionActivity.class);
        System.out.println("neutraalit intent size " + neutralCards.size());
        intent.putExtra("neutralCards", neutralCards);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(intent);
    }
}