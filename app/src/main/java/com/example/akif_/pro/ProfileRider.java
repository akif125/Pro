package com.example.akif_.pro;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ProfileRider extends AppCompatActivity {

    String user_name,JSON_String,name,userid,email,contactno,cnic,gender;
    JSONObject jsonObject;
    JSONArray jsonArray;
    TextView tx_name;
    TextView tx_userid;
    TextView tx_email;
    TextView tx_contactno;
    TextView tx_cnic;
    TextView tx_gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_rider);
        Intent i=getIntent();
        user_name=i.getExtras().getString("user_id");





        tx_name=(TextView)findViewById(R.id.txname);
        tx_userid=(TextView)findViewById(R.id.txuserid);
        tx_email=(TextView)findViewById(R.id.txemail);
        tx_contactno=(TextView)findViewById(R.id.txcontactno);
        tx_cnic=(TextView)findViewById(R.id.txcnic);
        tx_gender=(TextView)findViewById(R.id.txgender);
           new BackgroundTask().execute();




    }




    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String json_url;

        @Override
        protected void onPreExecute() {
            json_url = "http://192.168.1.2/profile_rider.php";
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                jsonObject=new JSONObject(s);



            jsonArray=jsonObject.getJSONArray("result");
            JSONObject jo=jsonArray.getJSONObject(0);
            userid=jo.getString("user_id");
            name=jo.getString("name");
            email=jo.getString("email");
            gender=jo.getString("gender");
            contactno=jo.getString("contactno");
            cnic=jo.getString("cnic");
            tx_name.setText(name);
            tx_cnic.setText(cnic);
            tx_userid.setText(userid);
            tx_contactno.setText(contactno);
            tx_gender.setText(gender);
            tx_email.setText(email);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }






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
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();



                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
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















