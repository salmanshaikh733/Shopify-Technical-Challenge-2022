package com.example.shopify_backend_challenge.controller;

import com.example.shopify_backend_challenge.model.Item;
import com.example.shopify_backend_challenge.service.ItemServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ItemControllerTest {

    @MockBean
    private ItemServiceImplementation itemService;

    @InjectMocks
    private ItemController itemController;

    @Test
    void getAllItems() {
        Item item1 = new Item("item1", 3, 7);
        Item item2 = new Item("item2", 2, 5);
        Item item3 = new Item("item3", 4, 4);
        Item item4 = new Item("item4", 1, 3);

        List<Item> itemsList = Arrays.asList(item1, item2, item3, item4);

        when(itemService.getAllItems()).thenReturn(itemsList);

        List<Item> res = itemController.getAllItems();

        assertEquals("item1", res.get(0).getItemName());
        assertEquals(4, res.size());
    }

    @Test
    void addNewItem() {
        Item toBeAdded = new Item("Item", 0, 0);

        itemController.addNewItem(toBeAdded);

        verify(itemService, times(1)).addNewItem(toBeAdded);
    }

    @Test
    void getItem() {
        Item toBeReturned = new Item("Item", 0, 0);

        when(itemService.getItem(3L)).thenReturn(toBeReturned);

        ResponseEntity<Item> res = itemController.getItem(3L);

        assertEquals("Item", res.getBody().getItemName());
    }

    @Test
    void updateItem() {
        Item updateItem = new Item("Item", 0, 0);

        when(itemService.checkIfItemExists(4L)).thenReturn(true);
        when(itemService.updateItem(4L, updateItem)).thenReturn(updateItem);

        ResponseEntity<Item> res = itemController.updateItem(4L, updateItem);

        assertEquals("Item", res.getBody().getItemName());
    }

    @Test
    void deleteItem() {
        Item toBeDeleted = new Item("deleteMe", 5, 5);

        when(itemService.checkIfItemExists(5L)).thenReturn(true);
        when(itemService.getItem(5L)).thenReturn(toBeDeleted);

        itemController.deleteItem(5L);

        verify(itemService, times(1)).deleteItem(toBeDeleted);
    }

    @Test
    void changeQuantity() {
        Item quantityChanged = new Item("Item", 5, 5);

        when(itemService.checkIfItemExists(5L)).thenReturn(true);
        when(itemService.changeItemQuantity(5L, true)).thenReturn(quantityChanged);

        ResponseEntity<Item> res = itemController.changeQuantity(5L, true);

        assertEquals(5, res.getBody().getQuantity());
    }
}