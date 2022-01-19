package com.example.shopify_backend_challenge.service;

import com.example.shopify_backend_challenge.model.Item;
import com.example.shopify_backend_challenge.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class CSVServiceImplementationTest {

    @MockBean
    private ItemRepository repository;

    @InjectMocks
    private CSVServiceImplementation csvServiceImplementation;

    @Test
    void loadCsv() {
        Item item1 = new Item("test1", 234, 74);
        Item item2 = new Item("test2", 23423, 55);
        Item item3 = new Item("test3", 4234, 24.653);
        Item item4 = new Item("test4", 234, 33);
        Item item5 = new Item("test5", 234, 35);
        Item item6 = new Item("test6", 342, 36.65);
        Item item7 = new Item("test7", 76, 343.65);

        List<Item> itemsList = Arrays.asList(item1, item2, item3, item4, item5, item6, item7);

        when(repository.findAll()).thenReturn(itemsList);

        InputStream stream = csvServiceImplementation.loadCsv();

        String expectedOutputString = "Item ID,Item Name,Item Quantity,Item Price\n" +
                "0,test1,234,74.0\n" +
                "0,test2,23423,55.0\n" +
                "0,test3,4234,24.653\n" +
                "0,test4,234,33.0\n" +
                "0,test5,234,35.0\n" +
                "0,test6,342,36.65\n" +
                "0,test7,76,343.65";

        String response = new BufferedReader
                (new InputStreamReader(stream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        assertEquals(expectedOutputString, response);
    }
}