package src.utils;

import java.util.HashMap;
import java.util.Map;

public class CalculatorUtil {
    /**
     * Die Berechnung der Summe
     * @param prices Die Preise der Produkte
     * @return Die Summe der Produkte
     */
    public static float calcSum(HashMap<Integer, Float> prices) {
        float sum = 0f;
        for (Map.Entry<Integer, Float> price : prices.entrySet()) sum += price.getValue();
        return sum;
    }

    /**
     * Die Berechnung der Mehrwertsteuer
     * @param prices Die Preise der Produkte
     * @param types Die Typen der Produkte (Grundbedarf oder Konsumgut)
     * @return Der gesamte Mehrwertsteuer der Produkte
     */
    public static float calcVAT(HashMap<Integer, Float> prices, HashMap<Integer, Float> types) {
        float VAT = 0f;
        int key;

        for (Map.Entry<Integer, Float> price : prices.entrySet()) {
            key = price.getKey();
            VAT += price.getValue() / (1 + types.get(key)) * types.get(key);
        }
        return VAT;
    }
}