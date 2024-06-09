# Curiosity

Applicazione Android sviluppata in Kotlin utilizzando il framework UI dichiarativo Jetpack Compose.

## Indice

- [Introduzione](#introduzione)
- [Features sviluppate](#features)
- [Architettura di progetto](#architettura)
- [Installazione](#installazione)
- [Uso](#uso)
- [Contributi](#contributi)
- [Contatti](#contatti)

## Introduzione

Curiosity è un'applicazione Android costruita con linguaggio Kotlin e framework Jetpack Compose.
Questa applicazione ti fornisce curiosità su determinate aree d'interesse (storia, arte, scienza ...) che l'utente potrà scegliere e modificare in ogni momento.
Ogni utente avrà un proprio punteggio in base a quante curiosità conosce, non conosce o che ha scartato, per poi determinarne un livello.
Le curiosità mostrate all'utente sono visualizzabili nella home page dell'applicazione oppure tramite notifiche push 
Le notifiche verrano mostrate ad ogni intervallo di tempo che l'utente potrà definire e modificare in ogni momento.

## Features sviluppate
1. Log-in Firebase Authentication
2. Sign-up Firebase Authentication
3. Logout Firebase Authentication
4. Change Password Firebase Authentication with email model
5. CRUD operations in Firebase Firestore
6. CRUD operations in Firebase Storage
7. CRUD operations in Android SharedPreferences
8. Complex composable functions with simple animations
9. Utilizzo dei Worker per invio delle notifiche 

## Architettura di progetto
Il progetto è stato creato utilizzando la Clean Architecture e la Dependency Injection con i Moduli Dagger-Hilt.

## Installazione

Segui questi passaggi per installare il progetto localmente:

1. Clona la repository:
    ```bash
    git clone https://github.com/matteoorig/Curiosity.git
    ```

2. Accedi alla directory del progetto:
    ```bash
    cd Curiosity
    ```

3. Installa le dipendenze:
    ```bash
    ./gradlew build
    ```

## Uso

Esegui l'applicazione con il seguente comando:
```bash
./gradlew run
```

## Contributi

Segui questi passaggi per contribuire:

1. Fai un fork del progetto
2. Crea un branch per la tua feature (`git checkout -b feature/nome-feature`)
3. Effettua il commit delle tue modifiche (`git commit -am 'Aggiunta feature'`)
4. Fai il push del branch (`git push origin feature/nome-feature`)
5. Apri una pull request

## Contatti

Per domande o supporto, contattami a:

- Email: matteoorig@gmail.com
- GitHub: [matteoorig](https://github.com/matteoorig)