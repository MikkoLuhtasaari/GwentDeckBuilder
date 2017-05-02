package mikkoluhtasaari.gwentdeckbuilder.LoadClasses;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import mikkoluhtasaari.gwentdeckbuilder.Card;
import mikkoluhtasaari.gwentdeckbuilder.CreateDecks.CreateNilfgaardDeck;
import mikkoluhtasaari.gwentdeckbuilder.CreateDecks.CreateNorthernRealmsDeck;
import mikkoluhtasaari.gwentdeckbuilder.R;
import mikkoluhtasaari.gwentdeckbuilder.ResultSet;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadNorthernRealms extends AppCompatActivity {

    public static final String northernRealmsUrl = "https://api.gwentapi.com/v0/cards/factions/sa1zdVBdST6FqFJUY1vIcQ?limit=200&offset=0";
    ArrayList<Card> neutralCards;
    ArrayList<Card> northernRealmsCards;
    ResultSet cardUrls;

    OkHttpClient client;
    MediaType JSON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_northern_realms);

        neutralCards = new ArrayList<>();
        northernRealmsCards = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if(extras.getSerializable("neutralCards") != null) {
                neutralCards = (ArrayList<Card>) extras.getSerializable("neutralCards");
            }
        }

        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");
        new GetTask(this).execute();
    }

    public class GetTask extends AsyncTask<URL, Integer, Long> {
        private Exception exception;
        private Context context;

        public GetTask(Context context) {
            this.context = context.getApplicationContext();
        }

        @Override
        protected Long doInBackground(URL... params) {
            try {
                System.out.println("Fetching nilfgaard cards");
                String getResponse = get(northernRealmsUrl);
                System.out.println(getResponse);
                Gson gson = new Gson();
                cardUrls = gson.fromJson(getResponse, ResultSet.class);

                for (int i = 0; i < cardUrls.getResults().size(); i++) {
                    String temp = get(cardUrls.getResults().get(i).getHref());
                    northernRealmsCards.add(gson.fromJson(temp, Card.class));
                }

                System.out.println(northernRealmsCards.size());
                return (long)northernRealmsCards.size();
            } catch (Exception e) {
                System.out.println(e);
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(Long result) {
            Intent intent = new Intent(context, CreateNorthernRealmsDeck.class);
            intent.putExtra("neutralCards",neutralCards);
            intent.putExtra("northernRealmsCards", northernRealmsCards);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

        public String get(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }
}
