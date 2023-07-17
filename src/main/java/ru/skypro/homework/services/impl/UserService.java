package ru.skypro.homework.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserUpdateDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repositories.UserRepository;
import ru.skypro.homework.utils.MappingUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MappingUtils mappingUtils;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, MappingUtils mappingUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mappingUtils = mappingUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public void updatePassword(String oldPass, String newPass) {
        User user = userRepository.getByPassword(oldPass);
        user.setPassword(newPass);
    }

    public UserDto getUserDto() {
        return mappingUtils.mapToUserDto(AuthServiceImpl.getAuthUser());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserUpdateDto updateUser(UserUpdateDto updateDto) {
        return mappingUtils.mapToUserUpdateDto(mappingUtils.mapFromUpdateDto(updateDto));
    }

    public void updateAvatar(MultipartFile avatar) {
        File convertFile = new File(AuthServiceImpl.getAuthUser().getAvatarReference() + avatar.getOriginalFilename());
        try (FileOutputStream stream = new FileOutputStream(convertFile)) {
            if (convertFile.createNewFile()) {
                stream.write(avatar.getBytes());
            }
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void createUser(RegisterReq registerReq, Role role) {
        User user = new User();
        user.setUsername(registerReq.getUsername());
        user.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        user.setFirstName(registerReq.getFirstName());
        user.setLastName(registerReq.getLastName());
        user.setPhone(registerReq.getPhone());
        user.setRole(role);
        userRepository.saveAndFlush(user);
    }
}