package com.example.akif_.pro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.test.suitebuilder.TestMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
public class Driverreg extends Activity {

    EditText et9,et10,et11,et12;
    String model,carno,licenseno;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverreg);

        et10=(EditText)findViewById(R.id.editText10);
        et11=(EditText)findViewById(R.id.editText11);
        et12=(EditText)findViewById(R.id.editText12);







    }
    public void onRegister(View view)
    {


            model=et10.getText().toString();
            carno=et11.getText().toString();
            licenseno=et12.getText().toString();

            Backgroundworker3 backgroundworker3=new Backgroundworker3(model,carno,licenseno,this);
            backgroundworker3.execute();




    }

    public void onChange(View view)
    {
        Intent i = new Intent(getApplicationContext(),Riderreg.class);
        startActivity(i);
    }

















    public class Backgroundworker3 extends AsyncTask<String,Void,String> {
        ProgressDialog pDialog;
        String model,carno,licenseno ;
        Context context;
        Backgroundworker3(String model,String carno,String licenseno,Context ctx)
        {

           this.model=model;
            this.carno=carno;
            this.licenseno=licenseno;
            this.context=ctx;

        }

        @Override
        protected String doInBackground(String... params) {
            String _url="http://192.168.1.2/driverreg.php";
            try {
                URL url=new URL(_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data= URLEncoder.encode("model","UTF-8")+"="+URLEncoder.encode(model,"UTF-8")+"&"+URLEncoder.encode("carno","UTF-8")+"="+URLEncoder.encode(carno,"UTF-8")+"&"+URLEncoder.encode("licenseno","UTF-8")+"="+URLEncoder.encode(licenseno,"UTF-8");
                bufferedWriter.write(data);
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
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Registering");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            pDialog.dismiss();
            pDialog.setMessage(result);





        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }

}
