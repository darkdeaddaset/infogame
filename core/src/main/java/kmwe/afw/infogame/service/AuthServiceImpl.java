package kmwe.afw.infogame.service;

import kmwe.afw.infogame.mapper.UserMapper;
import kmwe.afw.infogame.repository.UserRepository;
import kmwe.afw.infogame.user.UserDTO;
import kmwe.afw.infogame.user.UserDTOFull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<String> singIn(UserDTO userDTO) {
        return userRepository.getUserByLoginAndPassword(userDTO.getLogin(), userDTO.getPassword())
                .map(result -> new ResponseEntity<>("Вы авторизовались", HttpStatus.OK))
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с такими данными не найден"));
    }

    @Override
    public ResponseEntity<String> signUp(UserDTOFull userDTOFull) {
        return Optional.of(userDTOFull)
                .map(userMapper::getFromDTOForFull)
                .map(saveNewUser -> {
                    userRepository.save(saveNewUser);
                    return new ResponseEntity<>("Вы зарегистрировались", HttpStatus.OK);
                }).orElseThrow(() -> new EntityNotFoundException("Невозможно создать пользователя"));
    }
}
