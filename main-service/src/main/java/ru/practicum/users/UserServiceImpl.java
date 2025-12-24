package ru.practicum.users;

import org.springframework.stereotype.Service;
import ru.practicum.users.dto.NewUserRequestDto;
import ru.practicum.users.dto.UserDto;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserDto> getUsers(Long userId) {
        return List.of();
    }

    @Override
    public NewUserRequestDto addUser(NewUserRequestDto newUserRequestDto) {
        return null;
    }

    @Override
    public void delete(Long userId) {

    }
}
