package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.NewUserRequestDto;
import ru.practicum.dto.UserDto;
import ru.practicum.mapper.UserMapper;
import ru.practicum.model.User;
import ru.practicum.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getUsers(List<Long> ids, Pageable pageable) {
        if (ids == null || ids.isEmpty()) {
            return userRepository.findAll()
                    .stream()
                    .map(userMapper::mapUserToUserDto)
                    .collect(Collectors.toList());
        }
        return userRepository.findAllByIdIn(ids, pageable)
                .stream()
                .map(userMapper::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NewUserRequestDto addUser(NewUserRequestDto newUserRequestDto) {
        User user = userMapper.mapNewUserRequestDtoToUser(newUserRequestDto);
        return userMapper.mapUserToNewUserRequestDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
