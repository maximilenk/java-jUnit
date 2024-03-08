package edu.school21.models;

import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private int price;

    public Product(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }

        final Product product = (Product) other;
        if (!Objects.equals(product.name, this.name)) {
            return false;
        }

        if (!Objects.equals(product.id, this.id)) {
            return false;
        }

        return this.price == product.price;
    }

    @Override
    public String toString() {
        return "Product(id = "
                + this.id + ", name = "
                + this.name + ", price = "
                + this.price + ")";
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + id.hashCode();
        hash = 31 * hash + name.hashCode();
        hash = 31 * hash + price;
        return hash;
    }
}
