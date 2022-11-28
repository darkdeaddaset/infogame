package kmwe.afw.infogame.service;

import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.payload.CompanyInfo;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    ResponseEntity<CompanyInfo> getInfoCompany(String name, HttpServletRequest request);
    ResponseEntity<String> uploadGame(GameDTOFull gameDTOFull);
    ResponseEntity<String> uploadCompany(String name, MultipartFile logo);
}
