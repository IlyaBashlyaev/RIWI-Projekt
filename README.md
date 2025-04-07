# RIWI Rechner

*[English version below](#english-version)*

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

---

# English Version

## Project Overview

The RIWI Calculator is a Java-based application designed to perform calculations for products with different value-added tax (VAT) rates. The application allows the user to enter any number of products, set their price, and choose between basic necessity and consumer goods, which results in different VAT rates.

### Features

- Dynamic entry of any number of products
- Distinction between basic necessity goods (7% VAT) and consumer goods (19% VAT)
- Calculation of the total sum and the included VAT
- User-friendly graphical interface in German
- Validation of all inputs with helpful error messages

## GUI Implementation Details

The graphical user interface of the RIWI Calculator is implemented using Java Swing and consists of the following main components:

1. **Welcome Panel**: Contains a welcome message for the user.
2. **Input Panel**: Here the user enters the number of products.
3. **Product Panel**: After entering the number of products, input fields for each product are dynamically generated here.
4. **Results Panel**: Shows the total sum, the included VAT, and the number of products.
5. **Button Panel**: Contains buttons for calculating and resetting the form.

The class `Main` uses a responsive layout that adapts to the size of the window. For each product, a separate panel with input fields for name, price, and VAT rate is created.

## User Guide

### Step 1: Set the Number of Products
1. Enter the desired number of products in the field "Geben Sie die Anzahl der Produkte ein" (Enter the number of products).
2. Click on "Bestätigen" (Confirm) to generate the product input fields.

### Step 2: Enter Product Details
For each product:
1. Enter the name of the product.
2. Enter the price of the product (using "." as the decimal separator).
3. Select the product type:
   - "Grundbedarf (7%)" (Basic need) for products with reduced tax rate
   - "Konsumgut (19%)" (Consumer good) for products with standard tax rate

### Step 3: Perform Calculation
1. After entering all product details, click on "Berechnen" (Calculate).
2. The results (total sum, VAT, and number of products) are displayed in the results panel.

### Additional Functions
- **Reset**: Click on "Zurücksetzen" (Reset) to clear all inputs and start over.
- **Validation**: All inputs are checked. Error messages are displayed for invalid inputs.

## Technical Details and Requirements

### System Requirements
- Java Runtime Environment (JRE) 8 or higher
- At least 256 MB RAM
- Screen resolution of at least 800×600 pixels

### Implementation Details
- The application uses Java Swing for the GUI components
- The business logic for calculations is implemented in `CalculatorUtil`
- Localization: The application is localized in German (number formats, currency, etc.)
- Input validation: All user inputs are validated to ensure correct calculations

### Compile and Run
```bash
# Compile
javac src/Main.java

# Run
java src.Main
```

### Project Structure
```
RIWI-Projekt/
├── src/
│   ├── Main.java               # Main class
│   └── util/
│       └── CalculatorUtil.java # Calculation logic implementation
└── README.md                   # This file
```

# RIWI-Projekt

Der Rechner der Produkten für den Kunde "RIWI".

Das ist ein Lernprojekt im Fach "STD" im ersten Lehrjahr der Ausbildung zum "Fachinformatiker Anwendungsentwicklung"