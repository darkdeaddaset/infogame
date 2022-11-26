package kmwe.afw.infogame.service.abstracts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import kmwe.afw.infogame.mapper.UserMapper;
import kmwe.afw.infogame.repository.UserRepository;
import kmwe.afw.infogame.user.UserDTO;
import kmwe.afw.infogame.user.UserDTOFull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractUser implements UserFunction{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ObjectMapper objectMapper;

    @Override
    public ResponseEntity<UserDTO> getInfo(String login) {
        return userRepository.getUserByLogin(login)
                .map(userMapper::getFromModel)
                .map(getInfo -> new ResponseEntity<>(getInfo, HttpStatus.OK))
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
    }

    @Override
    public ResponseEntity<String> updateFullUser(UserDTOFull userDTOFull) {
        return userRepository.getUserByLogin(userDTOFull.getLogin())
                .map(updateFull -> {
                    if (userDTOFull.getLogin().isEmpty() || userDTOFull.getLogin().isBlank()
                            || userDTOFull.getPassword().isEmpty() || userDTOFull.getPassword().isBlank()
                            || userDTOFull.getEmail().isEmpty() || userDTOFull.getEmail().isBlank()) {
                        return new ResponseEntity<>("Невозможно обновить данные", HttpStatus.BAD_REQUEST);
                    } else {
                        userMapper.updateFull(userDTOFull, updateFull);
                        userRepository.save(updateFull);
                        return new ResponseEntity<>("Данные обновлены", HttpStatus.OK);
                    }
                }).orElseThrow(() -> new EntityNotFoundException("Не найден пользователь для обновления"));
    }

    @Override
    public ResponseEntity<String> partialUpdateUser(JsonPatch jsonPatch, String login) {
        UserDTOFull userDTOFull = userRepository.getUserByLogin(login)
                .map(userMapper::getFromModelForFull)
                .orElseThrow(() -> new EntityNotFoundException("Невозможно обновить выборочные данные пользователя"));

        UserDTOFull userDTOFull1;
        try {
            userDTOFull1 = applyPatchUser(jsonPatch, userDTOFull);
        } catch (JsonPatchException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return updateFullUser(userDTOFull1);
    }

    private UserDTOFull applyPatchUser(JsonPatch jsonPatch, UserDTOFull userDTOFull) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(userDTOFull, JsonNode.class));
        return objectMapper.treeToValue(patched, UserDTOFull.class);
    }

    @Override
    public ResponseEntity<String> deleteUser(String login) {
        return userRepository.getUserByLogin(login)
                .map(deleteUser -> {
                    userRepository.delete(deleteUser);
                    return new ResponseEntity<>("Пользователь удален", HttpStatus.OK);})
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден, потому не может быть удален"));
    }
}
