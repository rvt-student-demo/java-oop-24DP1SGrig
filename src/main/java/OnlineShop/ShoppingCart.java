package OnlineShop;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<String, Item> items;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    public void add(String product, int price) {
        this.items.compute(product, (name, item) -> {
            if (item == null) {
                return new Item(product, 1, price);
            }
            item.increaseQuantity();
            return item;
        });
    }

    public int price() {
        return this.items.values().stream().mapToInt(item -> item.price()).sum();
    }

    public void print() {
        this.items.values().forEach(item -> System.out.println(item));
    }
}