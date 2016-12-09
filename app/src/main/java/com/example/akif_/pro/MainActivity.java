package com.example.akif_.pro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class MainActivity extends Activity {
    EditText etpass,etName,etcat;
    Button btnLogin;
    TextView textView;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etpass=(EditText)findViewById(R.id.etPass);
        etName=(EditText)findViewById(R.id.etName);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        textView=(TextView)findViewById(R.id.textView2);
        Typeface ty=Typeface.createFromAsset(getAssets(),"font.ttf");
        textView.setTypeface(ty);
        etcat=(EditText)findViewById(R.id.etcat);


    }
    public void onLogin(View view)
    {
         email= etName.getText().toString();
        String password=etpass.getText().toString();
        String type="login";
        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        backgroundWorker.execute(type,email,password);
    }
    public void onSignup(View view)
    {
        Intent i= new Intent(getApplicationContext(),Register.class);
        startActivity(i);
    }










    public class BackgroundWorker extends AsyncTask<String,Void,String> {


        Context context;
        AlertDialog alertDialog;
        public BackgroundWorker(Context ctx)
        {
            context=ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type =params[0];
            String login_url="http://192.168.1.109/login.php";

            if (type.equals("login"))
            {
                try {
                    String email=params[1];
                    String password=params[2];
                    URL url=new URL(login_url);
                    HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data= URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line="";
                    while ((line=bufferedReader.readLine())!=null)
                    {
                        result+=line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return result;
                } catch (MalformedURLException e) {

                    e.printStackTrace();
                } catch (IOException e) {

                    e.printStackTrace();
                }

            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {

            alertDialog.setMessage(result);
            alertDialog.show();
            if(result.contains("successfully  login"))
            {
                if(etcat.getText().toString().contains("rider"))
                {
                    Intent i=new Intent(getApplicationContext(),Rider_stuff.class);
                    i.putExtra("user_name",email);
                    startActivity(i);
                }
                if (etcat.getText().toString().contains("driver"))
                {
                    Intent i =new Intent(getApplicationContext(),Driver_stuff.class);
                    i.putExtra("email",email);
                    startActivity(i);
                }

            }

        }

        @Override
        protected void onPreExecute() {
            alertDialog=new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status");

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }




























}
