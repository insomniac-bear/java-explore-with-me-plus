package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.service.UserService;
import ru.practicum.dto.NewUserRequestDto;
import ru.practicum.dto.UserDto;

import java.util.List;

@RestController("/admin/users")
@Slf4j
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers(@RequestParam Long userId) {
        return userService.getUsers(userId);
    }

    @PostMapping
    public NewUserRequestDto createUser(@RequestBody NewUserRequestDto userRequestDto) {
        return userService.addUser(userRequestDto);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long userId) {
        userService.delete(userId);
    }
}
