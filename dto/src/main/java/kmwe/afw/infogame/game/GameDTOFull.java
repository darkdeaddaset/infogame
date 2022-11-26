package kmwe.afw.infogame.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameDTOFull {
    private String name;
    private long companyId;
    private long publisherId;
    private byte[] photo;
    private char[] description;
}
