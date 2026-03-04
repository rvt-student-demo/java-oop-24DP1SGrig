package rvt.BoxInterface;

import java.util.ArrayList;

public class Box implements Packable {
    double capacity;
    double total;
    ArrayList<Packable> box = new ArrayList<Packable>();

    public Box(double capacity) {
        this.capacity = capacity;
    }
    
    public void add(Packable item) {
        box.add(item);
    }

    @Override
    public String toString() {
        if (weight() > capacity) {
            return "Too much weight!!!";
        } else {
        return "Box: " + box.size() + " items, total weight " + weight() + " kg";
        }
    }

    @Override
    public double weight() {
        double total = 0;
        for (Packable elem : box) {
            total += elem.weight();
        }
        return total;
    }
}
