package io.sbelkin.azure.computervision.controllers;

import io.sbelkin.azure.computervision.exceptions.AzureComputerVisionException;
import io.sbelkin.azure.computervision.extractions.MenuExtractions;
import io.sbelkin.azure.computervision.models.Region;
import io.sbelkin.azure.computervision.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("computer-vision")
public class AzureComputerVisionController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private MenuExtractions extractions;

    @PostMapping("/")
    public ResponseEntity<List<Region>> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException, AzureComputerVisionException {
        File uploaded = storageService.store(file);
        return ResponseEntity.ok().body(extractions.getBeerList(uploaded));
    }


}