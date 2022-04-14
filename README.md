# Proiect PAO
# Ionel Sebastian

ACTIUNI/INTEROGARI:
```
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
```
TIPURI DE OBIECTE:
```
    - Client
    - CabinetMedical
    - Programare
    - Medic
    - Analiza : Investigatie
    - Consultatie : Investigatie
    - Ecografie : Investigatie
    - Reteta
    - Medicament
 ```
