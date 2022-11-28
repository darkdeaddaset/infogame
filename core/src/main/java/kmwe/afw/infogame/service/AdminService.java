package kmwe.afw.infogame.service;

import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.payload.CompanyInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AdminService {
    ResponseEntity<CompanyInfo> getInfoCompany(String name);
    ResponseEntity<String> uploadGame(GameDTO gameDTO, MultipartFile logoGame);
    ResponseEntity<String> uploadCompany(CompanyDTO companyDTO, MultipartFile logoCompany);
    ResponseEntity<String> deleteCompanyService(String name);
    ResponseEntity<String> deleteGameService(String name);
}
