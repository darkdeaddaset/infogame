package kmwe.afw.infogame.controller;

import kmwe.afw.infogame.service.AuthService;
import kmwe.afw.infogame.user.UserDTO;
import kmwe.afw.infogame.user.UserDTOFull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sing-in")
    public ResponseEntity<String> singIn(@RequestBody UserDTO userDTO) {
        return authService.singIn(userDTO);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody UserDTOFull userDTOFull) {
        return authService.signUp(userDTOFull);
    }
}
