package kmwe.afw.infogame.service.abstracts;

import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.game.GameDTOFull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AdminFunction extends UserFunction {
    //ResponseEntity<CompanyInfo> getCompany(String name);
    ResponseEntity<String> uploadGameInfo(GameDTOFull gameDTOFull, MultipartFile logoGame);
    ResponseEntity<String> uploadCompanyInfo(CompanyDTO companyDTO, MultipartFile logoCompany);
    ResponseEntity<String> deleteGame(String name);
    ResponseEntity<String> deleteCompany(String name);
}
