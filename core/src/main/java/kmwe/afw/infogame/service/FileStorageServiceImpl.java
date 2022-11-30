package kmwe.afw.infogame.service;

import kmwe.afw.infogame.properties.ImageStoriesProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class FileStorageServiceImpl implements FileStorageService{
    private final Path fileStorageLocation;

    public FileStorageServiceImpl(ImageStoriesProperties imageStoriesProperties) {
        this.fileStorageLocation = Paths.get(imageStoriesProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String storeFile(MultipartFile file, String name) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (name.contains(" ")) {
            name = name.replace(" ", "_");
        }
        String fileNameChanged = name + "." + FilenameUtils.getExtension(fileName);
        System.out.println(fileNameChanged);

        try {
            if (fileNameChanged.contains("..")) {
                log.error("Файл имеет не валидный путь");
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileNameChanged);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Path loadImageAsReference(String filename) {
        if (filename.contains(" ")) {
            filename = filename.replace(" ", "_");
        }
        String fileNameChanged = filename + ".png";

        return this.fileStorageLocation.resolve(fileNameChanged).normalize();
    }

    @Override
    public Resource loadImageAsResource(String name) {
        if (name.contains(" ")) {
            name = name.replace(" ", "_");
        }
        String fileNameChanged = name + ".png";

        try {
            Path path = this.fileStorageLocation.resolve(fileNameChanged).normalize();
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Файл не найден");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteImage(String name) {
        if (name.contains(" ")) {
            name = name.replace(" ", "_");
        }
        String fileNameChanged = name + ".png";

        Path path = this.fileStorageLocation.resolve(fileNameChanged).normalize();
        File file = new File(path.toUri());

        if (file.delete()) {
            return "Лого удалено";
        } else {
            return "Ошибка при удалении изображения";
        }
    }
}
