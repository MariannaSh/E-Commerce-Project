package mshcherba.ecommerce.sales.cart;

import java.util.*;

public class Cart {

    Map<String, Integer> products;

    public Cart() {
        this.products = new HashMap<>();
    }

    public static Cart empty() {
        return new Cart();
    }

    public void addProduct(String productId) {
        if (!isInCart(productId)) {
            putIntoCart(productId);
        } else {
            increaseProductQty(productId);
        }
    }
    private boolean isInCart(String productId) {
        return products.containsKey(productId);
    }
    private void putIntoCart(String productId) {
        products.put(productId, 1);
    }
    private void increaseProductQty(String productId) {
        products.put(productId, products.get(productId) + 1);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public Integer getItemsCount() {
        return products.size();
    }

    public List<CartLine> getItems() {
        return products.entrySet().stream()
                .map(es -> new CartLine(es.getKey(), es.getValue()))
                .toList();
    }


}