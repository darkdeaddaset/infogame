package kmwe.afw.infogame.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StrategyDTO {
    private String name;
    private long authorId;
    private long gameId;
    private String description;
}
