package src;

import src.utils.CalculatorUtil;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Die Hauptklasse für die Verarbeitung und Ausgabe der Mehrwertsteuer, Preis und Typ des Produkts
 */
public class Main {
    // Die Initialisierung der Variablen
    static final String DATA_NAME = "data.csv";
    static HashMap<Integer, String[]> products = new HashMap<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int productIdCounter = 0;

    // Die Hauptfunktion zur Programmverwaltung
    public static void main(String[] args) {
        // Die "try"- und "catch"-Blöcke
        try {
            // Die Begrüßung
            System.out.println("Hallo! Das ist ein Rechner-Programm von RIWI.");

            // Das Laden der Produkte aus den Daten
            loadProductsFromFile();

            while (true) {
                // Die Programmverwaltung
                System.out.println("\n=== PROGRAMMVERWALTUNG ===");
                System.out.println("1. Neue Produkte hinzufügen");
                System.out.println("2. Produkte anzeigen");
                System.out.println("3. Produkte löschen");
                System.out.println("4. Summe und MwSt berechnen");
                System.out.println("5. Programm schließen");
                System.out.print("Wählen Sie eine Option: ");
                String option = br.readLine();

                // Die Aktionen abhängig der Option
                switch (option) {
                    case "1":
                        addProducts();
                        break;
                    case "2":
                        displayProducts();
                        break;
                    case "3":
                        deleteProducts();
                        break;
                    case "4":
                        calculateSummary();
                        break;
                    case "5":
                        System.out.println("\n=== PROGRAMMSCHLIEßUNG ===");
                        System.out.println("\nVielen Dank für die Benutzung dieses Programms. Auf Wiedersehen!");
                        return;
                    default:
                        System.out.println("\n=== FEHLERMELDUNG ===");
                        System.out.println("Sie haben ungültigen Wert eingeben. Bitte geben Sie ihn nochmal:");
                }
            }
        }

        // Die Programmfehler
        catch (InputMismatchException e) {
            System.out.println("\n=== FEHLERMELDUNG ===");
            e.printStackTrace();
            System.out.println("\nSie haben keine richtige Zahl eingegeben!");
        }

        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\n=== FEHLERMELDUNG ===");
            e.printStackTrace();
            System.out.println("\nSie haben falsch den Namen und den Preis des Produkts eingegeben!");
        }

        catch (NumberFormatException e) {
            System.out.println("\n=== FEHLERMELDUNG ===");
            e.printStackTrace();
            System.out.println("\nSie haben keine richtige Zahl beim Preis eingegeben!");
        }

        catch (Exception e) {
            System.out.println("\n=== FEHLERMELDUNG ===");
            e.printStackTrace();
            System.out.print("Ein unerwarteter Fehler trat auf. Bitte wenden Sie sich an den Administrator.");
        }
    }

    public static void addProducts() throws IOException {
        // Die Eingabe der Anzahl
        System.out.print("\nGeben Sie zuerst die Anzahl der Produkte ein: ");
        int n = Integer.parseInt(br.readLine());

        // Die Eingabe der Produkte
        System.out.println("\nSie müssen " + n + " Mal drei Felder mit \";\" getrennt eingeben:");
        System.out.println("- Der Name des Produkts");
        System.out.println("- Der Preis des Produkts (Bruttopreis mit \".\" als Komma)");
        System.out.println("- Der Typ des Produkts (\"g\" für Grundbedarf mit 7 % oder \"k\" für Konsumgut mit 19 %)");

        for (int i = 0; i < n; i++) {
            // Die Eingabe des bestimmten Produkts
            System.out.print("\nDer Name des Produkts #" + (i + 1) + ": ");
            String name = br.readLine();
            System.out.print("Der Preis des Produkts #" + (i + 1) + ": ");
            float price = Float.parseFloat(br.readLine());
            System.out.print("Der Typ des Produkts #" + (i + 1) + ": ");
            float type = br.readLine().charAt(0) == 'g' ? 0.07f : 0.19f;

            // Das Hinzufügen des Produkts zu den Daten
            products.put(productIdCounter, new String[]{name, String.valueOf(price), String.valueOf(type)});
            saveProductToFile(productIdCounter, name, price, type);
            productIdCounter++;
        }
    }

    // Der Anzeige der Produktliste
    public static void displayProducts() throws IOException {
        if (products.isEmpty()) {
            System.out.println("\nEs sind leider keine Produkte vorhanden.");
            return;
        }

        System.out.println("\n=== PRODUKTLISTE ===");
        for (Map.Entry<Integer, String[]> entry : products.entrySet()) {
            String[] data = entry.getValue();
            System.out.printf("ID: %d | Name: %s | Preis: %.2f € | Typ: %s\n",
                entry.getKey(), data[0], Float.parseFloat(data[1]), data[2].equals("0.07") ? "Grundbedarf" : "Konsumgut");
        }
    }

    // Die Produktlöschung
    public static void deleteProducts() throws IOException {
        System.out.println("\n=== PRODUKTLÖSCHUNG ===");
        System.out.print("Geben Sie den Bereich der IDs des zu löschenden Produkts in Form \"a b\" ein: ");

        String data[] = br.readLine().split(" ");
        int a = Integer.parseInt(data[0]),
            b = Integer.parseInt(data[1]);

        for (int i = a; i <= b; i++) {
            if (products.containsKey(i)) {
                products.remove(i);
                System.out.println("Das Produkt mit der ID " + i + " wurde erfolgreich gelöscht.");
            }
            else System.out.println("Das Produkt mit der ID " + i + " existiert nicht.");
        }

        overwriteFile();
    }

    // Die Berechnung der Summe und vom MwSt
    public static void calculateSummary() throws IOException {
        System.out.println("\n=== PRODUKTBERECHNUNG ===");
        HashMap<Integer, Float> prices = new HashMap<>();
        HashMap<Integer, Float> types = new HashMap<>();

        for (Map.Entry<Integer, String[]> entry : products.entrySet()) {
            prices.put(entry.getKey(), Float.parseFloat(entry.getValue()[1]));
            float vat = entry.getValue()[2].equals("0.07") ? 0.07f : 0.19f;
            types.put(entry.getKey(), vat);
        }

        float sum = CalculatorUtil.calcSum(prices),
              VAT = CalculatorUtil.calcVAT(prices, types);

        System.out.println("Die Gesamtsumme: " + String.format("%.2f", sum));
        System.out.println("Der Mehrwertsteuer: " + String.format("%.2f", VAT));
        System.out.println("Die Anzahl der Produkte: " + prices.size());
    }

    // Die Speicherung der neuen Produkten in den Daten
    public static void saveProductToFile(int id, String name, float price, float type) throws IOException {
        FileWriter fw = new FileWriter(DATA_NAME, true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(id + "," + name + "," + price + "," + type);
        bw.newLine();
        bw.close();
    }

    // Das Überschreiben der Daten
    public static void overwriteFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_NAME));
        for (Map.Entry<Integer, String[]> entry : products.entrySet()) {
            String[] p = entry.getValue();
            bw.write(entry.getKey() + "," + p[0] + "," + p[1] + "," + p[2]);
            bw.newLine();
        }
        bw.close();
    }

    // Das Laden der Produkte aus den Daten
    public static void loadProductsFromFile() throws IOException {
        File file = new File(DATA_NAME);
        if (!file.exists()) return;
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            products.put(id, new String[]{parts[1], parts[2], parts[3]});
            productIdCounter = Math.max(productIdCounter, id + 1);
        }
        br.close();
    }
}