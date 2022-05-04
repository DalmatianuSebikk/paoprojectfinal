package com.company;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String nume; // numele intreg al clientului
    private int varsta; // ne trebuie cand vom valida anumite investigatii
    private String nrDeTelefon; // presupunem ca prin numarul de telefon ii trimitem inapoi informatia
    private Boolean asiguratMedical; // presupunem ca oferim un discount de 40% celor care sunt asigurati medical pentru pretul final. (exploitable)


    public Client(String nume, int varsta, String nrDeTelefon, Boolean asiguratMedical) {
        this.nume = nume;
        this.varsta = varsta;
        this.nrDeTelefon = nrDeTelefon;
        this.asiguratMedical = asiguratMedical;
    }

    public Client() {

    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getNrDeTelefon() {
        return nrDeTelefon;
    }

    public void setNrDeTelefon(String nrDeTelefon) {
        this.nrDeTelefon = nrDeTelefon;
    }

    public Boolean getAsiguratMedical() {
        return asiguratMedical;
    }

    public void setAsiguratMedical(Boolean asiguratMedical) {
        this.asiguratMedical = asiguratMedical;
    }

    public Programare creeazaProgramare(String dataProgramare, String detalii, String locatieCabinet, Investigatie investigatie) {
        Programare programareClient = new Programare(dataProgramare, detalii, investigatie,this, locatieCabinet, "Stand-by");
        return programareClient;
    }

    //    public Programare creeazaProgramare(String dataProgramare, String detalii, String locatieCabinet) {
//
//        // Programarea o vom folosi atunci cand medicul va citi
//        Programare programareClient = new Programare(dataProgramare, detalii, listaDeAnalizeAlese, listaDeConsultatiiAlese, listaDeEcografiiAlese,
//                this, locatieCabinet, "Stand-by");
//        return programareClient;
//
//    }


    @Override
    public String toString() {
        return "Client{" +
                "nume='" + nume + '\'' +
                ", varsta=" + varsta +
                ", nrDeTelefon='" + nrDeTelefon + '\'' +
                ", asiguratMedical=" + asiguratMedical +
                '}';
    }
}
