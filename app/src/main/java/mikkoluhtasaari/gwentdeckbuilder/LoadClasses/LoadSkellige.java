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
import mikkoluhtasaari.gwentdeckbuilder.CreateDecks.CreateScoiatelDeck;
import mikkoluhtasaari.gwentdeckbuilder.CreateDecks.CreateSkelligeDeck;
import mikkoluhtasaari.gwentdeckbuilder.R;
import mikkoluhtasaari.gwentdeckbuilder.ResultSet;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadSkellige extends AppCompatActivity {

    public static final String skelligeUrl = "https://api.gwentapi.com/v0/cards/factions/wkY8HZJATUKd_EtraBoC3A?limit=200&offset=0";
    ArrayList<Card> neutralCards;
    ArrayList<Card> skelligeCards;
    ResultSet cardUrls;

    OkHttpClient client;
    MediaType JSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_skellige);

        neutralCards = new ArrayList<>();
        skelligeCards = new ArrayList<>();

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
                System.out.println("Fetching skellige cards");
                String getResponse = get(skelligeUrl);
                System.out.println(getResponse);
                Gson gson = new Gson();
                cardUrls = gson.fromJson(getResponse, ResultSet.class);

                for (int i = 0; i < cardUrls.getResults().size(); i++) {
                    String temp = get(cardUrls.getResults().get(i).getHref());
                    skelligeCards.add(gson.fromJson(temp, Card.class));
                }

                System.out.println(skelligeCards.size());
                return (long)skelligeCards.size();
            } catch (Exception e) {
                System.out.println(e);
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(Long result) {
            Intent intent = new Intent(context, CreateSkelligeDeck.class);
            intent.putExtra("neutralCards",neutralCards);
            intent.putExtra("skelligeCards", skelligeCards);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            finish();
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
