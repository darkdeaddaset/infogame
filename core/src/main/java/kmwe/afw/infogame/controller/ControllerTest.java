package kmwe.afw.infogame.controller;

import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.service.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class ControllerTest {
    private final AdminService adminService;
    //private final UserService userService;

    @GetMapping("/t")
    public String test() {
        return "test";
    }

    @PostMapping("/game")
    public ResponseEntity<String> uploadGameInfo(@RequestBody GameDTOFull gameDTOFull) {
        return adminService.uploadGame(gameDTOFull);
    }

    @PostMapping("/company")
    public ResponseEntity<String> uploadCompanyInfo(@ModelAttribute CompanyDTOFull companyDTOFull) {
        return adminService.uploadCompany(companyDTOFull);
    }
}
