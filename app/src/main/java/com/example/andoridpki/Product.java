package com.example.andoridpki;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    private String naziv;
    private String opis;
    private String sastojci;
    private int cena;
    private String slika;
    private ArrayList<String> komentari = new ArrayList<>();

    public Product(String naziv, String opis, String sastojci, Integer cena, String slika) {
        this.naziv = naziv;
        this.opis = opis;
        this.sastojci = sastojci;
        this.cena = cena;
        this.slika = slika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSastojci() {
        return sastojci;
    }

    public void setSastojci(String sastojci) {
        this.sastojci = sastojci;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public ArrayList<String> getKomentari() {
        return komentari;
    }

    public void setKomentari(ArrayList<String> komentari) {
        this.komentari = komentari;
    }

    public void writeComment(String komentar) {
        komentari.add(komentar);
    }
}
