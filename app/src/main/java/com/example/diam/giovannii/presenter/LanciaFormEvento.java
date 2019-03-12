package com.example.diam.giovannii.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.diam.giovannii.R;


public class LanciaFormEvento extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_evento);


        Intent i=new Intent();
        i.getExtras();
        //controllare se funziona

    }


}
