package kmwe.afw.infogame.service;

import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTOFull;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<String> uploadGame(GameDTOFull gameDTOFull);
    ResponseEntity<String> uploadCompany(CompanyDTOFull companyDTOFull);
}
