package kmwe.afw.infogame.controller;

import kmwe.afw.infogame.company.CompanyDTOFull;
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
    public ResponseEntity<String> uploadCompanyInfo(@RequestParam String name, @RequestParam MultipartFile logo) {
        return adminService.uploadCompany(name, logo);
    }

    @GetMapping("/company/{name}")
    public ResponseEntity<CompanyInfo> getLogoCompany(@PathVariable("name") String name, HttpServletRequest request) {
        return adminService.getInfoCompany(name, request);
    }
}
