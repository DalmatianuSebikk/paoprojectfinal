package com.company;
import com.company.Medicament;

import java.util.ArrayList;
import java.util.List;

public class Reteta {
    private int retetaID;
    private String dataEliberare;
    private List<Medicament> listaMedicamente = new ArrayList<Medicament>();

    // Constructor pentru cand putem da si lista de medicamente
    public Reteta(int retetaID, String dataEliberare, List<Medicament> listaMedicamente) {
        this.retetaID = retetaID;
        this.dataEliberare = dataEliberare;
        this.listaMedicamente = listaMedicamente;
    }

    // Constructor pentru cand nu putem sa o dam dar adaugam pe parcurs elemente.
    public Reteta(int retetaID, String dataEliberare) {
        this.retetaID = retetaID;
        this.dataEliberare = dataEliberare;
    }


    // Getters and Setters
    public int getRetetaID() {
        return retetaID;
    }

    public void setRetetaID(int retetaID) {
        this.retetaID = retetaID;
    }

    public String getDataEliberare() {
        return dataEliberare;
    }

    public void setDataEliberare(String dataEliberare) {
        this.dataEliberare = dataEliberare;
    }

    public List<Medicament> getListaMedicamente() {
        return listaMedicamente;
    }

    public void setListaMedicamente(List<Medicament> listaMedicamente) {
        this.listaMedicamente = listaMedicamente;
    }

    // Metode specifice
    public void AdaugaMedicament(Medicament medicament) {
        listaMedicamente.add(medicament);
    }

}
