package kmwe.afw.infogame.service;

import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.strategy.StrategyDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    ResponseEntity<StrategyDTO> getStrategy(String name);
    List<StrategyDTO> getStrategySearchForGame(String nameGame);
    ResponseEntity<CompanyDTOFull> getCompany(String name);
    ResponseEntity<GameDTOFull> getGame(String name);
    ResponseEntity<Resource> getImage(String name, HttpServletRequest request);
    ResponseEntity<String> createStrategy(StrategyDTO strategyDTO);
    List<GameDTO> getAllGames();
    List<CompanyDTO> getAllCompanies();
}
