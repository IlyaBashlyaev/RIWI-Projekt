package src.util;
public class CalculatorUtil {
    // Die Berechnung der Summe
    public float calcSum(float[] prices) {
        float sum = 0f;
        for (float price : prices) {
            sum += price;
        }
        return sum;
    }

    // Die Berechnung der Mehrwertsteuer
    public float calcVAT(float[] prices, float[] types) {
        float sum = 0f;
        for (int i = 0; i < prices.length; i++) {
            sum += prices[i] * types[i];
        }
        return sum;
    }
}