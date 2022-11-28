package kmwe.afw.infogame.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileStorageService {
    String storeFile(MultipartFile file, String name);
    Path loadImageAsResource(String filename);
}
