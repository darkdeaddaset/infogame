package kmwe.afw.infogame.service;

import kmwe.afw.infogame.user.UserDTO;
import kmwe.afw.infogame.user.UserDTOFull;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> singIn(UserDTO userDTO);
    ResponseEntity<String> signUp(UserDTOFull userDTOFull);
}
