package kmwe.afw.infogame.controller;

import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTOFull;
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
    public ResponseEntity<String> uploadGameInfo(@ModelAttribute GameDTOFull gameDTOFull, @RequestParam MultipartFile logoGame) {
        return adminService.uploadGame(gameDTOFull, logoGame);
    }

    @PostMapping("/company")
    public ResponseEntity<String> uploadCompanyInfo(@ModelAttribute CompanyDTOFull companyDTOFull, @RequestParam MultipartFile logoCompany) {
        return adminService.uploadCompany(companyDTOFull, logoCompany);
    }

    @PutMapping("/game/{name}")
    public ResponseEntity<String> refreshGame(@PathVariable("name") String name, @RequestBody GameDTOFull gameDTOFull) {
        return adminService.refreshGame(name, gameDTOFull);
    }

    @PutMapping("/company/{name}")
    public ResponseEntity<String> refreshCompany(@PathVariable("name") String name, @RequestBody CompanyDTOFull companyDTOFull) {
        return adminService.refreshCompany(name, companyDTOFull);
    }

    @DeleteMapping("game/{name}")
    public ResponseEntity<String> deleteGame(@PathVariable("name") String name) {
        return adminService.deleteGameService(name);
    }

    @DeleteMapping("company/{name}")
    public ResponseEntity<String> deleteCompany(@PathVariable("name") String name) {
        return adminService.deleteCompanyService(name);
    }
}
