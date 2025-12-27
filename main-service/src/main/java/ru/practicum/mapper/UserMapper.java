package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.NewUserRequestDto;
import ru.practicum.dto.UserDto;
import ru.practicum.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapNewUserRequestDtoToUser(NewUserRequestDto newUserRequestDto);

    NewUserRequestDto mapUserToNewUserRequestDto(User user);

    UserDto mapUserToUserDto(User user);

}
