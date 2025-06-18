HiveMind Project
l'obbiettivo di questo progetto è quello di realizzare un software per assegnare e amministrare lo svolgimento di "compiti" detti "Ticket" agli impiegati di una azienda.
Le classi sono 4: 
-User 
-Manager (estende User)
-Dipendente (estende User)
-Ticket
queste 4 classi hanno il compito di formare la struttura sulla quale si base il progetto realizzato tramite JavaFX (parte grafica).

Con javaFX abbiamo altre classi dette "Controller", che hanno la funzione di regolare le diverse pagine.fxml e il loro funzionamento, tramite:
-stampare liste di Ticket
-stampare liste di Utenti
-ricevere input dall'utente
-passare da una scena all'altra
...
Di queste classi ne è presente una per ogni pagina.fxml in modo tale che il funzionamento di ogni pagina sia isolato nel proprio controller,
Ho preferito adottare questo metodo perchè:
1)scalabilà maggiore rispetto ad usare un solo controller
2)codice più pulito
3)relativamente semplice funzionamento

Inoltre è presente una classe chiamata "GlobalController" con un ruolo diverso da quello di tutte le altre.
Il suo scopo è quello di essere un oggetto che viene passato di Controller in Controller per non perdere i dati al suo interno,
dato che contiene le informazioni "Globali" del software come:
-il Dipendete o il Manager loggato
-liste di ticket printate dal DB mysql
-liste degli utenti nel dipartimento del manager
-metodi comuni a tutte le pagine (come il cambio di scena)
In parole povere viene usato come oggetto globale;

La parte più complicata forse è il meccanismo con il quale i controller effettuano il cambio di scena:
1) il primo passo è quello di creare una variabile localmente dentro la funzione che viene azionata dal tasto. (la variabile DEVE essere del tipo di Controller sul quale si vuole cambiare pagina)
2) successivamente a quella variabile viene assegnato il controller ritornato dalla funzione GlobalController.changeScene(String url, ActionEvent event).
3) il parametro "url" serve per cercare il file .FXML sul quale si vuole spostarsi. Mentre il parametro "event" serve per risalire alla STAGE in cui ci si trova nel momento del passaggio.
4) successivamente viene caricato nella STAGE il file .FXML trovato.
5) una volta ritornato il controller alla variabile locale il gioco è fatto, la pagina è stata caricata nella STAGE e risponderà ai metodi del Controller assegnatagli.
6) successivamente si passa il GlobalController al nuovo Controller per non perdere i dati dell'account loggato.
7) come ultimo passaggio bisogna stampare la nuova pagina con il metodo "openTab" presente in tutti i Controller.


Il software prende i propri dati da un DB mySQL presente in localhost quindi necessita della presenza del DB nel computer che lo avvia.
Eventualmente si potrebbe provvedere ad inserire un DB in server, e far connettere il software a quella macchina, ma purtroppo io non dispongo di tali
strumenti.
