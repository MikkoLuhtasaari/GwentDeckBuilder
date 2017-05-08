package mikkoluhtasaari.gwentdeckbuilder.LoadClasses;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import mikkoluhtasaari.gwentdeckbuilder.Card;
import mikkoluhtasaari.gwentdeckbuilder.CreateDeck;
import mikkoluhtasaari.gwentdeckbuilder.R;
import mikkoluhtasaari.gwentdeckbuilder.ResultSet;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Loads class spesific cards from server.
 *
 * @author Mikko Luhtasaari
 * @version 1.0, 25 Apr 2017
 * @since 1.0
 */
public class LoadOther extends AppCompatActivity {

    /**
     * Contains cards from previous loaders.
     */
    ArrayList<Card> neutralCards;

    /**
     * Contains the retrieved cards.
     */
    ArrayList<Card> otherCards;

    /**
     * Contains the urls from which cards are retrieved.
     */
    ResultSet cardUrls;

    /**
     * Contains part of the url.
     */
    String factionUrl;

    /**
     * Contains part of the url.
     */
    private final String baseUrl = "https://api.gwentapi.com/v0/cards/factions/";

    /**
     * Contains part of the url.
     */
    private final String offset = "?limit=200&offset=0";

    /**
     * Makes internet connection easier.
     */
    OkHttpClient client;

    /**
     * Makes parsing data easier.
     */
    MediaType JSON;

    /**
     * Creates the view.
     *
     * @param savedInstanceState bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_other);
        neutralCards = new ArrayList<>();
        otherCards = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            if(extras.getSerializable("neutralCards") != null) {
                neutralCards = (ArrayList<Card>) extras.getSerializable("neutralCards");
            }

            if(extras.getString("factionUrl") != null) {
                factionUrl = extras.getString("factionUrl");
            }
        }

        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");
        new GetTask(this).execute();
    }

    /**
     * Handles backend connection.
     */
    public class GetTask extends AsyncTask<URL, Integer, Long> {
        private Exception exception;
        private Context context;

        public GetTask(Context context) {
            this.context = context.getApplicationContext();
        }

        /**
         * Retrieves data from backend.
         * @param params Url
         * @return returns length of the retrieved and parsed data.
         */
        @Override
        protected Long doInBackground(URL... params) {
            try {
                String getResponse = get(baseUrl+factionUrl+offset);
                Gson gson = new Gson();

                /**
                 * Parses resultset from initial data. The server first returns a set of card urls.
                 */
                cardUrls = gson.fromJson(getResponse, ResultSet.class);

                /**
                 * Gets cards from server with urls from resultset.
                 */
                for (int i = 0; i < cardUrls.getResults().size(); i++) {
                    String temp = get(cardUrls.getResults().get(i).getHref());
                    otherCards.add(gson.fromJson(temp, Card.class));
                }

                return (long)otherCards.size();
            } catch (Exception e) {
                System.out.println(e);
                this.exception = e;
                return null;
            }
        }

        /**
         * Starts new activity when all cards are retrieved.
         *
         * @param result Long result
         */
        protected void onPostExecute(Long result) {
            Intent intent = new Intent(context, CreateDeck.class);
            intent.putExtra("neutralCards",neutralCards);
            intent.putExtra("otherCards", otherCards);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

            /**
             * Returning to loader activity is not allowed.
             */
            finish();
        }

        /**
         * Creates requests.
         *
         * @param url url from which results are to be retrieved.
         * @return Returns results as String
         * @throws IOException Retrieving data might fail for various reasons.
         */
        public String get(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }
}
