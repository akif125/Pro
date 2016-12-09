package com.example.akif_.pro;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Displaylistview extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    contactAdapter contactAdapter;
    ListView listView;
    String name,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaylistview);
        contactAdapter=new contactAdapter(this,R.layout.row_layout);
        listView=(ListView) findViewById(R.id.listview);
        listView.setAdapter(contactAdapter);
        json_string=getIntent().getExtras().getString("json_data");
        try {
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("result");
            int count=0;

            while(count<jsonArray.length())
            {
                JSONObject jo=jsonArray.getJSONObject(count);
                name=jo.getString("name");
                email=jo.getString("email");
                password=jo.getString("password");

                contacts contacts=new contacts(name,email,password);
                contactAdapter.add(contacts);
                count ++;

            }





        } catch (JSONException e) {
            e.printStackTrace();
        }

      /*listView.setOnItemClickListener(
              new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      String str=String.valueOf(position);
                      Intent i=new Intent(getApplicationContext(),Driver_Rider.class);
                      i.putExtra("user_name",str);
                      startActivity(i);
                  }
              }
      );*/





    }
}
