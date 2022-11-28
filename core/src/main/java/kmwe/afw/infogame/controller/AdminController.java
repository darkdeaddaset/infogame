package kmwe.afw.infogame.controller;

import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/game")
    public ResponseEntity<String> uploadGameInfo(@ModelAttribute GameDTO gameDTO, @RequestParam MultipartFile logoGame) {
        return adminService.uploadGame(gameDTO, logoGame);
    }

    @PostMapping("/company")
    public ResponseEntity<String> uploadCompanyInfo(@ModelAttribute CompanyDTO companyDTO, @RequestParam MultipartFile logoCompany) {
        return adminService.uploadCompany(companyDTO, logoCompany);
    }
}
