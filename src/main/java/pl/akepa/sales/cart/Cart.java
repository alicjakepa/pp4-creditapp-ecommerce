package pl.akepa.sales.cart;

import java.util.*;

public class Cart {
    Map<String, CartItem> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public static Cart empty() {
        return new Cart();
    }

    public int getItemsCount() {
        return items.size();
    }

    public void addItem(CartItem item) {
        if (!isItemAlreadyExists(item)) {
            doAddToCart(item);
        } else {
            increaseQuantity(item);
        }
    }

    private void increaseQuantity(CartItem item) {
        items.get(item.getProductId())
                .increaseQuantity();
    }

    private void doAddToCart(CartItem item) {
        items.put(item.getProductId(), item);
    }

    private boolean isItemAlreadyExists(CartItem item) {
        return items.get(item.getProductId()) != null;
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }
}