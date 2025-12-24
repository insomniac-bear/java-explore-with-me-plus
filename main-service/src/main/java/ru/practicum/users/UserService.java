package ru.practicum.users;

import org.springframework.stereotype.Service;
import ru.practicum.users.dto.NewUserRequestDto;
import ru.practicum.users.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers(Long userId);

    NewUserRequestDto addUser(NewUserRequestDto newUserRequestDto);

    void delete(Long userId);

}
