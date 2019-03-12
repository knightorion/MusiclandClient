package com.example.diam.giovannii.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.diam.giovannii.R;
import com.example.diam.giovannii.presenter.PresenterAutenticazione;

public class MainPrincipale extends Activity {
    Bundle a;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_a);
    }


/*
    public void attiva_home_promotore(View v){
        Intent i = new Intent(getApplicationContext(), MainActivityPromotore.class);
        startActivity(i);
    }
*/

/*
    public void attiva_home_band_artisti(View v){
        Intent a = new Intent(getApplicationContext(), MainActivityBandArtisti.class);
        startActivity(a);
    }

    */

    public void apriFormLogin(View v) {
        Intent i=new Intent(getApplicationContext(), PresenterAutenticazione.class);
        startActivity(i);
    }



}

