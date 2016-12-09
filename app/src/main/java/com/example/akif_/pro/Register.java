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

public class Register extends Activity {
    EditText et2,et3,et4,et5,et6,et7,et8;
    String code,code2,name,gender,email,contactno,cnic,password;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
        et4=(EditText)findViewById(R.id.editText4);
        btn2=(Button)findViewById(R.id.btnRegister);
        et5=(EditText)findViewById(R.id.editText5);
        et6=(EditText)findViewById(R.id.editText6);
        et7=(EditText)findViewById(R.id.editText7);
        et8=(EditText)findViewById(R.id.editText8);





    }
    public void onRegister(View view)
    {
        code2="7499";
        if (code.equals(code2)) {

         name=et2.getText().toString();
         email=et3.getText().toString();
         password=et4.getText().toString();
         gender=et5.getText().toString();
         contactno=et6.getText().toString();
         cnic=et7.getText().toString();
            if(email.contains("@nu.edu.pk")) {
                Backgroundworker2 backgroundworker2 = new Backgroundworker2( name, email, password, gender, contactno, cnic, this);
                backgroundworker2.execute();
            }
            else
            {
                Toast.makeText(this,"enter NU email ",Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            Toast.makeText(this,"enter valid code ",Toast.LENGTH_LONG).show();
        }

    }

    public void onVerification(View view)
    {
        int max=9000;
        int min=1000;
        Random r=new Random();
    //   int x=r.nextInt(max-min+1)+min;
         int x=7499;
        code=Integer.toString(x);


        //String message="Your verificaton code is "+code;
        //sensms(contactno,message);





    }
    /*private void sensms(String phoneno,String message)
    {
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(phoneno,null,message,null,null);


    }*/

















    public class Backgroundworker2 extends AsyncTask<String,Void,String> {
        ProgressDialog pDialog;
        String name,email,password,gender,contactno,cnic ;
        Context context;
        Backgroundworker2(String name,String email,String password,String gender,String contactno,String cnic,Context ctx)
        {

            this.name=name;
            this.password=password;
            this.email=email;
            this.gender=gender;
            this.contactno=contactno;
            this.cnic=cnic;
            this.context=ctx;

        }

        @Override
        protected String doInBackground(String... params) {
            String _url="http://192.168.1.109/register.php";
            try {
                URL url=new URL(_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode("cnic","UTF-8")+"="+URLEncoder.encode(cnic,"UTF-8")+'&'+URLEncoder.encode("gender","UTF-8")+'='+URLEncoder.encode(gender,"UTF-8")+'&'+URLEncoder.encode("contactno","UTF-8")+'='+URLEncoder.encode(contactno,"UTF-8");
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

                Intent i=new Intent(getApplicationContext(),Driverreg.class);
                startActivity(i);





        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }


}
