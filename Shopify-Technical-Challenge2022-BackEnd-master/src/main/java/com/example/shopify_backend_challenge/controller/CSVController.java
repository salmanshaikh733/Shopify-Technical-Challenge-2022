package com.example.shopify_backend_challenge.controller;


import com.example.shopify_backend_challenge.service.CSVServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class CSVController {

    @Autowired
    private CSVServiceImplementation csvService;

    @GetMapping("/download-csv")
    public ResponseEntity<Resource> getCSV() {
        String fileName = "inventoryItems.csv";

        InputStreamResource csvFile = new InputStreamResource(csvService.loadCsv());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(csvFile);
    }
}
