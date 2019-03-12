package com.example.diam.giovannii.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diam.giovannii.R;

import java.util.ArrayList;

public class CustomAdapterBand extends ArrayAdapter<BandVisualizzata> {

    private int resource;
    private LayoutInflater inflater;

    public CustomAdapterBand(Context c, int resourceId, ArrayList<BandVisualizzata> bands){
        super(c, resourceId, bands);
        resource = resourceId;
        inflater = LayoutInflater.from(c);
    }


    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if (v == null) {
            v = inflater.inflate(R.layout.list_element_band, null);
        }

        BandVisualizzata bandVisualizzata = getItem(position);


        TextView nomeBand;
        String emailBand;


        nomeBand =  v.findViewById(R.id.textView_email_band_list_element);

        nomeBand.setText(bandVisualizzata.getNomeBand());
        emailBand = bandVisualizzata.getEmailBand();

        return v;
    }
}