package kmwe.afw.infogame.mapper;

import kmwe.afw.infogame.game.GameDTO;
import kmwe.afw.infogame.game.GameDTOFull;
import kmwe.afw.infogame.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "publisher.id", target = "publisherId")
    GameDTO getFromModel(Game game);

    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "publisherId", target = "publisher.id")
    Game getFromDTO(GameDTO gameDTO);

    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "publisherId", target = "publisher.id")
    Game getFromDTOOfFull(GameDTOFull gameDTOFull);

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "publisher.id", target = "publisherId")
    GameDTOFull getFromModelForFull(Game game);

    List<GameDTOFull> getAllGameDTOFull(List<Game> games);

    void refreshFull(GameDTOFull gameDTOFull, @MappingTarget Game game);
}
