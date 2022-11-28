package kmwe.afw.infogame.service;

import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.model.Company;
import kmwe.afw.infogame.payload.CompanyInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<CompanyInfo> getCompany(String name);
    ResponseEntity<GameDTOFull> getGame(String name);
    GameDTOFull getInfoGame(String name);
    List<GameDTOFull> getAllGamesOfCompany(String name);
    //Company getInfoCompany(String name);
    List<GameDTO> getAllGames();
    List<CompanyDTO> getAllCompanies();
}
