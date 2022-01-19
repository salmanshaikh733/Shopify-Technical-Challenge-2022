package com.example.shopify_backend_challenge.helperMethods;

import com.example.shopify_backend_challenge.exception.CsvException;
import com.example.shopify_backend_challenge.model.Item;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static ByteArrayInputStream generateCSV(List<Item> itemsList) {
        final CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("Item ID", "Item Name", "Item Quantity", "Item Price");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(outputStream), csvFormat);
            for (Item item : itemsList) {
                List<String> itemStringDataList = Arrays.asList(
                        String.valueOf(item.getId()),
                        item.getItemName(),
                        String.valueOf(item.getQuantity()),
                        String.valueOf(item.getPrice())
                );
                csvPrinter.printRecord(itemStringDataList);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            throw new CsvException("Failed to import data from MySQL to generate CSV file");
        }
    }
}
