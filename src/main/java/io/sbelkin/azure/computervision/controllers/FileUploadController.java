package io.sbelkin.azure.computervision.controllers;

import io.sbelkin.azure.computervision.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("files")
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/")
    public ResponseEntity<List<String>> listFiles() {
        return ResponseEntity.ok().body(storageService.listAll());
    }

    @GetMapping("/name/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public ResponseEntity<Resource> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        storageService.store(file);
        return ResponseEntity.accepted().build();
    }


}