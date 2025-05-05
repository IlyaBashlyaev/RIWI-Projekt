# RIWI-Projekt - Produktpreis- und Mehrwertsteuerrechner

Der Rechner der Produkten für den Kunde "RIWI". Das ist ein Lernprojekt im Fach "STD" im ersten Lehrjahr der Ausbildung zum "Fachinformatiker Anwendungsentwicklung".

## Der Beschreibung
Dieses Java-Programm ermöglicht die Berechnung von Produktpreisen und Mehrwertsteuer. Es können mehrere Produkte eingegeben werden, für die jeweils der Gesamtpreis und die enthaltene Mehrwertsteuer berechnet werden. Das Programm unterscheidet zwischen zwei Mehrwertsteuerwerte. Der erste Wert ist 7% für Produkte des Grundbedarfs und der zweite Wert ist 19% für Konsumgüter.

## Die Projektstruktur
```
 RIWI-Projekt/
 ├── src/
 │   ├── Main.java               # Das Hauptprogramm mit mit der "Main"-Klasse
 │   └── utils/
 │       └── CalculatorUtil.java # Die Hilfsklasse für die Berechnung
 └── README.md                   # Diese Datei
 ```

## Die Benutzung
1. Starten Sie das Programm und geben Sie die Anzahl der Produkte ein, die Sie berechnen möchten.
2. Für jedes Produkt werden folgende Informationen benötigt (durch Semikolon getrennt):
   - Der Name des Produkts
   - Der Preis (Bruttopreis mit Punkt als Dezimaltrennzeichen)
   - Der Typ des Produkts:
     - 'g' für Grundbedarf (7% MwSt.)
     - 'k' für Konsumgut (19% MwSt.)
Das Beispiel einer Eingabe: `Brot;2.5;g`

## Die Fehlerbehandlung
Das Programm erkennt folgende Fehlereingaben:
- Die ungültige Zahlen bei der Anzahl der Produkte oder beim Preis
- Die falsch formatierte Produktinformationen (fehlendes Semikolon)
- Die ungültige Typauswahl (andere Buchstaben als 'g' oder 'k')
Bei einer Fehlereingabe wird eine entsprechende Fehlermeldung ausgegeben und die Eingabe kann wiederholt werden.

## Die Ausgabe
Nach erfolgreicher Eingabe aller Produkte zeigt das Programm:
- Die Gesamtsumme aller Produkte
- Den Gesamtbetrag der enthaltenen Mehrwertsteuer
- Die Anzahl der eingegebenen Produkte

## Die Kompilierung und Ausführung
 ```bash
 # Kompilierung
 javac src/RiwiCalculatorGUI.java
 
 # Ausführung
 java src.RiwiCalculatorGUI
 java src.RiwiCalculatorGUI.java
 ```