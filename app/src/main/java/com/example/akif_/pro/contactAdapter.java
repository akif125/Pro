package com.example.akif_.pro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akif_ on 12/5/2016.
 */
public class contactAdapter extends ArrayAdapter {






    List list=new ArrayList();
    public contactAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(contacts object)
    {
        super.add(object);
        list.add(object);
    }

    public int getCount() {

        return list.size();
    }
    public Object getItem(int position) {
        return list.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        contactHolder contactHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout,parent,false);
            contactHolder = new contactHolder();
            contactHolder.tx_name=(TextView)row.findViewById(R.id.txname);
            contactHolder.tx_email=(TextView)row.findViewById(R.id.txemail);
            contactHolder.tx_password=(TextView)row.findViewById(R.id.tx_password);
            row.setTag(contactHolder);

        }
        else
        {
            contactHolder=(contactHolder)row.getTag();
        }

        contacts contacts=(com.example.akif_.pro.contacts)this.getItem(position);
        contactHolder.tx_name.setText(contacts.getName());
        contactHolder.tx_email.setText(contacts.getEmail());
        contactHolder.tx_password.setText(contacts.getPassword());
        return row;
    }


    static class contactHolder
    {
        TextView tx_name,tx_email,tx_password;
    }
}
