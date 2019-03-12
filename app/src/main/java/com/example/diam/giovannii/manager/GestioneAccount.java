package com.example.diam.giovannii.manager;

import android.view.MenuItem;
import android.view.View;

import com.example.diam.giovannii.model.pojo.Artista;
import com.example.diam.giovannii.model.pojo.Band;
import com.example.diam.giovannii.model.pojo.Evento;
import com.example.diam.giovannii.model.pojo.Promotore;
import com.example.diam.giovannii.model.pojo.PropostaEvento;


import java.util.ArrayList;

public interface GestioneAccount {



    public Artista visualizzaProfiloArtista(View v, MenuItem id);

    public Band visualizzaProfiloBand(View v, MenuItem id);

    public Promotore visualizzaProfiloPromotore(View v, MenuItem id);

    public Artista modificaProfiloArtista(View v, int idArtista);

    public Band modificaProfiloBand(View v, String emailBand);

    public Promotore modificaProfiloPromotore(View v, String emailPromotore);

    public ArrayList<PropostaEvento> visualizzaMieProposte(String emailPromotore);

    public ArrayList<Evento> visualizzaMieiEventi(String emailUtente);

    public ArrayList<Artista> cercaArtisti(String filtro);

    public ArrayList<Band> cercaBand(String filtro);

    public ArrayList<Promotore> cercaPromotori();



}
