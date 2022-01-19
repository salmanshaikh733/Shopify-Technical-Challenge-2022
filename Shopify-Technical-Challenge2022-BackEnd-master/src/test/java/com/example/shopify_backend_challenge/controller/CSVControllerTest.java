package com.example.shopify_backend_challenge.controller;

import com.example.shopify_backend_challenge.service.CSVServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class CSVControllerTest {

    @MockBean
    private CSVServiceImplementation csvService;

    @InjectMocks
    private CSVController csvController;

    @Test
    void getCSV() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        when(csvService.loadCsv()).thenReturn(inputStream);

        ResponseEntity responseEntity = csvController.getCSV();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.parseMediaType("application/csv"), responseEntity.getHeaders().getContentType());
        assertEquals("attachment; filename=inventoryItems.csv", responseEntity.getHeaders().get("Content-Disposition").get(0));
        assertEquals(true, responseEntity.hasBody());
    }
}