package com.company.servicii;

import com.company.entitati.*;
import com.company.mySQL.DatabaseService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Serviciu {
    private Scanner sc;

    private List<Investigatie> listaInvestigatiiDisponibile;
    private List<Medic> listaMediciDisponibili;
    DatabaseService databaseService = DatabaseService.getInstance();

    public Serviciu(Scanner sc, List<Investigatie> listaInvestigatiiDisponibile, List<Medic> listaMediciDisponibili) {
        this.sc = sc;
        this.listaInvestigatiiDisponibile = listaInvestigatiiDisponibile;
        this.listaMediciDisponibili = listaMediciDisponibili;
    }

    private void afisareProgramariMedici(Medic medic, List<Programare> listaProgramariTotal) {
        int index = 0;

        for (Programare programare : listaProgramariTotal) {
            for(Medic medicIt : programare.getInvestigatie().getListaDeMedici()) {
                if (Objects.equals(medicIt.getNume(), medic.getNume())) {
                    System.out.println(index + ". : " + programare + '\n');
                    index += 1;
                }
            }

        }
    }

    private void afisareClientiCareNuAuVrutProgramare(Medic medic, List<Programare> listaProgramariTotal) {
        int index = 0;
        for (Programare programare : listaProgramariTotal) {
            for(Medic medicIt : programare.getInvestigatie().getListaDeMedici()) {
                if (Objects.equals(medicIt.getNume(), medic.getNume())) {
                    System.out.println(index + ". : " + programare.getClient() + '\n');
                    index += 1;
                }
            }

        }
    }

    private void modificareProgramare(Medic medic) {
        int index;

        if(medic.getNumarDePacienti() == 10) {
            System.out.println("Numarul maxim de pacienti este deja atins.");
        }
        else{
            System.out.println("Programarea se va modifica doar din punct de vedere al starii acesteia");
            System.out.println("Indexul nu uita ca este de la 0. Il vezi si mai sus btw");

            // Validare index
            while(true) {

                System.out.println("Indexul programarii: ");
                index = sc.nextInt();

                if(index > medic.getListaProgramari().size() || index < 0) {
                    System.out.println("Index gresit");
                }
                else {
                    break;
                }
            }

            Programare programare = medic.getListaProgramari().get(index);

            System.out.println("Alege starea programarii: Acceptat/Refuzat/Stand-by");
            String stare = sc.nextLine();

            programare.setStare(stare);

            System.out.println("Programarea a fost modificata: "+ programare);
        }
    }

    private void afisareInvestigatii() {
//        int index = 0;
//        for (Investigatie investigatie: listaInvestigatiiDisponibile) {
//            System.out.println(index + ". :" + investigatie);
//            index += 1;
//        }
        databaseService.selectAllAnalize();
        databaseService.selectAllEcografii();
        databaseService.selectAllConsultatii();

    }

    private void stergereInvestigatii() {
        int index;
        String tip;
        System.out.println("ID investigatie: ");
        index = sc.nextInt();

        System.out.println("Tip investigatie (Analiza, Consultatie, Ecografie):");
        sc.skip("\\R?");
        tip = sc.nextLine();

        if(Objects.equals(tip, "Analiza")) {
            databaseService.deleteAnaliza(index);
        }

        else if(Objects.equals(tip, "Consultatie")) {
            databaseService.deleteConsultatie(index);
        }

        else if(Objects.equals(tip, "Ecografie")) {
           databaseService.deleteEcografie(index);
        }

    }

    private void modificareInvestigatii() {
        int index;
        String tip;
        System.out.println("ID investigatie: ");
        index = sc.nextInt();
        Investigatie investigatie = null;

        System.out.println("Tip investigatie (Analiza, Consultatie, Ecografie):");
        sc.skip("\\R?");
        tip = sc.nextLine();

        if(Objects.equals(tip, "Analiza")) {
            investigatie = databaseService.selectAnalizaWithID(index);

            if(investigatie == null) {
                System.out.println("Esuat.");
                return;
            }
        }

        else if(Objects.equals(tip, "Consultatie")) {
            investigatie = databaseService.selectConsultatieWithID(index);

            if(investigatie == null) {
                System.out.println("Esuat.");
                return;
            }
        }

        else if(Objects.equals(tip, "Ecografie")) {
            investigatie = databaseService.selectEcografieWithID(index);

            if(investigatie == null) {
                System.out.println("Esuat.");
                return;
            }
        }

        String nume = "";
        int pret = -1;
        String locatieCabinet = "";

        System.out.println("Poti modifica numele, pretul, locatia cabinetului. Enter = gol, nu se modifica, -1 = pretul nu se modifica");

        sc.skip("\\R?"); // in cazul in care imi mai ramane ceva de la inputul anterior
        System.out.println("Nume:");
        sc.skip("\\R?");
        nume = sc.nextLine();

        System.out.println("Pret:");
        sc.skip("\\R?");
        pret = sc.nextInt();

        System.out.println("Locatie Cabinet:");
        sc.skip("\\R?");
        locatieCabinet = sc.nextLine();

        if(!Objects.equals(nume, "")) {
            assert investigatie != null;
            investigatie.setNume(nume);
        }
        if(pret != -1) {
            assert investigatie != null;
            investigatie.setPret(pret);
        }
        if(!Objects.equals(locatieCabinet, "")) {
            assert investigatie != null;
            investigatie.setLocatieCabinet(locatieCabinet);
        }

        if(Objects.equals(tip, "Analiza")) {
            assert investigatie instanceof Analiza;
            databaseService.updateAnaliza((Analiza) investigatie, index);
        }

        else if(Objects.equals(tip, "Consultatie")) {
            assert investigatie instanceof Consultatie;
            databaseService.updateConsultatie((Consultatie) investigatie, index);

        }

        else if(Objects.equals(tip, "Ecografie")) {
            assert investigatie instanceof Ecografie;
            databaseService.updateEcografie((Ecografie) investigatie, index);
        }

        System.out.println("Investigatia modificata este: " + investigatie + '\n');
    }

    private void afisareProgramariPacient(List<Programare> listaProgramariClient) {

        if(listaProgramariClient.size() == 0) {
            System.out.println("Momentan nu avem nicio programare.");
        }
        else {
            for (Programare programare: listaProgramariClient) {
                System.out.println(programare);
            }

        }
    }

    private int creareProgramareClient(Client client, List<Programare> listaProgramariClient, List<Programare> listaProgramariTotal) {
        System.out.println("Acum vei crea o programare. Introdu urmatoarele date");

        System.out.println("Data programarii: ZZ/LL/AAAAA");
        sc.skip("\\R?");
        String dataProgramare = sc.nextLine();

        System.out.println("Numele investigatiei la care doresti sa faci o programare:");
        sc.skip("\\R?");
        String numeInvestigatie = sc.nextLine();

        Investigatie investigatieCautata = null;

        for (Investigatie investigatie: listaInvestigatiiDisponibile) {
            if(Objects.equals(numeInvestigatie, investigatie.getNume())) {
                investigatieCautata = investigatie;
            }
        }

        if(investigatieCautata == null) {
            return -1;
        }

        System.out.println("Adauga alte detalii aici:");
        sc.skip("\\R?");
        String detalii = sc.nextLine();

        Programare programareClient = new Programare(
                dataProgramare,
                detalii,
                investigatieCautata,
                client,
                investigatieCautata.getLocatieCabinet(),
                "Stand-by");

        listaProgramariClient.add(programareClient);

        for(Programare programare: listaProgramariClient) {
            System.out.println(programare);
        }

        listaProgramariTotal.add(programareClient);

        return 0;
    }

    private void afiseazaInvestigatiiPacient() {
//        int index = 0;
//        for (Investigatie investigatie: listaInvestigatiiDisponibile) {
//            System.out.println(index + ". :" + investigatie);
//            index += 1;
//        }

        databaseService.selectAllConsultatii();
        databaseService.selectAllAnalize();
        databaseService.selectAllEcografii();
    }

    private void afiseazaMedici() {
//        int index = 0;
//        for (Medic medic1 : listaMediciDisponibili) {
//            System.out.println(index + ". :" + medic1);
//            index += 1;
//        }

        databaseService.selectAllMedici();
    }

    public void meniu() {
        // Creez o lista de clienti:
        List<Client> listaClienti = new ArrayList<>();


        // Creez o lista de programari
        List<Programare> listaProgramariTotal = new ArrayList<>();
        List<Programare> listaProgramariClient = new ArrayList<>();

        AuditService auditService = AuditService.getInstance();
        // -------- parte cu meniu ----

        try {
            auditService.initializeAudit("src/com/company/csv/Audit.csv");
        } catch(IOException e) {
            e.getStackTrace();
        }

        while(true) {

            System.out.println("------------ MENIU ------------\n");
            System.out.println("Medic / Client / Admin");

            String cineFolosesteMeniul = sc.nextLine();

            switch (cineFolosesteMeniul) {


                // ------------------ FIECARE CATEGORIE VA AVEA UN MENIU DIFERIT ------------------


                // ------------------ 1. MENIU MEDICI ------------------
                case "Medic":

                    System.out.println("Numele medicului:");
                    String numeMedicLogat = sc.nextLine(); // nu e logat, dar presupunem

                    // Gaseste medicul
                    Medic medic = new Medic();

                    for (Medic medic1: listaMediciDisponibili) {

                        if(Objects.equals(medic1.getNume(), numeMedicLogat)) {
                            medic = medic1; // am gasit medicul cu care lucram.
                            break;
                        }
                    }

                    try{
                        auditService.addInAudit("src/com/company/csv/Audit.csv", "medicLogin", numeMedicLogat);
                    } catch(IOException e) {
                        e.getStackTrace();
                    }

                    int alegere = -1;
                    while(true) {

                        if(alegere == 0) {
                            break;
                        }
                        else {
                            // ----------- ASPECT MENIU -----------
                            System.out.println("------------ MENIU MEDICI ------------\n");
                            System.out.println("Buna ziua, " + medic.getNume() + "!\n\n"); // asa verific si daca lucrez cu medicul bun
                            System.out.println("1. Vezi programarile\n");
                            System.out.println("2. Vezi datele despre clientii care vor sa se programeze\n");
                            System.out.println("3. Modifica programare\n");
                            System.out.println("4. Afiseaza investigatii\n");
                            System.out.println("5. Modifica investigatie\n");
                            System.out.println("6. Stergere investigatie\n");
                            alegere = sc.nextInt();

                            switch(alegere) {

                                // ----------- IMPLEMENTARE AFISARE PROGRAMARI -----------
                                case 1:
                                    afisareProgramariMedici(medic, listaProgramariTotal);

                                    try{
                                        auditService.addInAudit("src/com/company/csv/Audit.csv", "printProgramari", numeMedicLogat);
                                    } catch(IOException e) {
                                        e.getStackTrace();
                                    }

                                    break;

                                // ----------- IMPLEMENTARE AFISARE CLIENTI CARE AU VRUT PROGRAMARE -----------
                                case 2:
                                    afisareClientiCareNuAuVrutProgramare(medic, listaProgramariTotal);

                                    try{
                                        auditService.addInAudit("src/com/company/csv/Audit.csv", "printClientiCuProgramareRefuzata", numeMedicLogat);
                                    } catch(IOException e) {
                                        e.getStackTrace();
                                    }

                                    break;

                                // ----------- IMPLEMENTARE MODIFICARE -----------
                                case 3:
                                    modificareProgramare(medic);

                                    try{
                                        auditService.addInAudit("src/com/company/csv/Audit.csv", "modificareProgramare", numeMedicLogat);
                                    } catch(IOException e) {
                                        e.getStackTrace();
                                    }

                                    break;

                                // ----------- IMPLEMENTARE AFISARE INVESTIGATII -----------
                                case 4:
                                    afisareInvestigatii();

                                    try{
                                        auditService.addInAudit("src/com/company/csv/Audit.csv", "printInvestigatii", numeMedicLogat);
                                    } catch(IOException e) {
                                        e.getStackTrace();
                                    }

                                    break;

                                // ----------- IMPLEMENTARE MODIFICARE INVESTIGATII -----------
                                case 5:
                                    // Validare index
                                    modificareInvestigatii();

                                    try{
                                        auditService.addInAudit("src/com/company/csv/Audit.csv", "modificareInvestigatii", numeMedicLogat);
                                    } catch(IOException e) {
                                        e.getStackTrace();
                                    }
                                    break;

                                case 6:
                                    stergereInvestigatii();
                                    try{
                                        auditService.addInAudit("src/com/company/csv/Audit.csv", "modificareInvestigatii", numeMedicLogat);
                                    } catch(IOException e) {
                                        e.getStackTrace();
                                    }
                                    break;
                            }
                        }
                    }

                    break;

                case "Client":

                    System.out.println("Numele clientului:");
                    sc.skip("\\R?");
                    String numeClient = sc.nextLine();
                    Client client = new Client();
                    for (Client client1: listaClienti) {
                        if(Objects.equals(client1.getNume(), numeClient)) {
                            client = client1;
                        }
                    }

                    if(Objects.equals(client.getNume(), null)) {
                        // atunci inseamna ca nu s-a creat. Deci trebuie sa il cream
                        System.out.println("Clientul nu exista. Doresti sa-l creezi? (Y/N)");
                        sc.skip("\\R?");
                        String daSauNu = sc.nextLine();

                        if(Objects.equals(daSauNu, "Y")) {
                            System.out.println("Ai ales sa creezi Clientul. Avem nevoie de urmatoarele date:");

                            System.out.println("Nume (Full):");
                            sc.skip("\\R?");
                            String numeClientFull = sc.nextLine();
                            numeClient = numeClientFull;

                            System.out.println("Varsta:");
                            sc.skip("\\R?");
                            int varsta = sc.nextInt();

                            System.out.println("Numar de telefon");
                            sc.skip("\\R?");
                            String nrTelefon = sc.nextLine();

                            System.out.println("Esti asigurat medical? (Da/Nu)");
                            sc.skip("\\R?");
                            daSauNu = sc.nextLine();

                            Boolean asigurat;

                            asigurat = Objects.equals(daSauNu, "Da");

                            client = new Client(numeClientFull, varsta, nrTelefon, asigurat);

                            listaClienti.add(client);

                            System.out.println("Clientul a fost creat cu succes!");

                            try {
                                auditService.addInAudit("src/com/company/csv/Audit.csv", "createUserClient", numeClient);
                            } catch (IOException e) {
                                e.getStackTrace();
                            }
                        }

                        else {
                            System.out.println("Ne pare rau, dar trebuie sa fii inregistrat pentru a folosi dashboard-ul clientului.");
                        }

                    }

                    alegere = -1;

                    while(true) {

                        if(alegere == 0) {
                            break;
                        }
                        else{
                            System.out.println("------------ MENIU CLIENTI ------------");
                            System.out.println("Buna ziua," + client.getNume() + "!\n\n");
                            System.out.println("1. Vizualizeaza programari");
                            System.out.println("2. Creeaza o programare");
                            System.out.println("3. Afiseaza investigatii");
                            System.out.println("4. Afiseaza medici");

                            alegere = sc.nextInt();

                            switch(alegere) {

                                case 1:
                                   // IMPLEMENTARE AFISARE PROGRAMARI
                                   afisareProgramariPacient(listaProgramariClient);

                                    try {
                                        auditService.addInAudit("src/com/company/csv/Audit.csv", "viewProgramariPacient", numeClient);
                                    } catch (IOException e) {
                                        e.getStackTrace();
                                    }

                                    break;
                                case 2:
                                    // IMPLEMENTARE CREARE PROGRAMARE

                                    int rezultat = creareProgramareClient(client, listaProgramariClient, listaProgramariTotal);

                                    if(rezultat == -1) {
                                        System.out.println("Nu am gasit investigatia cautata. Verifica daca numele acesteia este scris corect.");
                                    }

                                    try {
                                        auditService.addInAudit("src/com/company/csv/Audit.csv", "createProgramareClient", numeClient);
                                    } catch (IOException e) {
                                        e.getStackTrace();
                                    }

                                    break;
                                case 3:
                                    afiseazaInvestigatiiPacient();
                                    try {
                                        auditService.addInAudit("src/com/company/csv/Audit.csv", "viewInvestigatiiPacient", numeClient);
                                    } catch (IOException e) {
                                        e.getStackTrace();
                                    }
                                    break;

                                case 4:
                                    afiseazaMedici();
                                    try {
                                        auditService.addInAudit("src/com/company/csv/Audit.csv", "viewMediciPacient", numeClient);
                                    } catch (IOException e) {
                                        e.getStackTrace();
                                    }
                                    break;
                            }

                        }
                    }

                    break;

                case "exit":
                    return;

                default:
                    System.out.println("Alegere incorecta.");
                    break;

            }
        }
    }
}
