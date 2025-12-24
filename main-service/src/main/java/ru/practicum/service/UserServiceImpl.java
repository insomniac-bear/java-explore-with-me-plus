package ru.practicum.service;

import org.springframework.stereotype.Service;
import ru.practicum.dto.NewUserRequestDto;
import ru.practicum.dto.UserDto;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserDto> getUsers(Long userId) {
        return List.of();
    }

    @Override
    public NewUserRequestDto addUser(NewUserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public void delete(Long userId) {

    }
}
