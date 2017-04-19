package mikkoluhtasaari.gwentdeckbuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

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
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            neutralCards = (ArrayList<Card>) extras.getSerializable("neutralCards");
        }
        System.out.println("Neutrals size "+neutralCards.size());

    }

    public void openMonsters(View view) {
        //TODO new loading activity
    }

}
