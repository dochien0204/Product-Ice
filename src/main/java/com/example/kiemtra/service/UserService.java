package com.example.kiemtra.service;

import com.example.kiemtra.dto.UserDTO;
import com.example.kiemtra.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User findUserById(Long userId);

    void createNewUserAccount(UserDTO userDTO);

    void updateUserById(Long userId, UserDTO userDTO);

    void deleteUserById(Long userId);
    void uploadAvatar(Long id, MultipartFile multipartFile);
}
