package kmwe.afw.infogame.service.abstracts;

import com.fasterxml.jackson.databind.ObjectMapper;
import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.mapper.CompanyMapper;
import kmwe.afw.infogame.mapper.GameMapper;
import kmwe.afw.infogame.mapper.UserMapper;
import kmwe.afw.infogame.model.Company;
import kmwe.afw.infogame.repository.CompanyRepository;
import kmwe.afw.infogame.repository.GameRepository;
import kmwe.afw.infogame.repository.UserRepository;
import kmwe.afw.infogame.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
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
    public ResponseEntity<String> uploadGameInfo(GameDTOFull gameDTOFull, MultipartFile logoGame) {
        writeLogoInFile(logoGame, gameDTOFull.getName());

        return Optional.of(gameDTOFull)
                .map(gameMapper::getFromDTOOfFull)
                .map(saveGame -> {
                    if (gameDTOFull.getName().isEmpty() || gameDTOFull.getName().isBlank()
                    || gameDTOFull.getCompanyId() == 0 || gameDTOFull.getPublisherId() == 0) {
                        log.error("Некорректные данные о игре, игра не сохранена");
                        return new ResponseEntity<>("Некорректные данные", HttpStatus.BAD_REQUEST);
                    } else {
                        gameRepository.save(saveGame);
                        log.info("Игра сохранена");
                        return new ResponseEntity<>("Игра добавлена", HttpStatus.CREATED);
                    }
                }).orElseThrow(() -> new EntityNotFoundException("Невозможно создать страницу игры"));
    }

    @Override
    public ResponseEntity<String> uploadCompanyInfo(CompanyDTOFull companyDTOFull, MultipartFile logoCompany) {
        writeLogoInFile(logoCompany, companyDTOFull.getName());

        return Optional.of(companyDTOFull)
                .map(companyMapper::getFromDTOOfFull)
                .map(saveCompany -> {
                    if (companyDTOFull.getName().isEmpty() || companyDTOFull.getName().isBlank()) {
                        log.error("Некорректные данные о компании, компания не сохранена");
                        return new ResponseEntity<>("Некорректные данные", HttpStatus.BAD_REQUEST);
                    } else {
                        companyRepository.save(saveCompany);
                        log.info("Компания сохранена");
                        return new ResponseEntity<>("Компания добавлена", HttpStatus.CREATED);
                    }
                }).orElseThrow(() -> new EntityNotFoundException("Невозможно создать страницу компании"));

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

    @Override
    public ResponseEntity<String> refreshCompanyInfo(String name, CompanyDTOFull companyDTOFull) {
        return companyRepository.getCompaniesByName(name)
                .map(update -> {
                    if (companyDTOFull.getName().isBlank() || companyDTOFull.getName().isEmpty()) {
                        return new ResponseEntity<>("Пустое новое имя, обновление не применено", HttpStatus.BAD_REQUEST);
                    }
                    companyMapper.refreshFull(companyDTOFull, update);
                    companyRepository.save(update);

                    log.info("Данные о компании обновлены");
                    return new ResponseEntity<>("Данные о компании обновлены", HttpStatus.OK);
                }).orElseThrow(() -> new EntityNotFoundException("Компания не найдена"));
    }

    @Override
    public ResponseEntity<String> refreshGameInfo(String name, GameDTOFull gameDTOFull) {
        return gameRepository.getGameByName(name)
                .map(update -> {
                    if (gameDTOFull.getName().isBlank() || gameDTOFull.getName().isEmpty()) {
                        log.error("Невалидные данные для обновления игры");
                        return new ResponseEntity<>("Пустое новое имя, обновление не применено", HttpStatus.BAD_REQUEST);
                    }
                    gameMapper.refreshFull(gameDTOFull, update);
                    gameRepository.save(update);

                    log.info("Данные о игре обновлены");
                    return new ResponseEntity<>("Данные о компании обновлены", HttpStatus.OK);
                }).orElseThrow(() -> new EntityNotFoundException("Компания не найдена"));
    }

    private ResponseEntity<String> writeLogoInFile(MultipartFile file, String name) {
        String fileName = fileStorageService.storeFile(file, name);

        if (FilenameUtils.getExtension(fileName).equals("PNG") || FilenameUtils.getExtension(fileName).equals("png")
                || FilenameUtils.getExtension(fileName).equals("JPEG") || FilenameUtils.getExtension(fileName).equals("jpeg")) {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadLogo")
                    .path(fileName)
                    .toUriString();

            return new ResponseEntity<>("Лого сохранено", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Ошибка сохранения изображения", HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<String> deleteGame(String name) {
        return gameRepository.getGameByName(name)
                .map(deleteGame -> {
                    gameRepository.delete(deleteGame);
                    fileStorageService.deleteImage(name);
                    return new ResponseEntity<>("Игра " + name + " удалена", HttpStatus.OK);
                }).orElseThrow(() -> new EntityNotFoundException("Невозможно удалить игру: " + name));
    }

    @Override
    public ResponseEntity<String> deleteCompany(String name) {
        return companyRepository.getCompaniesByName(name)
                .map(deleteCompany -> {
                    companyRepository.delete(deleteCompany);
                    fileStorageService.deleteImage(name);
                    return new ResponseEntity<>("Компания " + name + " удалена", HttpStatus.OK);
                }).orElseThrow(() -> new EntityNotFoundException("Невозможно удалить компанию: " + name));
    }
}
