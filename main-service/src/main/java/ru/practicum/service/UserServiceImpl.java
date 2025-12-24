package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.dto.NewUserRequestDto;
import ru.practicum.dto.UserDto;
import ru.practicum.mapper.UserMapper;
import ru.practicum.repository.UserRepositoiry;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepositoiry userRepositoiry;
    private final UserMapper userMapper;


    @Override
    public List<UserDto> getUsers(Long userId) {

        return userRepositoiry.getAllUsers();
    }

    @Override
    public NewUserRequestDto addUser(NewUserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public void delete(Long userId) {

    }
}
