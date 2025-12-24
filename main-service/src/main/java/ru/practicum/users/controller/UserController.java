package ru.practicum.users.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.users.UserService;
import ru.practicum.users.dto.NewUserRequestDto;
import ru.practicum.users.dto.UserDto;

import java.util.List;

@AllArgsConstructor
@RestController("/admin/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers(@RequestParam Long userId) {
        return userService.getUsers(userId);
    }

    @PostMapping
    public NewUserRequestDto createUser(@RequestBody NewUserRequestDto newUserRequestDto) {
        return userService.addUser(newUserRequestDto);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long userId) {
        return userService.delete(userId);
    }
}
