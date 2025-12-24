package ru.practicum.service;

import ru.practicum.dto.NewUserRequestDto;
import ru.practicum.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers(Long userId);

    NewUserRequestDto addUser(NewUserRequestDto userRequestDto);

    void delete(Long userId);

}
