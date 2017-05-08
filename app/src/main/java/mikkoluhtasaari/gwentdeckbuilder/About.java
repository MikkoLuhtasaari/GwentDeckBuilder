package mikkoluhtasaari.gwentdeckbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Shows Copyright related messages and general info about app.
 *
 * @author Mikko Luhtasaari
 * @version 1.0, 05 May 2017
 * @since 1.0
 */
public class About extends AppCompatActivity {

    /**
     * Contains neutral cards which are kept throught the app.
     */
    ArrayList<Card> neutralCards;

    /**
     * Creates the view.
     *
     * @param savedInstanceState bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        neutralCards = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getSerializable("neutralCards") != null) {
                neutralCards = (ArrayList<Card>) extras.getSerializable("neutralCards");
            }
        }
    }

    /**
     * Overrides normal back button in order to keep neutral cards in case if activity
     * is killed by android device
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this.getApplicationContext(), SelectFactionActivity.class);
        intent.putExtra("neutralCards",neutralCards);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(intent);
    }
}
