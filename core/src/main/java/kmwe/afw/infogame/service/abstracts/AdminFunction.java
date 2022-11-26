package kmwe.afw.infogame.service.abstracts;

import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTOFull;
import org.springframework.http.ResponseEntity;

public interface AdminFunction extends UserFunction{
    ResponseEntity<String> uploadGameInfo(GameDTOFull gameDTOFull);
    ResponseEntity<String> uploadCompanyInfo(CompanyDTOFull companyDTOFull);
    ResponseEntity<String> deleteGame(String name);
    ResponseEntity<String> deleteCompany(String name);
}
