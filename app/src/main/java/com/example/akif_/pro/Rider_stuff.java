package com.example.akif_.pro;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Rider_stuff extends Activity {
    String user_name;
    TextView textView;
    String JSON_String, str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_stuff);
        textView = (TextView) findViewById(R.id.profile);
        Intent i = getIntent();
        user_name = i.getExtras().getString("user_name");
        textView.setText(user_name);
        new BackgroundTask().execute();
    }
   public void onProfile(View view)
   {
       Intent i=new Intent(getApplicationContext(),ProfileRider.class);
       i.putExtra("user_id",user_name);
       startActivity(i);
   }

    public void onSearch(View view) {
        if(str==null)
        {
            Toast.makeText(getApplicationContext(),"no driver",Toast.LENGTH_LONG).show();

        }
        else {
            Intent i=new Intent(getApplicationContext(),Displaylistview.class);
            i.putExtra("json_data",str);
            startActivity(i);

        }


    }


    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "http://192.168.1.109/retrive.php";
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            str = s;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected String doInBackground(Void... params) {


            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_String = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_String + "\n");

                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
