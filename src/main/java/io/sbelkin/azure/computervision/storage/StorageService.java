package io.sbelkin.azure.computervision.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface StorageService {

    void init();

    File store(MultipartFile file) throws IOException;

    List<String> listAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

}