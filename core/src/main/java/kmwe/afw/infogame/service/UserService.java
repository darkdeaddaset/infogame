package kmwe.afw.infogame.service;

import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.model.Company;

import java.util.List;

public interface UserService {
    GameDTOFull getInfoGame(String name);
    List<GameDTOFull> getAllGamesOfCompany(String name);
    Company getInfoCompany(String name);
    List<GameDTO> getAllGames();
    List<CompanyDTO> getAllCompanies();
}
