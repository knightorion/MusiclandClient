package com.example.diam.giovannii.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.ListView;
import android.widget.Toast;


import com.example.diam.giovannii.R;

import com.example.diam.giovannii.presenter.FragmentEventiArtista;
import com.example.diam.giovannii.presenter.FragmentEventiBand;
import com.example.diam.giovannii.presenter.FragmentHome;
import com.example.diam.giovannii.presenter.FragmentListaArtisti;
import com.example.diam.giovannii.presenter.FragmentListaBand;
import com.example.diam.giovannii.presenter.FragmentListaPromotori;
import com.example.diam.giovannii.presenter.FragmentListaProposteEvento;
import com.example.diam.giovannii.presenter.FragmentProfiloArtista;
import com.example.diam.giovannii.presenter.FragmentProfiloBand;

import com.example.diam.giovannii.presenter.PresenterAccount;
import com.example.diam.giovannii.presenter.PresenterAutenticazione;
import com.example.diam.giovannii.presenter.PresenterEventi;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivityBandArtisti extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    String tipoProfilo;

    Intent i;
    Bundle a;
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigationView;

    PresenterAccount pa = new PresenterAccount();
    PresenterEventi pe = new PresenterEventi();

    FragmentListaProposteEvento fragmentListaProposteEvento;
    FragmentHome home;
    FragmentProfiloArtista fragmentProfiloArtista;
    FragmentProfiloBand fragmentProfiloBand;
    FragmentListaBand fragmentListaBand;
    FragmentListaArtisti fragmentListaArtisti;
    FragmentListaPromotori fragmentListaPromotori;
    FragmentEventiArtista fragmentEventiArtista;
    FragmentEventiBand fragmentEventiBand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        i = getIntent();
        a = i.getExtras();

        tipoProfilo = a.getString("TIPOPROFILO");

        setContentView(R.layout.activity_b_a);

        String tipoMetodo = "cercaEventi";
        JSONArray risposta = pe.creazioneE(tipoMetodo);
        JSONArray eventi = null;
        try {
            eventi = risposta.getJSONArray(1);
            i.putExtra("LISTA_EVENTI",eventi.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        home = new FragmentHome();
        ft.replace(R.id.frammento_da_inserire, home);
        ft.commit();



        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }


    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mio_profilo) {

            if (tipoProfilo.equals("artista")) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                fragmentProfiloArtista = new FragmentProfiloArtista();
                ft.replace(R.id.frammento_da_inserire, fragmentProfiloArtista);
                ft.addToBackStack(null);
                ft.commit();



            } else if (tipoProfilo.equals("band")) {


                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                fragmentProfiloBand = new FragmentProfiloBand();
                ft.replace(R.id.frammento_da_inserire, fragmentProfiloBand);
                ft.addToBackStack(null);
                ft.commit();


            }


        } else if (id == R.id.cerca_band) {

            String tipoMetodo = "cercaBandArtisti";

            JSONArray risposta = pa.creazione(tipoMetodo);
            JSONArray band = null;
            try {
                band = risposta.getJSONArray(2);
                System.out.println(band.toString());
                i.putExtra("LISTA_BAND",band.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            fragmentListaBand = new FragmentListaBand();
            ft.replace(R.id.frammento_da_inserire, fragmentListaBand);
            ft.addToBackStack(null);
            ft.commit();



        } else if(id == R.id.cerca_artisti){

            String tipoMetodo = "cercaBandArtisti";
            JSONArray risposta = pa.creazione(tipoMetodo);
            JSONArray artisti = null;
            try {
                artisti = risposta.getJSONArray(1);
                i.putExtra("LISTA_ARTISTI",artisti.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(artisti.toString());
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            fragmentListaArtisti = new FragmentListaArtisti();
            ft.replace(R.id.frammento_da_inserire, fragmentListaArtisti);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.cerca_evento) {


            String tipoMetodo = "cercaEventi";
            JSONArray risposta = pe.creazioneE(tipoMetodo);
            JSONArray eventi = null;
            try {
                eventi = risposta.getJSONArray(1);
                i.putExtra("LISTA_EVENTI",eventi.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(risposta.toString());


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            home = new FragmentHome();
            ft.replace(R.id.frammento_da_inserire, home);
            ft.addToBackStack(null);
            ft.commit();


        } else if (id == R.id.cerca_promotore) {

            String tipoMetodo = "cercaPromotori";
            JSONArray risposta = pa.creazione(tipoMetodo);
            JSONArray promotori = null;
            try {
                promotori = risposta.getJSONArray(1);
                i.putExtra("LISTA_PROMOTORI", promotori.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            fragmentListaPromotori = new FragmentListaPromotori();
            ft.replace(R.id.frammento_da_inserire, fragmentListaPromotori);
            ft.addToBackStack(null);
            ft.commit();


        } else if (id == R.id.cerca_proposte_evento) {



            String tipoMetodo = "cercaProposteEvento";
            JSONArray risposta = pe.creazioneE(tipoMetodo);
            JSONArray proposte = null;
            try {
                proposte = risposta.getJSONArray(1);
                i.putExtra("PROPOSTE_EVENTO_PROMOTORE", proposte.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println(risposta.toString());
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            fragmentListaProposteEvento = new FragmentListaProposteEvento();
            ft.replace(R.id.frammento_da_inserire, fragmentListaProposteEvento);
            ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.logout) {
            Toast.makeText(getApplicationContext(), "Logout. ", Toast.LENGTH_LONG).show();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if(home!=null) ft.remove(home);
            if(fragmentEventiArtista!=null) ft.remove(fragmentEventiArtista);
            if(fragmentEventiBand!=null) ft.remove(fragmentEventiBand);
            if(fragmentListaArtisti!=null) ft.remove(fragmentListaArtisti);
            if(fragmentEventiBand!=null) ft.remove(fragmentListaBand);
            if(fragmentProfiloArtista!=null) ft.remove(fragmentProfiloArtista);
            if(fragmentProfiloBand!=null) ft.remove(fragmentProfiloBand);
            if(fragmentListaPromotori!=null) ft.remove(fragmentListaPromotori);
            if(fragmentListaProposteEvento!=null) ft.remove(fragmentListaProposteEvento);
            ft.commit();
            Intent i = new Intent(getApplicationContext(), PresenterAutenticazione.class);
            startActivity(i);

        } else if (id == R.id.miei_eventi) {

            if (tipoProfilo.equals("artista")) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                fragmentEventiArtista = new FragmentEventiArtista();
                ft.replace(R.id.frammento_da_inserire, fragmentEventiArtista);
                ft.addToBackStack(null);
                ft.commit();


            } else if (tipoProfilo.equals("band")) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                fragmentEventiBand = new FragmentEventiBand();
                ft.replace(R.id.frammento_da_inserire, fragmentEventiBand);
                ft.addToBackStack(null);
                ft.commit();


            }

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}


