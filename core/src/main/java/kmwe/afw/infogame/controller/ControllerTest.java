package kmwe.afw.infogame.controller;

import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.model.Company;
import kmwe.afw.infogame.repository.CompanyRepository;
import kmwe.afw.infogame.service.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class ControllerTest {
    private final AdminService adminService;
    private final CompanyRepository companyRepository;

    @PostMapping("/test")
    public ResponseEntity<String> saveTest(@RequestBody Company company) {
        companyRepository.save(company);

        return new ResponseEntity<>("Сохранено", HttpStatus.OK);
    }

    @GetMapping("/test/{name}")
    public ResponseEntity<Company> getTest(@PathVariable("name") String name) {
        Company company = companyRepository.getCompaniesByName(name)
                .orElseThrow();

        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/t")
    public String test() {
        return "test";
    }

    /*@PostMapping("/game")
    public ResponseEntity<String> uploadGameInfo(@ModelAttribute GameDTO gameDTO, @RequestParam MultipartFile logoGame) {
        return adminService.uploadGame(gameDTO, logoGame);
    }*/

    @PostMapping("/company")
    public ResponseEntity<String> uploadCompanyInfo(@ModelAttribute CompanyDTO companyDTO, @RequestParam MultipartFile logoCompany) {
        return adminService.uploadCompany(companyDTO, logoCompany);
    }

    /*@GetMapping("/company/{name}")
    public ResponseEntity<CompanyInfo> getLogoCompany(@PathVariable("name") String name) {
        return adminService.getInfoCompany(name);
    }*/


}
