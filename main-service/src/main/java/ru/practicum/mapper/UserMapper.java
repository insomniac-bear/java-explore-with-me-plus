package ru.practicum.mapper;

import org.mapstruct.Mapper;
import ru.practicum.dto.NewUserRequestDto;
import ru.practicum.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapUserDtoToUser(NewUserRequestDto newUserRequestDto);

    NewUserRequestDto mapUserToNewUserRequestDto(User user);

}
