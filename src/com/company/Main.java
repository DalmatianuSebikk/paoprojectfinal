package com.company;

import com.company.entitati.*;
import com.company.servicii.CSVNefunctionabil;
import com.company.servicii.CSVReadService;
import com.company.servicii.CSVWriteService;
import com.company.servicii.Serviciu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/*
    NUME: Sebastian Ionel
    PROIECT ALES: Programare cabinet medical
    ACTIUNI/INTEROGARI:
        - Clientul are posibilitatea sa isi aleaga doar una dintre investigatii (Analize, Consultatii, Ecografii).
        - Dupa ce isi alege investigatia dorita, mesajul este transmis cu un obiect de tip Programare si va avea un status de stand by
        - In functie de medicii disponibili, ei pot modifica respectiva programare, acceptand-o sau refuzand-o.
        - Daca este acceptata, investigatia facute deja de client va fi updatata.
        - Medicii vor avea un numar de programari disponibile. Daca un anumit medic nu mai are locuri, un alt medic poate sa ii accepte sau sa ii refuze programarea.
        - Anumite investigatii medicale pot avea restrictii precum cele de varsta. Daca pacientul in cauza nu este apt pentru respectiva investigatie, i se va refuza automat.
        - Clientul isi mai poate alege cabinetul medical la care doreste sa mearga (putem avea de exemplu cabinete medicale in mai multe localitati)
        - Se pot afisa date despre cabinete, pacienti, medici si investigatii
        - Medicii pot citi date despre clienti
        - Doar medicii pot modifica datele investigatiilor

    TIPURI DE OBIECTE:
        - Client
        - CabinetMedical
        - Programare
        - Medic
        - Analiza : Investigatie
        - Consultatie : Investigatie
        - Ecografie : Investigatie
        - Reteta
        - Medicament
 */
public class Main {

    public static void main(String[] args) throws IOException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {


        // CSVNEFUNCTIONABIL este defect pentru clasele care contin referinte catre alte clase, dar merge pentru cazurile in care ai doar date primitive de citit si atat.


        CSVReadService csvReadService = new CSVReadService();
        CSVWriteService csvWriteService = new CSVWriteService();

        ArrayList<Medic> listaMedici = csvReadService.readMedici("src/com/company/csv/Medici.csv");

//        for(Medic medic: lista) {
//            System.out.println(medic);
//        }

        ArrayList<Analiza> listaAnalize = csvReadService.readAnalize("src/com/company/csv/Analize.csv", listaMedici);

//        for(Analiza analiza: listaAnalize) {
//            System.out.println(analiza);
//        }

        ArrayList<Consultatie> listaConsultatii = csvReadService.readConsultatii("src/com/company/csv/Consultatii.csv", listaMedici);

//        for(Consultatie consultatie: listaConsultatii) {
//            System.out.println(consultatie);
//        }

        ArrayList<Ecografie> listaEcografii = csvReadService.readEcografii("src/com/company/csv/Ecografii.csv", listaMedici);
//        for(Ecografie ecografie: listaEcografii) {
//            System.out.println(ecografie);
//        }

//        boolean res = csvWriteService.saveAnalize("src/com/company/csv/AnalizeOut.csv", listaAnalize, lista);
//
//        if(res) {
//            System.out.println("OK");
//        }
//        else{
//            System.out.println("EROARE");
//        }
        Scanner sc = new Scanner(System.in);

        // INCARCARE DATE
        ArrayList<Investigatie> listaInvestigatiiDisponibile = new ArrayList<>();
        listaInvestigatiiDisponibile.addAll(listaAnalize);
        listaInvestigatiiDisponibile.addAll(listaConsultatii);
        listaInvestigatiiDisponibile.addAll(listaEcografii);

        Serviciu clasaServiciu = new Serviciu(sc, listaInvestigatiiDisponibile, listaMedici);
        clasaServiciu.meniu();


        // SALVEZ IN CAZUL IN CARE EXISTA MODIFICARI LA CEVA ANUME
        boolean res = csvWriteService.saveAnalize("src/com/company/csv/Analize.csv", listaAnalize, listaMedici);

        if(res) {
            System.out.println("SAVED ANALIZE...");
        }
        else{
            System.out.println("EROARE SALVARE ANALIZE...");
        }

        res = csvWriteService.saveConsultatii("src/com/company/csv/Consultatii.csv", listaConsultatii, listaMedici);

        if(res) {
            System.out.println("SAVED CONSULTATII...");
        }
        else{
            System.out.println("EROARE SALVARE CONSULTATII...");
        }

        res = csvWriteService.saveEcografii("src/com/company/csv/Ecografii.csv", listaEcografii, listaMedici);

        if(res) {
            System.out.println("SAVED CONSULTATII...");
        }
        else{
            System.out.println("EROARE SALVARE CONSULTATII...");
        }
    }
}
