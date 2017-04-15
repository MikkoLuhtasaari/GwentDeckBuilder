package mikkoluhtasaari.gwentdeckbuilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String baseUrl = "https://api.gwentapi.com/v0";
    ArrayList<Card> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cards = new ArrayList<>();
        setContentView(R.layout.activity_main);
    }
}
