package kmwe.afw.infogame.service;

import kmwe.afw.infogame.properties.ImageStoriesProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Path loadImageAsResource(String filename) {
        if (filename.contains(" ")) {
            filename = filename.replace(" ", "_");
        }
        String fileNameChanged = filename + ".png";

        Path path = this.fileStorageLocation.resolve(fileNameChanged).normalize();
        /*try {
            System.out.println(path);
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                log.error("Изображение не может быть получено");
                throw new RuntimeException("");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }*/

        return path;
    }
}
