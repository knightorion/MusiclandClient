package com.example.diam.giovannii.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diam.giovannii.R;

import java.util.ArrayList;

public class CustomAdapterPromotore extends ArrayAdapter<PromotoreVisualizzato> {

    private int resource;
    private LayoutInflater inflater;

    public CustomAdapterPromotore(Context c, int resourceId, ArrayList<PromotoreVisualizzato> promoters){
        super(c, resourceId, promoters);
        resource = resourceId;
        inflater = LayoutInflater.from(c);
    }


    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if (v == null) {
            v = inflater.inflate(R.layout.list_element_promotore, null);
        }

        PromotoreVisualizzato promotoreVisualizzato = getItem(position);


        TextView nomePromotore;
        String emailPromotore;


        nomePromotore =  v.findViewById(R.id.textView_email_promotore_list_element_campo);
        nomePromotore.setText(promotoreVisualizzato.getNomePromotore());
        emailPromotore = promotoreVisualizzato.getEmailPromotore();

        return v;
    }
}
