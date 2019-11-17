package io.sbelkin.azure.computervision.controllers;

import io.sbelkin.azure.computervision.exceptions.AzureComputerVisionException;
import io.sbelkin.azure.computervision.models.Beer;
import io.sbelkin.azure.computervision.services.BeerListExtraction;
import io.sbelkin.azure.computervision.models.Region;
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
@RequestMapping("menu-to-beer")
public class MenuToBeerController {

    @Autowired
    private BeerListExtraction beerListExtraction;

    @PostMapping("/")
    public ResponseEntity<List<Beer>> menuToBeerListExtraction(@RequestParam("file") MultipartFile file)
            throws IOException, AzureComputerVisionException {
        return ResponseEntity.ok().body(beerListExtraction.convert(file));
    }
}