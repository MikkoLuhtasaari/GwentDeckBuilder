package mikkoluhtasaari.gwentdeckbuilder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import mikkoluhtasaari.gwentdeckbuilder.LoadClasses.LoadMonsters;
import mikkoluhtasaari.gwentdeckbuilder.LoadClasses.LoadNilfgaard;
import mikkoluhtasaari.gwentdeckbuilder.LoadClasses.LoadNorthernRealms;

public class SelectFactionActivity extends AppCompatActivity {

    public static final String baseUrl = "https://api.gwentapi.com/v0";
    public static final String[] factionUrls = new String[6];
    public static final String offset = "?limit=200&offset=0";
    ArrayList<Card> neutralCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        neutralCards = new ArrayList<>();
        //Monsters
        factionUrls[0] = "9aD4AoKlRhqKxQ2b7F0JOA";

        //Neutrals
        factionUrls[1] = "C21SnrUdSSW7ttfGNkOzeA";

        //Nilfgaard
        factionUrls[2] = "DuOHJOMpTWSevegxs7xOKQ";

        //NR
        factionUrls[3] = "sa1zdVBdST6FqFJUY1vIcQ";

        //Scoiatel
        factionUrls[4] = "vVL5p_u6SRmotqThkahITA";

        //Skellige
        factionUrls[5] = "wkY8HZJATUKd_EtraBoC3A";

        setContentView(R.layout.activity_select_faction);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getSerializable("neutralCards") != null) {
                neutralCards = (ArrayList<Card>) extras.getSerializable("neutralCards");
            }
        }
        System.out.println("Neutrals size "+neutralCards.size());

    }

    public void openMonsters(View view) {
        Intent intent = new Intent(view.getContext(), LoadMonsters.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    public void openNilfgaard(View view) {
        Intent intent = new Intent(view.getContext(), LoadNilfgaard.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    public void openNorthernRealms(View view) {
        Intent intent = new Intent(view.getContext(), LoadNorthernRealms.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    public void openScoiatel(View view) {
        System.out.println("open ST");
    }

    public void openSkellige(View view) {
        System.out.println("open SK");
    }

}
