package mikkoluhtasaari.gwentdeckbuilder.CreateDecks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import mikkoluhtasaari.gwentdeckbuilder.Card;
import mikkoluhtasaari.gwentdeckbuilder.R;

public class CreateScoiatelDeck extends AppCompatActivity {

    ArrayList<Card> neutralCards;
    ArrayList<Card> scoiatelCards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_scoiatel_deck);

        neutralCards = new ArrayList<>();
        scoiatelCards = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getSerializable("neutralCards") != null) {
                neutralCards = (ArrayList<Card>) extras.getSerializable("neutralCards");
            }
            if(extras.getSerializable("scoiatelCards") != null) {
                scoiatelCards = (ArrayList<Card>) extras.getSerializable("scoiatelCards");
            }
        }
        System.out.println("neutral size " + neutralCards.size());
        System.out.println("scoiatel size " +scoiatelCards.size());
    }
}
