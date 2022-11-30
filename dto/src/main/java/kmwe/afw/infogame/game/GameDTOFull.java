package kmwe.afw.infogame.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.Path;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDTOFull {
    private String name;
    private long companyId;
    private long publisherId;
    private Path path;
    private String description;
}