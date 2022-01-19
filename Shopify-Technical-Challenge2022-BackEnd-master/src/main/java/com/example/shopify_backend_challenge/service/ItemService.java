package com.example.shopify_backend_challenge.service;

import com.example.shopify_backend_challenge.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    void addNewItem(Item item);

    Item getItem(Long id);

    boolean checkIfItemExists(Long id);

    Item updateItem(Long id, Item newItemInfo);

    void deleteItem(Item itemId);

    Item changeItemQuantity(Long id, boolean operation);

}
