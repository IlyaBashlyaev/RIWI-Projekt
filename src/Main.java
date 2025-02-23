package src;
import java.util.Scanner;
import src.calculator.Calculator;
import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NumberFormatException;

public class Main {
    public static void main(String[] args) {
        // Die Begrüßung
        Calculator calculator = new Calculator();
        System.out.println("Hallo! Das ist ein Rechner-Programm von RIWI.\n");

        try {
            int n = 6;
            while (n > 5) {
                System.out.print("Geben Sie bitte die Anzahl der Produkte ein: ");
                n = new Scanner(System.in).nextInt();
                if (n > 5) System.out.println("Bitte geben Sie maximal \"5\" ein!");
            }

            String products[] = new String [5];
            float prices[] = new float [5];
            System.out.println();

            // Die Eingabe des Benutzers
            for (int i = 0; i < n; i++) {
                System.out.print("Geben Sie den Namen und den Preis des Produkts " + (i + 1) + " mit \";\" getrennt ein: ");
                String data[] = new Scanner(System.in).nextLine().split(";");
                products[i] = data[0];
                prices[i] = Float.parseFloat(data[1]);
            }

            // Das Berechnung der Werte
            float sum = calculator.calcSum(prices);
            float VAT = calculator.calcVAT(prices, true); // true = 19% MwSt.

            // Die ausgegebene Information für den Benutzer
            System.out.println("\nDie Gesamtsumme: " + String.format("%.2f", sum));
            System.out.println("Der Mehrwertsteuer: " + String.format("%.2f", VAT));
            System.out.println("Die Anzahl der Produkte: " + prices.length);
        }

        catch (InputMismatchException e) {
            System.out.println("\nSie haben keine Zahl eingegeben!");
        }

        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nSie haben falsch den Namen und den Preis des Produkts eingegeben!");
        }

        catch (NumberFormatException e) {
            System.out.println("\nSie haben keine Zahl beim Preis eingegeben!");
        }
    }
}