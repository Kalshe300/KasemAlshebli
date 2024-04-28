package sppcw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Deductions {
    private Map<String, Double> allowableDeductions;

    public Deductions() {
        this.allowableDeductions = new HashMap<String, Double>();
    }

    public void addDeduction(String description, double amount) {
        this.allowableDeductions.put(description, amount);
    }

    public double totalDeductions(){
        double total = 0;
        for (Double d : allowableDeductions.values()){
            total = d;
        }
        return total;
    }

    public String listOfDeductions(){
        if (allowableDeductions.size() == 0){
            return "no deductions";
        }
        StringBuilder keysAsString = new StringBuilder();
        keysAsString.append("deductions:");
        for (String key : allowableDeductions.keySet()) {
            if (keysAsString.length() > 0) {
                keysAsString.append(",");
            }
            keysAsString.append(key);
        }
        return keysAsString.toString();
    }
}
