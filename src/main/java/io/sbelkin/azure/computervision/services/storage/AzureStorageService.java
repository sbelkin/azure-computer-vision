package io.sbelkin.azure.computervision.services.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class AzureStorageService implements StorageService {

    @Override
    public void init() {

    }

    @Override
    public File store(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public List<String> listAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

}
