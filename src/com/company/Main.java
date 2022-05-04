package com.company;

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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // ------- PASUL 1. Initializam medicii si analizele disponibile -------


        List<Medic> listaMediciDisponibili = Arrays.asList(
                // Folosim constructorul fara lista de Programari fiindca nu le avem si nu le putem face inca.
                new Medic("Sebi Ionel", 0, "Biochimie"),
                new Medic("Andrei Maxim", 5, "Biochimie"),
                new Medic("Gheorghita Zaharia", 8, "Imunologie"),
                new Medic("Mihaela Scutelnicu", 4, "Imunologie"),
                new Medic("Petronela Maria", 6, "Hematologie"),
                new Medic("Ioan Rosca", 3, "Ecografie cardiaca"),
                new Medic("Alex Lupascu", 2, "Consultatii"),
                new Medic("Malus Camelusa", 5, "Ecografie abdomen"),
                new Medic("Slincu Elena", 2, "Psihiatrie")
        );


        // Lista de investigatii in care se pot adauga si alte elemente.
        List<Investigatie> listaInvestigatiiDisponibile = Arrays.asList(

                new Analiza("APTT", 18, "Bucuresti", Arrays.asList(listaMediciDisponibili.get(4)),
                        true, "Timp de executie: 1 zi"),

                new Analiza("Acid uric seric", 11, "Bucuresti",
                        Arrays.asList(
                            listaMediciDisponibili.get(0),
                            listaMediciDisponibili.get(1)
                        ),
                        false,
                        "Timp de executie: 1 zi"
                        ),

                new Analiza("Feritina", 40, "Bucuresti",
                        Arrays.asList(
                            listaMediciDisponibili.get(0),
                            listaMediciDisponibili.get(1)
                ),
                        false,
                        "Timp de execitie: 1-3 Zile"
                        ),

                new Consultatie("Consultatie de control psihiatrie", 60, "Bucuresti",
                        Arrays.asList(
                        listaMediciDisponibili.get(8)
                        ),
                        "Psihiatrie",
                        "Evaluarea psihologica, tratarea depresiei"),

                new Ecografie("Ecografie cardiaca Doppler color IIID", 150, "Bucuresti", Arrays.asList(
                        listaMediciDisponibili.get(5)
                ),
                        "Ecografie Doppler este un efect fizic descris inca din 1842, definind comportamentul unei unde",
                        false,
                        80
                        )
        );


        Serviciu clasaServiciu = new Serviciu(sc, listaInvestigatiiDisponibile, listaMediciDisponibili);
        clasaServiciu.meniu();

    }
}
