package kmwe.afw.infogame.service.abstracts;

import com.fasterxml.jackson.databind.ObjectMapper;
import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.mapper.CompanyMapper;
import kmwe.afw.infogame.mapper.GameMapper;
import kmwe.afw.infogame.mapper.UserMapper;
import kmwe.afw.infogame.repository.CompanyRepository;
import kmwe.afw.infogame.repository.GameRepository;
import kmwe.afw.infogame.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
public abstract class AbstractAdmin extends AbstractUser implements AdminFunction {
    private final GameRepository gameRepository;
    private final CompanyRepository companyRepository;
    private final GameMapper gameMapper;
    private final CompanyMapper companyMapper;

    public AbstractAdmin(UserRepository userRepository, UserMapper userMapper, ObjectMapper objectMapper, GameRepository gameRepository, CompanyRepository companyRepository, GameMapper gameMapper, CompanyMapper companyMapper) {
        super(userRepository, userMapper, objectMapper);
        this.gameRepository = gameRepository;
        this.companyRepository = companyRepository;
        this.gameMapper = gameMapper;
        this.companyMapper = companyMapper;
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
    public ResponseEntity<String> uploadCompanyInfo(CompanyDTOFull companyDTOFull) {
        return Optional.of(companyDTOFull)
                .map(companyMapper::getFromDTOOfFull)
                .map(saveCompany -> {
                    if (companyDTOFull.getName().isEmpty() || companyDTOFull.getName().isBlank()) {
                        return new ResponseEntity<>("Некорректные даные для компании", HttpStatus.BAD_REQUEST);
                    } else {
                        companyRepository.save(saveCompany);
                        return new ResponseEntity<>("Компания добавлена", HttpStatus.OK);
                    }
                }).orElseThrow(() -> new EntityNotFoundException("Невозможно создать страницу компании"));
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
