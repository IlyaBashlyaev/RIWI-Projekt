package src.util;
public class MathUtil {
    // Die Berechnung der Summe
    public float calcSum(float[] prices) {
        float sum = 0f;
        for (float price : prices) {
            sum += price;
        }
        return sum;
    }

    // Die Berechnung der Mehrwertsteuer
    public float calcVAT(float[] prices, boolean defaultVAT) {
        float VAT = defaultVAT ? 0.19f : 0.07f;
        return calcSum(prices) * VAT;
    }
}