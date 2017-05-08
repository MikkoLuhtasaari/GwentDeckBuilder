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
import mikkoluhtasaari.gwentdeckbuilder.R;
import mikkoluhtasaari.gwentdeckbuilder.ResultSet;
import mikkoluhtasaari.gwentdeckbuilder.SelectFactionActivity;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Loads neutral cards from server on startup.
 *
 * @author Mikko Luhtasaari
 * @version 1.0, 25 Apr 2017
 * @since 1.0
 */
public class LoadNeutrals extends AppCompatActivity {

    /**
     * Contains the url from which the card urls are retrieved.
     */
    public static final String neutralUrl = "https://api.gwentapi.com/v0/cards/factions/C21SnrUdSSW7ttfGNkOzeA?limit=200&offset=0";

    /**
     * Contains the retrieved cards.
     */
    ArrayList<Card> neutralCards;

    /**
     * Contains the urls from which cards are retrieved.
     */
    ResultSet cardUrls;

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
        setContentView(R.layout.activity_load_neutrals);

        neutralCards = new ArrayList<>();

        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");
            new LoadNeutrals.GetTask(this).execute();
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
                String getResponse = get(neutralUrl);
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
                    neutralCards.add(gson.fromJson(temp, Card.class));
                }
                return (long)neutralCards.size();
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
        @Override
        protected void onPostExecute(Long result) {
            Intent intent = new Intent(context, SelectFactionActivity.class);
            intent.putExtra("neutralCards",neutralCards);
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
