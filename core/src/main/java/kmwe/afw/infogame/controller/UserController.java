package kmwe.afw.infogame.controller;

import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.payload.CompanyInfo;
import kmwe.afw.infogame.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/company/{name}")
    public ResponseEntity<CompanyInfo> getCompany(@PathVariable("name") String name) {
        return userService.getCompany(name);
    }

    @GetMapping("/game/{name}")
    public ResponseEntity<GameDTOFull> getGame(@PathVariable("name") String name) {
        return userService.getGame(name);
    }
}
