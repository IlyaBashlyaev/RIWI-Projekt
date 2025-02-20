package src.methods;
public class Methods {
    // Die Berechnung der Summe
    public static float calcSum(float[] prices) {
        float sum = 0f;
        for (float price : prices) {
            sum += price;
        }
        return sum;
    }

    // Die Berechnung der Mehrwertsteuer
    public static float calcVAT(float[] prices, boolean defaultVAT) {
        float sum = 0f,
              VAT = defaultVAT ? 0.19f : 0.07f;
        for (float price : prices) {
            sum += price;
        }
        return sum * VAT;
    }
}