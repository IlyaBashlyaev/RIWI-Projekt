package src;

import src.utils.CalculatorUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Die Hauptklasse für die Verarbeitung und Ausgabe der Mehrwertsteuer, Preis und Typ des Produkts
 */
public class Main {
	public static void main(String[] args) {
        // Die Begrüßung
        CalculatorUtil calculator = new CalculatorUtil();
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hallo! Das ist ein Rechner-Programm von RIWI.");

        // Die Initialisierung der Veriablen
        int n;
        float sum, VAT;
        char type, exit;

        // Die Initialisierung der Hashmaps
        HashMap<Integer, String> products = new HashMap<>();
        HashMap<Integer, Float> prices = new HashMap<>();
        HashMap<Integer, Float> types = new HashMap<>();

        // Die "try"- und "catch"-Blöcke
        try {
            // Die wiederholte "while"-Schleife
            while (true) {
                // Die Eingabe der Anzahl
                System.out.print("\nGeben Sie zuerst die Anzahl der Produkte ein: ");
                n = Integer.parseInt(br.readLine());

                // Die Eingabe der Produkte
                System.out.println("\nSie müssen " + n + " Mal drei Felder mit \";\" getrennt eingeben:");
                System.out.println("- Der Name des Produkts");
                System.out.println("- Der Preis des Produkts (Bruttopreis mit \".\" als Komma)");
                System.out.println("- Der Typ des Produkts (\"g\" für Grundbedarf mit 7 % oder \"k\" für Konsumgut mit 19 %)\n");

                for (int i = 0; i < n; i++) {
                     System.out.print("Geben Sie drei Felder für das Produkt #" + (i + 1) + " ein: ");
                     String data[] = br.readLine().split(";");
                     type = data[2].charAt(0);

                    // Die Speicherung der Daten aus der Eingabe
                    if (type == 'g' || type == 'k') {
                        products.put(i, data[0]);
                        prices.put(i, Math.abs(Float.parseFloat(data[1])));
                        types.put(i, type == 'g' ? 0.07f : 0.19f);
                    }

                     // Die Ausgabe des Fehlers mit dem Typ
                     else {
                         System.out.println("Bitte geben Sie nur \"g\" oder \"k\" für den Typ des Produkts ein!");
                         i--;
                     }
                }

                // Die Berechnung der Werte
                sum = calculator.calcSum(prices);
                VAT = calculator.calcVAT(prices, types);

                // Die ausgegebene Information für den Benutzer
                System.out.println("\nDie Gesamtsumme: " + String.format("%.2f", sum));
                System.out.println("Der Mehrwertsteuer: " + String.format("%.2f", VAT));
                System.out.println("Die Anzahl der Produkte: " + n);

                // Das Aufräumen der Hashmaps
                products.clear();
                prices.clear();
                types.clear();

                // Das Verlassen der Programms
                System.out.print("\nWollen Sie das Programm verlassen [\"y\" = Ja, Jede andere Antwort = Nein]: ");
                exit = br.readLine().charAt(0);
                if (exit == 'y') break;
            }
        }

        // Die Programmfehler
        catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.println("\nSie haben keine richtige Zahl eingegeben!");
        }

        catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("\nSie haben falsch den Namen und den Preis des Produkts eingegeben!");
        }

        catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("\nSie haben keine richtige Zahl beim Preis eingegeben!");
        }

        catch (Exception e) {
            e.printStackTrace();
            System.out.print("Ein unerwarteter Fehler trat auf. Bitte wenden Sie sich an den Administrator.");
        }
	}

}
