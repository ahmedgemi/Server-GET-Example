package com.example.ahmed.rest_example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {


    Button b1;
    TextView t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        b1 = (Button) findViewById(R.id.button);
        t1 = (TextView) findViewById(R.id.textView2);






        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Request request = new Request();
                request.execute();

            }
        });

    }





    private class Request extends AsyncTask<Void,Void,JSONObject>{


        String link = "https://www.okcoin.cn/api/v1/ticker.do?symbol=btc_cny";


        @Override
        protected JSONObject doInBackground(Void... params) {


            try {

                URL url = new URL(link);

                URLConnection urlConnection = url.openConnection();
                urlConnection.setConnectTimeout(5000);

                HttpURLConnection connection =(HttpURLConnection) urlConnection;

                BufferedReader bufferReader= new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String StringBuffer;
                String stringText = "";


                while ((StringBuffer = bufferReader.readLine()) != null) {
                    stringText += StringBuffer;
                }
                bufferReader.close();


                return new JSONObject(stringText);


            }
            catch (Exception e){

                Log.d("test",e.toString());
            }



            return null;
        }


        @Override
        protected void onPostExecute(JSONObject json) {

            try {

                Log.d("test",json.toString());

                String ticker = json.getString("ticker");

                json = new JSONObject(ticker);

                String price = json.getString("sell");

                t1.setText(price);
            }
            catch (Exception e){
                Log.d("test",e.toString());
            }


        }
    }


}
