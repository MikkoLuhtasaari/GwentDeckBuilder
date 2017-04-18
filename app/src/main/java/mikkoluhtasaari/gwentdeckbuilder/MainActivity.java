package mikkoluhtasaari.gwentdeckbuilder;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client;
    MediaType JSON;

    public static final String baseUrl = "https://api.gwentapi.com/v0";
    ArrayList<Card> cards;
    ArrayList<TestCard> testCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cards = new ArrayList<>();
        testCards = new ArrayList<>();
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();
        JSON = MediaType.parse("application/json; charset=utf-8");

    }
    public void makeGetRequest(View v) throws IOException {

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
                String getResponse = get("https://api.gwentapi.com/v0/cards/SAMJj-MpRum5SvXE6OZQFg");
                //testCards.add(new TestCard(getResponse));
                //cards.add(new Card(getResponse));
                System.out.println(getResponse);
                Gson gson = new Gson();
                TestCard card = gson.fromJson(getResponse, TestCard.class);
                System.out.println(card);
                //System.out.println(getResponse);
                return getResponse;
            } catch (Exception e) {
                System.out.println("Exception");
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(String getResponse) {
            System.out.println(getResponse);
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
