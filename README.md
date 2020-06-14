# Projekat iz predmeta Internet softverske arhitekture i Metodologije razvoja softvera
# Clinical Center Information System
------------
### Članovi tima:
1. Vukašin Jokić SW 42/2017
2. Nemanja Jevtić SW 45/2017
3. Nikola Stojanović SW 36/2017

### Korišćene tehnologije:
1. vue.js za frontend
2. spring framework za bakend
3. Postgres baza podataka

### Korišćeni alati:
1. TravisCI - alat za integraciju
2. SonarCloud - alata za analizu kvaliteta koda
3. Heroku - link na kome je izvršen deplozment: https://clinic-center-inf-system.herokuapp.com/

### Preduslovi:
Neophodno je instalirati:
1. Java 11
2. Apache Maven
3. PostgreSQL
4. Neophodne plagine za spring boot u odabranom radnom okruženju
5. Node.js radi korišćenja node package manager-a (npm)

### Pokretanje bekend projekta:
1. Otvoriti CCIS projekat u IntellIJ: Open -> pom.xml -> Open as Project
2. Instalirati sve zavisnosti iz pom.xml-a
3. Pokrenuti projekat -> Izvršava se na http://localhost:8081
```
clinical-center-information-system
│   README.md
│   .gitignore
└───client
└───CCIS
    │   pom.xml   <-
```

### Pokretanje frontend projekta:
1. Pozicionirati se na projekat client i instalirati zavisnosti
2. Pokrenuti ga sa komendom npm run serve -> Izvršava se na http://localhost:8080
```
cd client
npm install
npm run serve
```
