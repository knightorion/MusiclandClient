package com.example.diam.giovannii.manager;

import com.example.diam.giovannii.model.pojo.Artista;
import com.example.diam.giovannii.model.pojo.Band;
import com.example.diam.giovannii.model.pojo.Promotore;

public interface GestioneRegistrazione {

    public Artista registrazioneArtista();

    public Band registrazioneBand();

    public Promotore registrazionePromotore();

    public boolean verificaMail(String email);
}
