package kmwe.afw.infogame.service.abstracts;

import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.payload.CompanyInfo;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface AdminFunction extends UserFunction {
    ResponseEntity<CompanyInfo> getCompany(String name, HttpServletRequest request);
    ResponseEntity<String> uploadGameInfo(GameDTOFull gameDTOFull);
    ResponseEntity<String> uploadCompanyInfo(String name, MultipartFile logo);
    ResponseEntity<String> deleteGame(String name);
    ResponseEntity<String> deleteCompany(String name);
}
