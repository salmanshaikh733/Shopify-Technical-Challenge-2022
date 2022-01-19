package com.example.shopify_backend_challenge.helperMethods;

import com.example.shopify_backend_challenge.model.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class CSVHelperTest {

    @InjectMocks
    private CSVHelper csvHelper;

    @Test
    void generateCSV() {
        Item item1 = new Item("item1", 3, 7);
        Item item2 = new Item("item2", 2, 5);
        Item item3 = new Item("item3", 4, 4);
        Item item4 = new Item("item4", 1, 3);

        List<Item> itemsList = Arrays.asList(item1, item2, item3, item4);

        InputStream stream = csvHelper.generateCSV(itemsList);

        String expectedOutputString = "Item ID,Item Name,Item Quantity,Item Price\n" +
                "0,item1,3,7.0\n" +
                "0,item2,2,5.0\n" +
                "0,item3,4,4.0\n" +
                "0,item4,1,3.0";

        String response = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        assertEquals(expectedOutputString, response);
    }
}