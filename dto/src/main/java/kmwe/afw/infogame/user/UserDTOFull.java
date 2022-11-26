package kmwe.afw.infogame.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTOFull {
    private String login;
    private String email;
    private String password;
}
