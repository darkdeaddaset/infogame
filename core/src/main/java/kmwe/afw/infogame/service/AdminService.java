package kmwe.afw.infogame.service;

import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.game.GameDTOFull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {
    ResponseEntity<String> uploadGame(GameDTOFull gameDTOFull, MultipartFile logoGame);
    ResponseEntity<String> uploadCompany(CompanyDTO companyDTO, MultipartFile logoCompany);
    ResponseEntity<String> deleteCompanyService(String name);
    ResponseEntity<String> deleteGameService(String name);
}
