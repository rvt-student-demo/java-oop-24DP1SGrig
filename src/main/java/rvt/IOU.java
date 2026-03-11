package rvt;

import java.util.HashMap;

public class IOU {
    HashMap<String, Double> map = new HashMap<>();

    public IOU() {

    }

    public void setSum(String toWhom, double amount) {
        map.put(toWhom, amount);
    }

    public double howMuchDoIOweTo(String toWhom) {
        return map.get(toWhom);
    }
}
