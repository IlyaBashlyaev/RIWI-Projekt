import java.util.Scanner;

class Main {
    public static float calcSum(float[] prices) {
        float sum = 0f;
        for (float price : prices) {
            if (price > 0) sum += price;
        }
        return sum;
    }

    public static float calcVAT(float[] prices, boolean defaultVAT) {
        float sum = 0f,
              VAT = defaultVAT ? 0.19f : 0.07f;
        for (float price : prices) {
            if (price > 0) sum += price;
        }
        return sum * VAT;
    }

    public static int countProducts(float[] prices) {
        int count = 0;
        for (float price : prices) {
            if (price > 0) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Hallo! Das ist ein Rechner-Programm von RIWI.\n");
        
        int n = 6;
        Scanner input = new Scanner(System.in);
        while (n > 5) {
            System.out.print("Geben Sie bitte die Anzahl der Produkte ein: ");
            n = input.nextInt();
            if (n > 5) System.out.println("Bitte geben Sie maximal \"5\" ein!");
        }

        String names[] = new String [6];
        float prices[] = new float [6];
        System.out.println();

        for (int i = 0; i < n; i++) {
            Scanner products = new Scanner(System.in);
            System.out.print("Der Name vom Produkt #" + (i + 1) + ": ");
            String name = products.nextLine();
            names[i] = name;
            System.out.print("Der Preis vom Produkt #" + (i + 1) + ": ");
            int price = products.nextInt();
            prices[i] = price;
        }

        float sum = calcSum(prices);
        float VAT = calcVAT(prices, true);
        int count = countProducts(prices);

        System.out.println("\nDie Werte werden unten ausgegeben:");
        System.out.print("Die Summe von Preisen (ohne MwSt.): ");
        System.out.println(sum);
        System.out.print("Der Mehrwertsteuer: ");
        System.out.println(VAT);
        System.out.print("Die Anzahl der Produkte: ");
        System.out.println(count);
    }
}