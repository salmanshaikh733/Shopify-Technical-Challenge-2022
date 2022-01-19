package com.example.shopify_backend_challenge.service;

import com.example.shopify_backend_challenge.exception.InvalidInputException;
import com.example.shopify_backend_challenge.exception.ResourceNotFoundException;
import com.example.shopify_backend_challenge.model.Item;
import com.example.shopify_backend_challenge.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ItemServiceImplementationTest {

    @MockBean
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImplementation itemService;

    @Test
    void getAllItems() {
        Item item1 = new Item("item1", 3, 7);
        Item item2 = new Item("item2", 2, 5);
        Item item3 = new Item("item3", 4, 4);
        Item item4 = new Item("item4", 1, 3);

        List<Item> itemsList = Arrays.asList(item1, item2, item3, item4);

        when(itemRepository.findAll()).thenReturn(itemsList);

        List<Item> result = itemService.getAllItems();

        assertEquals(4, result.size());
        assertTrue(result.containsAll(itemsList));
    }

    @Test
    void addNewItem() {
        Item newItem = new Item("itemToAdd", 4, 5.5);

        when(itemRepository.save(newItem)).thenReturn(newItem);
        itemService.addNewItem(newItem);

        verify(itemRepository, times(1)).save(newItem);
    }

    @Test
    void getItem() {
        when(itemRepository.findById(3L)).thenThrow(new ResourceNotFoundException("Item not found"));
        assertThrows(ResourceNotFoundException.class, () -> itemService.getItem(3L));

        when(itemRepository.findById(4L)).thenReturn(Optional.of(new Item("foundItem", 4, 4)));

        Item item = itemService.getItem(4L);
        assertEquals("foundItem", item.getItemName());
    }

    @Test
    void checkIfItemExists() {

        when(itemRepository.existsById(3L)).thenReturn(true);
        boolean ifExists = itemService.checkIfItemExists(3L);

        assertEquals(true, ifExists);

        when(itemRepository.existsById(4L)).thenReturn(false);
        boolean doesNotExist = itemService.checkIfItemExists(4L);

        assertEquals(false, doesNotExist);
    }

    @Test
    void updateItem() {
        Item validItemNewInfo = new Item("validItem", 4, 5.6);
        Item invalidItemNewInfo = new Item("InvalidItem", -2, -54.43);

        Item invalidSavedItem = new Item("InvalidItem", 5, 5);
        Item validSavedItem = new Item("Valid Saved Item", 100, 10.4);

        when(itemRepository.findById(4L)).thenReturn(Optional.of(invalidSavedItem));

        when(itemRepository.findById(3L)).thenReturn(Optional.of(validSavedItem));
        when(itemRepository.save(validSavedItem)).thenReturn(validItemNewInfo);

        Item res = itemService.updateItem(3L, validItemNewInfo);

        assertEquals("validItem", res.getItemName());
        assertThrows(InvalidInputException.class, () -> itemService.updateItem(4L, invalidItemNewInfo));
    }

    @Test
    void deleteItem() {
        Item deleteMe = new Item();

        itemService.deleteItem(deleteMe);

        verify(itemRepository, times(1)).delete(deleteMe);
    }

    @Test
    void changeItemQuantity() {
        Item toBeIncremented = new Item("incrementMe", 5, 6.6);
        Item incrementedItem = new Item("incrementMe", 6, 6.6);

        when(itemRepository.findById(5L)).thenReturn(Optional.of(toBeIncremented));
        when(itemRepository.save(toBeIncremented)).thenReturn(incrementedItem);

        Item res = itemService.changeItemQuantity(5L, true);

        assertEquals(6, res.getQuantity());
    }

    @Test
    void changeItemQuantityNegativeQuantity() {
        Item quantityZero = new Item("decrementMe", 0, 5.5);

        when(itemRepository.findById(5L)).thenReturn(Optional.of(quantityZero));

        assertThrows(InvalidInputException.class, () -> itemService.changeItemQuantity(5L, false));
    }
}