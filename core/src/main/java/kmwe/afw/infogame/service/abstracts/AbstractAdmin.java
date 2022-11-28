package kmwe.afw.infogame.service.abstracts;

import com.fasterxml.jackson.databind.ObjectMapper;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.mapper.CompanyMapper;
import kmwe.afw.infogame.mapper.GameMapper;
import kmwe.afw.infogame.mapper.UserMapper;
import kmwe.afw.infogame.model.Company;
import kmwe.afw.infogame.payload.CompanyInfo;
import kmwe.afw.infogame.repository.CompanyRepository;
import kmwe.afw.infogame.repository.GameRepository;
import kmwe.afw.infogame.repository.UserRepository;
import kmwe.afw.infogame.service.FileStorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.util.Optional;

@Component
public abstract class AbstractAdmin extends AbstractUser implements AdminFunction {
    private final GameRepository gameRepository;
    private final CompanyRepository companyRepository;
    private final GameMapper gameMapper;
    private final CompanyMapper companyMapper;
    private final FileStorageService fileStorageService;

    public AbstractAdmin(UserRepository userRepository,
                         UserMapper userMapper,
                         ObjectMapper objectMapper,
                         GameRepository gameRepository,
                         CompanyRepository companyRepository,
                         GameMapper gameMapper,
                         CompanyMapper companyMapper,
                         FileStorageService fileStorageService) {
        super(userRepository, userMapper, objectMapper);
        this.gameRepository = gameRepository;
        this.companyRepository = companyRepository;
        this.gameMapper = gameMapper;
        this.companyMapper = companyMapper;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public ResponseEntity<CompanyInfo> getCompany(String name, HttpServletRequest request) {
        Company company = companyRepository.getCompaniesByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Компания не найдена"));

        Path path = fileStorageService.loadImageAsResource(name);

        String contentType = null;

        /*try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        /*return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);*/


        return ResponseEntity.ok()
                .body(new CompanyInfo(company.getName(), path));
    }

    @Override
    public ResponseEntity<String> uploadGameInfo(GameDTOFull gameDTOFull) {
        return Optional.of(gameDTOFull)
                .map(gameMapper::getFromDTOOfFull)
                .map(saveGame -> {
                    if (gameDTOFull.getName().isEmpty() || gameDTOFull.getName().isBlank()
                    || gameDTOFull.getCompanyId() == 0 || gameDTOFull.getPublisherId() == 0) {
                        return new ResponseEntity<>("Некорректные данные", HttpStatus.BAD_REQUEST);
                    } else {
                        gameRepository.save(saveGame);
                        return new ResponseEntity<>("Игра добавлена", HttpStatus.OK);
                    }
                }).orElseThrow(() -> new EntityNotFoundException("Невозможно создать страницу игры"));
    }

    @Override
    public ResponseEntity<String> uploadCompanyInfo(String name, MultipartFile logo) {
        getInfoOfCompany(logo, name);

        Company company = new Company();
        company.setName(name);

        companyRepository.save(company);

        return new ResponseEntity<>("Компания успешно сохранена", HttpStatus.CREATED);

        /*return Optional.of(companyDTOFull)
                .map(companyMapper::getFromDTOOfFull)
                .map(saveCompany -> {
                    if (companyDTOFull.getName().isEmpty() || companyDTOFull.getName().isBlank()) {
                        return new ResponseEntity<>("Некорректные даные для компании", HttpStatus.BAD_REQUEST);
                    } else {

                        companyRepository.save(saveCompany);
                        return new ResponseEntity<>("Компания добавлена", HttpStatus.OK);
                    }
                }).orElseThrow(() -> new EntityNotFoundException("Невозможно создать страницу компании"));*/
    }

    private ResponseEntity<String> getInfoOfCompany(MultipartFile file, String name) {
        String fileName = fileStorageService.storeFile(file, name);

        if (FilenameUtils.getExtension(fileName).equals("PNG") || FilenameUtils.getExtension(fileName).equals("png")
                || FilenameUtils.getExtension(fileName).equals("JPEG") || FilenameUtils.getExtension(fileName).equals("jpeg")) {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadLogo")
                    .path(fileName)
                    .toUriString();

            return new ResponseEntity<>("Лого сохранено", HttpStatus.OK);
        }

        return new ResponseEntity<>("Ошибка сохранения изображения", HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<String> deleteGame(String name) {
        return gameRepository.getGameByName(name)
                .map(deleteGame -> {
                    gameRepository.delete(deleteGame);
                    return new ResponseEntity<>("Игра " + name + " удалена", HttpStatus.OK);
                }).orElseThrow(() -> new EntityNotFoundException("Невозможно удалить игру: " + name));
    }

    @Override
    public ResponseEntity<String> deleteCompany(String name) {
        return companyRepository.getCompaniesByName(name)
                .map(deleteCompany -> {
                    companyRepository.delete(deleteCompany);
                    return new ResponseEntity<>("Компания " + name + " удалена", HttpStatus.OK);
                }).orElseThrow(() -> new EntityNotFoundException("Невозможно удалить компанию: " + name));
    }
}
