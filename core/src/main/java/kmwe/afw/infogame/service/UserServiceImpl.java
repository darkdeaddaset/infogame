package kmwe.afw.infogame.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.mapper.CompanyMapper;
import kmwe.afw.infogame.mapper.GameMapper;
import kmwe.afw.infogame.mapper.UserMapper;
import kmwe.afw.infogame.model.Company;
import kmwe.afw.infogame.model.Game;
import kmwe.afw.infogame.payload.CompanyInfo;
import kmwe.afw.infogame.repository.CompanyRepository;
import kmwe.afw.infogame.repository.GameRepository;
import kmwe.afw.infogame.repository.UserRepository;
import kmwe.afw.infogame.service.abstracts.AbstractUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public ResponseEntity<CompanyInfo> getCompany(String name) {
        Company company = companyRepository.getCompaniesByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Компания не найдена"));

        Path path = fileStorageService.loadImageAsResource(name);
        return ResponseEntity.ok()
                .body(new CompanyInfo(company.getName(), path, company.getCountry()));
    }

    @Override
    public ResponseEntity<GameDTOFull> getGame(String name) {
        Game game = gameRepository.getGameByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Игра не найдена"));

        Path path = fileStorageService.loadImageAsResource(name);
        return ResponseEntity.ok()
                .body(new GameDTOFull(game.getName(), game.getCompany().getId(), game.getPublisher().getId(), path));
    }

    @Override
    public GameDTOFull getInfoGame(String name) {
        return gameRepository.getGameByName(name)
                .map(gameMapper::getFromModelForFull)
                .orElseThrow(() -> new EntityNotFoundException("Игра: " + name + " не найдена"));
    }

    @Override
    public List<GameDTOFull> getAllGamesOfCompany(String name) {
        return companyRepository.getCompaniesByName(name)
                .map(gameRepository::getGameByCompany)
                .map(gameMapper::getAllGameDTOFull)
                .orElseThrow(() -> new EntityNotFoundException("Игры от компании: " + name + " не найдены"));
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