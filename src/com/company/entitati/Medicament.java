package com.company.entitati;

public class Medicament {
    private int medicamentID;
    private String nume;
    private String dataExpirare;
    private int pret;

    public Medicament(int medicamentID, String nume, String dataExpirare, int pret) {
        this.medicamentID = medicamentID;
        this.nume = nume;
        this.dataExpirare = dataExpirare;
        this.pret = pret;
    }

    // Getters and Setters
    public int getMedicamentID() {
        return medicamentID;
    }

    public void setMedicamentID(int medicamentID) {
        this.medicamentID = medicamentID;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(String dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }
}
