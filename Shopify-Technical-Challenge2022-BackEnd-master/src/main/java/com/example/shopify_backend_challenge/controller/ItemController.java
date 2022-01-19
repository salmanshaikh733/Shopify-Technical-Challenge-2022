package com.example.shopify_backend_challenge.controller;

import com.example.shopify_backend_challenge.exception.ResourceNotFoundException;
import com.example.shopify_backend_challenge.model.Item;
import com.example.shopify_backend_challenge.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/")
@CrossOrigin (origins = "http://localhost:3000")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/add-item")
    public void addNewItem(@RequestBody Item item) {
        itemService.addNewItem(item);
    }

    @GetMapping("/get-item/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        Item item = itemService.getItem(id);

        return ResponseEntity.ok(item);
    }

    @PutMapping("/update-item/{id}")
    public ResponseEntity<Item> updateItem (@PathVariable Long id, @RequestBody Item updatedItem){
        if(itemService.checkIfItemExists(id)) {
            Item item = itemService.updateItem(id, updatedItem);
            return ResponseEntity.ok(item);
        } else {
            throw new ResourceNotFoundException("Item not found: " + id);
        }
    }

    @DeleteMapping("/delete-item/{id}")
    public void deleteItem(@PathVariable Long id) {
        if (itemService.checkIfItemExists(id)) {
            Item item = itemService.getItem(id);
            itemService.deleteItem(item);
        } else {
            throw new ResourceNotFoundException("Item failed to be deleted, does not exist with id:" + id);
        }
    }

    @PutMapping("/change-item-quantity/{id}/{operation}")
    public ResponseEntity<Item> changeQuantity(@PathVariable Long id, @PathVariable boolean operation) {
        if (itemService.checkIfItemExists(id)) {
            Item item = itemService.changeItemQuantity(id, operation);
            return ResponseEntity.ok(item);
        } else {
            throw new ResourceNotFoundException("Cannot update quantity item with id " + id + "not found");
        }
    }

}
