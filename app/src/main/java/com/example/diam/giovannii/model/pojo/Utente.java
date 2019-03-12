package com.example.diam.giovannii.model.pojo;


public class Utente {

    public Utente() {

    }

    public String getPassword() {
        return password;
    }

    public String getNumeroTelefono() {
        return telefono;
    }

    public String getLinkSocial() {
        return contattoSocial;
    }

    public String getLuogo() {
        return luogo;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {

        return email;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.telefono = numeroTelefono;
    }

    public void setLinkSocial(String linkSocial) {
        this.contattoSocial = linkSocial;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }


    private String email;
    private String password;
    private String telefono;
    private String contattoSocial;
    private String luogo;
    private String biografia;
    private String foto;
}
