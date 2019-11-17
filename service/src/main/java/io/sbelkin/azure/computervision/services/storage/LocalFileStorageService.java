package io.sbelkin.azure.computervision.services.storage;

import io.sbelkin.azure.computervision.services.storage.configurations.LocalStorageConfiguration;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class LocalFileStorageService implements StorageService {

    private static Logger logger = LoggerFactory.getLogger(AzureStorageService.class);

    private File directory;

    @Autowired
    private LocalStorageConfiguration configuration;

    @Override
    @PostConstruct
    public void init() {
        this.directory = new File(configuration.getFilePath());
        if (!directory.exists()) {
            directory.mkdirs();
        }
        logger.info("Files stored in : {}", directory.getAbsolutePath());
    }

    @Override
    public File store(MultipartFile multipartFile) throws IOException {
        File file = new File(directory, multipartFile.getOriginalFilename());
        FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        return file;
    }

    @Override
    public List<String> listAll() {
        return Arrays.asList(directory.list());
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
        Arrays.stream(directory.listFiles()).forEach(file -> file.delete());
    }
}
