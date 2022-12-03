package kmwe.afw.infogame.controller;

import com.github.fge.jsonpatch.JsonPatch;
import kmwe.afw.infogame.company.CompanyDTO;
import kmwe.afw.infogame.company.CompanyDTOFull;
import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.service.UserService;
import kmwe.afw.infogame.strategy.StrategyDTO;
import kmwe.afw.infogame.user.UserDTOFull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/guide/{name}")
    public ResponseEntity<StrategyDTO> getStrategy(@PathVariable("name") String name) {
        return userService.getStrategy(name);
    }

    @GetMapping("/guide-all/{gameName}")
    public List<StrategyDTO> getAllByGame(@PathVariable("gameName") String gameName) {
        return userService.getStrategySearchForGame(gameName);
    }

    @PostMapping("/guide")
    public ResponseEntity<String> createStrategy(@RequestBody StrategyDTO strategyDTO) {
        return userService.createStrategy(strategyDTO);
    }

    @PutMapping("/me")
    public ResponseEntity<String> updateUser(@RequestBody UserDTOFull userDTOFull) {
        return userService.updateFullUser(userDTOFull);
    }

    @PatchMapping("/me")
    public ResponseEntity<String> partialUser(JsonPatch jsonPatch, String login) {
        return userService.partialUpdateUser(jsonPatch, login);
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteUser(String login) {
        return userService.deleteUser(login);
    }
}
