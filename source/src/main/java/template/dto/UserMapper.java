package template.dto;

import org.mapstruct.Mapper;
import template.model.User;
import java.util.List;

@Mapper
public interface UserMapper {
    UserDto toUserDto(User user);
    List<UserDto> usersDto(List<User> users);
    User toUser(UserDto userDto);
}
