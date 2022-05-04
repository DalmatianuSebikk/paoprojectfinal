package com.company;

import java.util.ArrayList;
import java.util.List;

public class Programare {

    private String dataProgramare;
    private String detalii;

    private Investigatie investigatie;

    private Client client;
    private String locatieCabinet;
    private String stare; // poate fi doar de 3 tipuri: Stand-by, Refuzat, Acceptat.

    // Ne asumam faptul ca programarea nu poate fi modificata pe parcurs in ceea ce priveste investigatiile.
    // Deci vom face doar constructorul de mai jos:


    public Programare(String dataProgramare, String detalii, Investigatie investigatie, Client client, String locatieCabinet, String stare) {
        this.dataProgramare = dataProgramare;
        this.detalii = detalii;
        this.investigatie = investigatie;
        this.client = client;
        this.locatieCabinet = locatieCabinet;
        this.stare = stare;
    }

    public String getDataProgramare() {
        return dataProgramare;
    }

    public void setDataProgramare(String dataProgramare) {
        this.dataProgramare = dataProgramare;
    }

    public String getDetalii() {
        return detalii;
    }

    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }

    public Investigatie getInvestigatie() {
        return investigatie;
    }

    public void setInvestigatie(Investigatie investigatie) {
        this.investigatie = investigatie;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getLocatieCabinet() {
        return locatieCabinet;
    }

    public void setLocatieCabinet(String locatieCabinet) {
        this.locatieCabinet = locatieCabinet;
    }

    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }

    @Override
    public String toString() {
        return "Programare{" +
                "dataProgramare='" + dataProgramare + '\'' +
                ", detalii='" + detalii + '\'' +
                ", investigatie=" + investigatie +
                ", client=" + client +
                ", locatieCabinet='" + locatieCabinet + '\'' +
                ", stare='" + stare + '\'' +
                '}';
    }


}
