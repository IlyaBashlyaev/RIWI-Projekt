# RIWI Rechner

Der Rechner der Produkten für den Kunde "RIWI". Das ist ein Lernprojekt im Fach "STD" im ersten Lehrjahr der Ausbildung zum "Fachinformatiker Anwendungsentwicklung".

## Projektübersicht

Der RIWI Rechner ist eine Java-basierte Anwendung, die entwickelt wurde, um Berechnungen für Produkte mit unterschiedlichen Mehrwertsteuersätzen durchzuführen. Die Anwendung erlaubt es dem Benutzer, eine beliebige Anzahl an Produkten einzugeben, ihren Preis festzulegen und zwischen Grundbedarfs- und Konsumgütern zu wählen, was unterschiedliche Mehrwertsteuersätze zur Folge hat.

### Funktionen

- Dynamische Eingabe von beliebig vielen Produkten
- Unterscheidung zwischen Grundbedarfsgütern (7% MwSt) und Konsumgütern (19% MwSt)
- Berechnung der Gesamtsumme und der enthaltenen Mehrwertsteuer
- Benutzerfreundliche grafische Oberfläche in deutscher Sprache
- Validierung aller Eingaben mit hilfreichen Fehlermeldungen

## GUI-Implementierungsdetails

Die grafische Benutzeroberfläche des RIWI Rechners wurde mit Java Swing implementiert und besteht aus den folgenden Hauptkomponenten:

1. **Begrüßungs-Panel**: Enthält eine Willkommensnachricht für den Benutzer.
2. **Eingabe-Panel**: Hier gibt der Benutzer die Anzahl der Produkte ein.
3. **Produkt-Panel**: Nach Eingabe der Produktanzahl werden hier dynamisch Eingabefelder für jedes Produkt generiert.
4. **Ergebnis-Panel**: Zeigt die Gesamtsumme, die enthaltene Mehrwertsteuer und die Anzahl der Produkte an.
5. **Button-Panel**: Enthält Schaltflächen zum Berechnen und Zurücksetzen des Formulars.

Die Klasse `App` nutzt ein responsives Layout, das sich an die Größe des Fensters anpasst. Für jedes Produkt wird ein separates Panel mit Eingabefeldern für Name, Preis und MwSt-Satz erstellt.

## Bedienungsanleitung

### Schritt 1: Anzahl der Produkte festlegen
1. Geben Sie die gewünschte Anzahl der Produkte in das Feld "Geben Sie die Anzahl der Produkte ein" ein.
2. Klicken Sie auf "Bestätigen", um die Produkteingabefelder zu generieren.

### Schritt 2: Produktdetails eingeben
Für jedes Produkt:
1. Geben Sie den Namen des Produkts ein.
2. Geben Sie den Preis des Produkts ein (mit "." als Dezimaltrennzeichen).
3. Wählen Sie den Produkttyp:
   - "Grundbedarf (7%)" für Produkte mit ermäßigtem Steuersatz
   - "Konsumgut (19%)" für Produkte mit Standardsteuersatz

### Schritt 3: Berechnung durchführen
1. Nachdem Sie alle Produktdetails eingegeben haben, klicken Sie auf "Berechnen".
2. Die Ergebnisse (Gesamtsumme, Mehrwertsteuer und Anzahl der Produkte) werden im Ergebnis-Panel angezeigt.

### Zusätzliche Funktionen
- **Zurücksetzen**: Klicken Sie auf "Zurücksetzen", um alle Eingaben zu löschen und von vorne zu beginnen.
- **Validierung**: Alle Eingaben werden überprüft. Bei ungültigen Eingaben werden Fehlermeldungen angezeigt.

## Technische Details und Anforderungen

### Systemanforderungen
- Java Runtime Environment (JRE) 8 oder höher
- Mindestens 256 MB RAM
- Bildschirmauflösung von mindestens 800×600 Pixel

### Implementierungsdetails
- Die Anwendung verwendet Java Swing für die GUI-Komponenten
- Die Geschäftslogik für Berechnungen ist in `CalculatorUtil` implementiert
- Lokalisierung: Die Anwendung ist auf Deutsch lokalisiert (Zahlenformate, Währung, etc.)
- Eingabevalidierung: Alle Benutzereingaben werden validiert, um korrekte Berechnungen sicherzustellen

### Kompilieren und Ausführen
```bash
# Kompilieren
javac src/Main.java

# Ausführen
java src.Main
```

### Projektstruktur
```
RIWI-Projekt/
├── src/
│   ├── Main.java               # Hauptklasse
│   └── util/
│       └── CalculatorUtil.java # Berechnungslogik-Implementierung
└── README.md                   # Diese Datei
```