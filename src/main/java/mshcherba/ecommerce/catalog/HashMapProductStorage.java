package mshcherba.ecommerce.catalog;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HashMapProductStorage implements ProductStorage {
    private final HashMap<String, Product> productMap;

    public HashMapProductStorage() {
        this.productMap=new HashMap<>();
    }

    @Override
    public List<Product> allProducts() {
        return productMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public void add(Product newProduct) {
        productMap.put(newProduct.getId(),newProduct);

    }

    @Override
    public Product getProductBy(String id) {
        return productMap.get(id);
    }


}