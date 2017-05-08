package mikkoluhtasaari.gwentdeckbuilder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

/**
 * User can create decks in this activity. It's no where near finished.
 *
 * @author Mikko Luhtasaari
 * @version 1.0, 04 May 2017
 * @since 1.0
 */
public class CreateDeck extends AppCompatActivity {

    /**
     * Contains neutral cards.
     */
    ArrayList<Card> neutralCards;

    /**
     * Contains avaible cards to create deck from.
     */
    ArrayList<Card> avaibleCards;

    /**
     * Contains the cards that the user has in deck.
     */
    ArrayList<Card> deckCards;

    /**
     * Contains avaible cards.
     */
    private RecyclerView avaibleCardsView;

    /**
     * Makes it possible to show avaible cards as recycler list.
     */
    private CardAdapter avaibleCardsAdapter;

    /**
     * Contains deck cards.
     */
    private RecyclerView deckCardsView;

    /**
     * Makes it possible to show deck cards as recycler list.
     */
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

    /**
     * Max amount of cards for a deck.
     */
    private final int maxCards = 40;

    /**
     * Max amount of silver cards for a deck.
     */
    private final int maxSilvers = 6;

    /**
     * Max amount of gold cards for a deck.
     */
    private final int maxGolden = 4;

    /**
     * How many silver cards user currently has in deck.
     */
    private int silvers = 0;

    /**
     * How many gold cards user currently has in deck.
     */
    private int goldens = 0;


    /**
     * Creates the view
     *
     * @param savedInstanceState Bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deck);

        neutralCards = new ArrayList<>();
        deckCards = new ArrayList<>();
        avaibleCards = new ArrayList<>();

        // Find cards from intent extras
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

        // Create view for deck cards
        deckCardsView = (RecyclerView) findViewById(R.id.deck);
        deckCardsAdapter = new CardAdapter(deckCards);
        RecyclerView.LayoutManager deckLayoutManager = new LinearLayoutManager(getApplicationContext());
        deckCardsView.setLayoutManager(deckLayoutManager);
        deckCardsView.setItemAnimator(new DefaultItemAnimator());

        // Create and add divider to deck cards
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
                displayDetails(avaibleCards.get(position), view);
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
                displayDetails(deckCards.get(position), view);
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

            // Case golden card
            if (card.getGroup().getName().equalsIgnoreCase("gold")) {

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

                        // If the deck is going to contain 3 copies of that card remove it from avaible cards
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

    /**
     * Removes card from users deck and adds it to avaible cards if needed.
     *
     * @param card Card to be procecced
     */
    private void removeCard(Card card) {

        if (card.getGroup().getName().equalsIgnoreCase("gold")) {
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
            // If card is bronze check if it needs to be added to avaible cards.
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

    /**
     * Displays details about card.
     *
     * @param card Card to be shown
     * @param view View to which show details
     */
    public void displayDetails(Card card, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        // Use stringbuilder for better performance.
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + card.getName() + "\n\n");
        sb.append("Info: " + card.getInfo() + "\n\n");
        sb.append("Positions: ");
        for(String position: card.getPositions()) {
            sb.append(position+", ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("\n\n");
        sb.append("Group: " + card.getGroup().getName() + "\n\n");
        sb.append("Flavor: " + card.getFlavor());
        builder.setMessage(sb.toString());
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

    /**
     * Creates simple dialog with message and one button.
     *
     * @param message Message to be shown.
     * @param view View to which show warning.
     */
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

    /**
     * Overrides normal back button in order to keep neutral cards in case if activity
     * is killed by android device
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this.getApplicationContext(), SelectFactionActivity.class);
        intent.putExtra("neutralCards", neutralCards);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(intent);
    }
}