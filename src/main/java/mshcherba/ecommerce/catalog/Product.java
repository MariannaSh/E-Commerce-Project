package mshcherba.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private  String id;
    private  String name;
    private  String description;

    private BigDecimal price;

    Product() {
    }

    public Product(UUID id, String name, String description, BigDecimal price) {
        this.id = id.toString();
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void changePrice(BigDecimal newPrice) {
        this.price = newPrice;
    }
}