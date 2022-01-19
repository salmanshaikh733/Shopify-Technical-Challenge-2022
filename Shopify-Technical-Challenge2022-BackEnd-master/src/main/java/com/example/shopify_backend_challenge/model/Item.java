package com.example.shopify_backend_challenge.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items")
public class Item implements Serializable {

    private static final long serialVersionUID = 7398151513260624831L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_quantity")
    private int quantity;

    @Column (name = "item_price")
    private double price;

    public Item() {

    }

    public Item(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
