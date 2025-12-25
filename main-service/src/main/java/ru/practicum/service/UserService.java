package ru.practicum.service;

import org.springframework.data.domain.Pageable;
import ru.practicum.dto.NewUserRequestDto;
import ru.practicum.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers(List<Long> ids, Pageable pageable);

    UserDto addUser(NewUserRequestDto userRequestDto);

    void delete(Long userId);

}
