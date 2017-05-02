package mikkoluhtasaari.gwentdeckbuilder.CreateDecks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import mikkoluhtasaari.gwentdeckbuilder.Card;
import mikkoluhtasaari.gwentdeckbuilder.R;

public class CreateMonsterDeck extends AppCompatActivity {

    ArrayList<Card> neutralCards;
    ArrayList<Card> monsterCards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_monster_deck);

        neutralCards = new ArrayList<>();
        monsterCards = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getSerializable("neutralCards") != null) {
                neutralCards = (ArrayList<Card>) extras.getSerializable("neutralCards");
            }
            if(extras.getSerializable("monsterCards") != null) {
                monsterCards = (ArrayList<Card>) extras.getSerializable("monsterCards");
            }
        }
        System.out.println("neutral size " + neutralCards.size());
        System.out.println("monster size " +monsterCards.size());
    }
}
