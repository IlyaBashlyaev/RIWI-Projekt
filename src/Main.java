package src;
import java.util.Scanner;
import src.util.CalculatorUtil;
import java.util.InputMismatchException;
import java.lang.Math;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NumberFormatException;

public class Main {
    public static void main(String[] args) {
        // Die Begrüßung
        CalculatorUtil calculator = new CalculatorUtil();
        System.out.println("Hallo! Das ist ein Rechner-Programm von RIWI.\n");

        try {
            // Die Eingabe der Anzahl
            System.out.print("Geben Sie zuerst die Anzahl der Produkte ein: ");
            int n = new Scanner(System.in).nextInt();
            String products[] = new String [n];
            float prices[] = new float [n];
            float types[] = new float [n];
            char type;

            // Die Eingabe der Produkte
            System.out.println("\nSie müssen " + n + " Mal den Namen des Produkts, den Preis des Produkts (mit \".\" als Komma) und den Typ (\"g\" für Grundbedarf oder \"k\" für Konsumgut) des Produkts mit \";\" getrennt eingeben.\n");
            for (int i = 0; i < n; i++) {
                System.out.print("Geben Sie drei Felder für das Produkt #" + (i + 1) + " ein: ");
                String data[] = new Scanner(System.in).nextLine().split(";");
                type = data[2].charAt(0);

                // Die Speicherung der Daten aus der Eingabe
                if (type == 'g' || type == 'k') {
                    products[i] = data[0];
                    prices[i] = Math.abs(Float.parseFloat(data[1]));
                    types[i] = type == 'g' ? 0.07f : 0.19f;
                }

                // Die Ausgabe des Fehlers mit dem Typ
                else {
                    System.out.println("Bitte geben Sie nur \"g\" oder \"k\" für den Typ des Produkts ein!");
                    i--;
                }
            }

            // Die Berechnung der Werte
            float sum = calculator.calcSum(prices);
            float VAT = calculator.calcVAT(prices, types);

            // Die ausgegebene Information für den Benutzer
            System.out.println("\nDie Gesamtsumme: " + String.format("%.2f", sum));
            System.out.println("Der Mehrwertsteuer: " + String.format("%.2f", VAT));
            System.out.println("Die Anzahl der Produkte: " + n);
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
    }
}