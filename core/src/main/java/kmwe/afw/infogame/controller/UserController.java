package kmwe.afw.infogame.controller;

import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/company/{name}")
    public ResponseEntity<CompanyDTOFull> getCompany(@PathVariable("name") String name) {
        return userService.getCompany(name);
    }

    @GetMapping("/game/{name}")
    public ResponseEntity<GameDTOFull> getGame(@PathVariable("name") String name) {
        return userService.getGame(name);
    }

    @GetMapping("/games")
    public List<GameDTO> getAllGame() {
        return userService.getAllGames();
    }

    @GetMapping("/companies")
    public List<CompanyDTO> getAllCompany() {
        return userService.getAllCompanies();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Resource> getImage(@PathVariable("name") String name, HttpServletRequest request) {
        return userService.getImage(name, request);
    }
}
