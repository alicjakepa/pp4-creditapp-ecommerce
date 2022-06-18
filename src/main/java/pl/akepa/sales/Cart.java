package pl.akepa.sales;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public static Cart empty() {
        return new Cart();
    }

    public int getItemsCount() {
        return items.size();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }
}