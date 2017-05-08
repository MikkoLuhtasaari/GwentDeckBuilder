package mikkoluhtasaari.gwentdeckbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import mikkoluhtasaari.gwentdeckbuilder.LoadClasses.LoadOther;

/**
 * Shows "main menu" from which user can select different factions.
 *
 * @author Mikko Luhtasaari
 * @version 1.0, 25 Apr 2017
 * @since 1.0
 */
public class SelectFactionActivity extends AppCompatActivity {

    /**
     * Faction specific url parts.
     */
    public static final String[] factionUrls = new String[6];

    /**
     * Contains neutral cards.
     */
    ArrayList<Card> neutralCards;

    /**
     * Creates the view.
     *
     * @param savedInstanceState Bundle savedInstanceState
     */
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

        neutralCards.clear();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getSerializable("neutralCards") != null) {
                neutralCards = (ArrayList<Card>) extras.getSerializable("neutralCards");
            }
        }
        System.out.println("Neutrals size "+neutralCards.size());

    }

    /**
     * Opens loader with monster url
     *
     * @param view
     */
    public void openMonsters(View view) {
        Intent intent = new Intent(view.getContext(), LoadOther.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.putExtra("factionUrl", factionUrls[0]);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    /**
     * Opens loader with nilgaard url
     *
     * @param view
     */
    public void openNilfgaard(View view) {
        Intent intent = new Intent(view.getContext(), LoadOther.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.putExtra("factionUrl", factionUrls[2]);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    /**
     * Opens loader with NR url
     *
     * @param view
     */
    public void openNorthernRealms(View view) {
        Intent intent = new Intent(view.getContext(), LoadOther.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.putExtra("factionUrl", factionUrls[3]);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    /**
     * Opens loader with Scoiatel url
     *
     * @param view
     */
    public void openScoiatel(View view) {
        Intent intent = new Intent(view.getContext(), LoadOther.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.putExtra("factionUrl", factionUrls[4]);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    /**
     * Opens loader with Skellige url
     *
     * @param view
     */
    public void openSkellige(View view) {
        Intent intent = new Intent(view.getContext(), LoadOther.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.putExtra("factionUrl", factionUrls[5]);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    /**
     * Opens about activity
     *
     * @param view
     */
    public void openAbout(View view) {
        Intent intent = new Intent(view.getContext(), About.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

}
