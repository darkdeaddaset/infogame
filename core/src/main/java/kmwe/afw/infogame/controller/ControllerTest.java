package kmwe.afw.infogame.controller;

import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.payload.CompanyInfo;
import kmwe.afw.infogame.service.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class ControllerTest {
    private final AdminService adminService;

    @GetMapping("/t")
    public String test() {
        return "test";
    }

    @PostMapping("/game")
    public ResponseEntity<String> uploadGameInfo(@ModelAttribute GameDTO gameDTO, @RequestParam MultipartFile logoGame) {
        return adminService.uploadGame(gameDTO, logoGame);
    }

    @PostMapping("/company")
    public ResponseEntity<String> uploadCompanyInfo(@ModelAttribute CompanyDTO companyDTO, @RequestParam MultipartFile logoCompany) {
        return adminService.uploadCompany(companyDTO, logoCompany);
    }

    @GetMapping("/company/{name}")
    public ResponseEntity<CompanyInfo> getLogoCompany(@PathVariable("name") String name) {
        return adminService.getInfoCompany(name);
    }

    @DeleteMapping("company/{name}")
    public ResponseEntity<String> deleteCompany(@PathVariable("name") String name) {
        return adminService.deleteCompanyService(name);
    }

    @DeleteMapping("game/{name}")
    public ResponseEntity<String> deleteGame(@PathVariable("name") String name) {
        return adminService.deleteGameService(name);
    }
}
