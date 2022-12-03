package kmwe.afw.infogame.mapper;

import kmwe.afw.infogame.model.Strategy;
import kmwe.afw.infogame.strategy.StrategyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StrategyMapper {
    @Mapping(source = "authorId", target = "author.id")
    @Mapping(source = "gameId", target = "game.id")
    Strategy getFromDTO(StrategyDTO strategyDTO);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "game.id", target = "gameId")
    StrategyDTO getFromModel(Strategy strategy);

}
