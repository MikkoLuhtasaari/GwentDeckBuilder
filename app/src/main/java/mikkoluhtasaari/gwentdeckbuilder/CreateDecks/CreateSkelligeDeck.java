package mikkoluhtasaari.gwentdeckbuilder.CreateDecks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import mikkoluhtasaari.gwentdeckbuilder.Card;
import mikkoluhtasaari.gwentdeckbuilder.R;
import mikkoluhtasaari.gwentdeckbuilder.SelectFactionActivity;

public class CreateSkelligeDeck extends AppCompatActivity {

    ArrayList<Card> neutralCards;
    ArrayList<Card> skelligeCards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_skellige_deck);

        neutralCards = new ArrayList<>();
        skelligeCards = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getSerializable("neutralCards") != null) {
                neutralCards = (ArrayList<Card>) extras.getSerializable("neutralCards");
            }
            if(extras.getSerializable("skelligeCards") != null) {
                skelligeCards = (ArrayList<Card>) extras.getSerializable("skelligeCards");
            }
        }
        System.out.println("neutral size " + neutralCards.size());
        System.out.println("skellige size " +skelligeCards.size());
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this.getApplicationContext(), SelectFactionActivity.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(intent);
    }
}
