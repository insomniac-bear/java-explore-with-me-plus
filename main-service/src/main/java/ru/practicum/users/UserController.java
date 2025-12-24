package ru.practicum.users;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController("/admin/users")
public class UserController {
    private final UserService userService;


}
