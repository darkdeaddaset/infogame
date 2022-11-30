package kmwe.afw.infogame.service;

import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTOFull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {
    ResponseEntity<String> uploadGame(GameDTOFull gameDTOFull, MultipartFile logoGame);
    ResponseEntity<String> uploadCompany(CompanyDTOFull companyDTOFull, MultipartFile logoCompany);
    ResponseEntity<String> refreshCompany(String name, CompanyDTOFull companyDTOFull);
    ResponseEntity<String> refreshGame(String name, GameDTOFull gameDTOFull);
    ResponseEntity<String> deleteCompanyService(String name);
    ResponseEntity<String> deleteGameService(String name);
}
