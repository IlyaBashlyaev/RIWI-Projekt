package src;
import src.util.MathUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Definieren der Dinge die benötigt werden
        MathUtil mathUtil = new MathUtil();
        String[] productNames = new String[5];
        float[] prices = new float[5];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            //Eingabe des Nutzers
            for (int i = 0; i < 5; i++) {
                System.out.println("Gib den Namen des Produkts " + (i + 1) + " ein sowie deren Preis mit \";\" getrennt:");
                String[] input = reader.readLine().split(";");
                productNames[i] = input[0];
                prices[i] = Float.parseFloat(input[1]);
            }
            //Speicherung der Ergebnisse der Util
            float sum = mathUtil.calcSum(prices);
            float vat = mathUtil.calcVAT(prices, true); // Standard MwSt.
            //Infos für den Nutzer
            System.out.println("Gesamtsumme: " + String.format("%.2f", sum) + " von den Produkten: " + Arrays.toString(productNames));
            System.out.println("MwSt.: " + String.format("%.2f", vat));
            System.out.println("Anzahl der Produkte: " + prices.length);
        } catch (IOException e) {
            System.out.println("Es gab einen Fehler beim Auslesen der Eingabe.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Die Zahl existiert nicht.");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Bitte in diesem Format eingeben: \"name;preis\"");
            e.printStackTrace();
        }
    }
}