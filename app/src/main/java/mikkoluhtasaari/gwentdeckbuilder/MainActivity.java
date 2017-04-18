package mikkoluhtasaari.gwentdeckbuilder;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client;
    MediaType JSON;

    public static final String baseUrl = "https://api.gwentapi.com/v0";
    public static final String[] factionUrls = new String[6];
    public static final String offset = "?limit=200&offset=0";
    ArrayList<Card> neutralCards;
    ResultSet cardUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        neutralCards = new ArrayList<>();
        factionUrls[0] = "9aD4AoKlRhqKxQ2b7F0JOA";
        factionUrls[1] = "C21SnrUdSSW7ttfGNkOzeA";
        factionUrls[2] = "DuOHJOMpTWSevegxs7xOKQ";
        factionUrls[3] = "sa1zdVBdST6FqFJUY1vIcQ";
        factionUrls[4] = "vVL5p_u6SRmotqThkahITA";
        factionUrls[5] = "wkY8HZJATUKd_EtraBoC3A";

        setContentView(R.layout.activity_main);

        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");

    }

    public void testHttp(View view) throws IOException {
        GetTask task = new GetTask();
        task.execute();
    }

    public class GetTask extends AsyncTask {
        private Exception exception;
        @Override
        protected Object doInBackground(Object[] params) {

            try {
                System.out.println("doInBackground");
                String getResponse = get("https://api.gwentapi.com/v0/cards/factions/"+factionUrls[1]+offset);
                System.out.println(getResponse);
                Gson gson = new Gson();
                cardUrls = gson.fromJson(getResponse, ResultSet.class);

                for (int i = 0; i < cardUrls.getResults().size(); i++) {
                    String temp = get(cardUrls.getResults().get(i).getHref());
                    neutralCards.add(gson.fromJson(temp, Card.class));
                    System.out.println("haetaan");
                }
                System.out.println("ready");
                System.out.println(neutralCards.size());
                return getResponse;
            } catch (Exception e) {
                System.out.println("Exception");
                this.exception = e;
                return null;
            }
        }

        /*protected void onPostExecute(String getResponse) {
            System.out.println(getResponse);
        }*/

        public String get(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

}
