package kmwe.afw.infogame.mapper;

import kmwe.afw.infogame.model.User;
import kmwe.afw.infogame.user.UserDTO;
import kmwe.afw.infogame.user.UserDTOFull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO getFromModel(User user);
    User getFromDTO(UserDTO userDTO);

    void updateFull(UserDTOFull userDTOFull, @MappingTarget User user);

    UserDTOFull getFromModelForFull(User user);
    User getFromDTOForFull(UserDTOFull userDTOFull);
}
