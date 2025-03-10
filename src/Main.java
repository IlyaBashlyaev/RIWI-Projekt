package src;
import java.util.Scanner;
import src.util.CalculatorUtil;
import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NumberFormatException;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Die Begrüßung
        CalculatorUtil calculator = new CalculatorUtil();
        System.out.println("Hallo! Das ist ein Rechner-Programm von RIWI.\n");
        int n = 6;

        try {
            // Die Eingabe der Anzahl
            System.out.print("Geben Sie bitte die Anzahl der Produkte ein: ");
            n = new Scanner(System.in).nextInt();
            String products[] = new String [n];
            float prices[] = new float [n];
            float types[] = new float [n];

            // Die Eingabe der Produkte
            System.out.print("\nSie müssen " + n + " Mal den Namen des Produkts, den Preis des Produkts und den Typ (\"true\" für Grundbedarf oder \"false\" für Konsumgut) des Produkts eingeben.\n");
            for (int i = 0; i < n; i++) {
                System.out.print("Geben Sie drei Felder für das Produkt #" + (i + 1) + " mit \";\" getrennt ein: ");
                String data[] = new Scanner(System.in).nextLine().split(";");
                products[i] = data[0];
                prices[i] = Float.parseFloat(data[1]);
                types[i] = Boolean.parseBoolean(data[2]) ? 0.19f : 0.07f;
            }

            // Das Berechnung der Werte
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
            System.out.println("\nSie haben keine Zahl eingegeben!");
        }

        catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("\nSie haben falsch den Namen und den Preis des Produkts eingegeben!");
        }

        catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("\nSie haben keine Zahl beim Preis eingegeben!");
        }
    }
}