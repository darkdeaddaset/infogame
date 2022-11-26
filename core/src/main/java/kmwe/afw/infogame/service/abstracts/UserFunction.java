package kmwe.afw.infogame.service.abstracts;

import com.github.fge.jsonpatch.JsonPatch;
import kmwe.afw.infogame.user.UserDTO;
import kmwe.afw.infogame.user.UserDTOFull;
import org.springframework.http.ResponseEntity;

public interface UserFunction {
    ResponseEntity<UserDTO> getInfo(String login);
    ResponseEntity<String> updateFullUser(UserDTOFull userDTOFull);
    ResponseEntity<String> partialUpdateUser(JsonPatch jsonPatch, String login);
    ResponseEntity<String> deleteUser(String login);
}
