package com.example.diam.giovannii.presenter;

public class ArtistaVisualizzato {

    private String nicknameArtista;
    private int idArtista;

    public ArtistaVisualizzato(String nicknameArtista, int idArtista){
        this.nicknameArtista = nicknameArtista;
        this.idArtista = idArtista;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public String getNicknameArtista() {
        return nicknameArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public void setNicknameArtista(String nicknameArtista) {
        this.nicknameArtista = nicknameArtista;
    }
}
