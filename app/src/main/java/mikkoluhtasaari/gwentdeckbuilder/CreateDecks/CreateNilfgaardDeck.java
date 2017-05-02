package mikkoluhtasaari.gwentdeckbuilder.CreateDecks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import mikkoluhtasaari.gwentdeckbuilder.Card;
import mikkoluhtasaari.gwentdeckbuilder.R;
import mikkoluhtasaari.gwentdeckbuilder.SelectFactionActivity;

public class CreateNilfgaardDeck extends AppCompatActivity {

    ArrayList<Card> neutralCards;
    ArrayList<Card> nilfgaardCards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_nilfgaard_deck);

        neutralCards = new ArrayList<>();
        nilfgaardCards = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getSerializable("neutralCards") != null) {
                neutralCards = (ArrayList<Card>) extras.getSerializable("neutralCards");
            }
            if(extras.getSerializable("nilfgaardCards") != null) {
                nilfgaardCards = (ArrayList<Card>) extras.getSerializable("nilfgaardCards");
            }
        }
        System.out.println("neutral size " + neutralCards.size());
        System.out.println("nilfgaard size " +nilfgaardCards.size());
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this.getApplicationContext(), SelectFactionActivity.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(intent);
    }
}
