package kmwe.afw.infogame.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.mapper.CompanyMapper;
import kmwe.afw.infogame.mapper.GameMapper;
import kmwe.afw.infogame.mapper.UserMapper;
import kmwe.afw.infogame.model.Company;
import kmwe.afw.infogame.model.Game;
import kmwe.afw.infogame.repository.CompanyRepository;
import kmwe.afw.infogame.repository.GameRepository;
import kmwe.afw.infogame.repository.UserRepository;
import kmwe.afw.infogame.service.abstracts.AbstractUser;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends AbstractUser implements UserService {
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final FileStorageService fileStorageService;

    protected UserServiceImpl(UserRepository userRepository,
                              UserMapper userMapper,
                              ObjectMapper objectMapper,
                              GameRepository gameRepository,
                              GameMapper gameMapper,
                              CompanyRepository companyRepository,
                              CompanyMapper companyMapper, FileStorageService fileStorageService) {
        super(userRepository, userMapper, objectMapper);
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public ResponseEntity<CompanyDTOFull> getCompany(String name) {
        Company company = companyRepository.getCompaniesByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Компания не найдена"));

        Path path = fileStorageService.loadImageAsReference(name);
        return ResponseEntity.ok()
                .body(new CompanyDTOFull(company.getName(), path, company.getCountry(), company.getDescription()));
    }

    @Override
    public ResponseEntity<GameDTOFull> getGame(String name) {
        Game game = gameRepository.getGameByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Игра не найдена"));

        Path path = fileStorageService.loadImageAsReference(name);
        return ResponseEntity.ok()
                .body(new GameDTOFull(game.getName(), game.getCompany().getId(), game.getPublisher().getId(), path, game.getDescription()));
    }

    @Override
    public ResponseEntity<Resource> getImage(String name, HttpServletRequest request) {
        Resource resource = fileStorageService.loadImageAsResource(name);

        String typeContent = null;

        try {
            typeContent = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (typeContent == null) {
            typeContent = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(typeContent))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Override
    public List<GameDTO> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::getFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::getFromModel)
                .collect(Collectors.toList());
    }
}