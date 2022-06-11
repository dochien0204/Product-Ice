package com.example.kiemtra.controller;

import com.example.kiemtra.base.VsResponseUtil;
import com.example.kiemtra.dto.UserDTO;
import com.example.kiemtra.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Lấy ra tất cả user", response = ResponseEntity.class)
    public ResponseEntity<?> getAllUser()
    {
        return VsResponseUtil.ok(userService.getAllUser());
    }

    @PostMapping("/add-user")
    @ApiOperation(value = "Thêm user ", response = ResponseEntity.class)
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UserDTO userDTO)
    {
        userService.createNewUserAccount(userDTO);
        return ResponseEntity.status(200).body("Tạo User mới thành công");
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Lấy ra thông tin user theo userId")
    public ResponseEntity<?> getUserById(@PathVariable(name = "userId") Long userId)
    {
        return VsResponseUtil.ok(userService.findUserById(userId));
    }

    @PatchMapping("/update/{userId}")
    @ApiOperation(value = "Cập nhập thông tin người dùng", response = ResponseEntity.class)
    public ResponseEntity<?> updateUserById(@PathVariable(name = "userId") Long userId,@RequestBody UserDTO userDTO)
    {
        userService.updateUserById(userId,userDTO);
        return ResponseEntity.status(200).body("Update thành công");
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ApiOperation(value = "Xóa User theo Id", response = ResponseEntity.class)
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "userId") Long userId)
    {
        userService.deleteUserById(userId);
        return ResponseEntity.status(200).body("Xóa thành công user có id " + userId);
    }

    @PostMapping("/upload-avatar/{userId}")
    @ApiOperation(value = "Cập nhật ảnh đại diện")
    public ResponseEntity<?> uploadAvatarUserById(@PathVariable(name = "userId") Long userId,@RequestParam(name = "file") MultipartFile multipartFile)
    {
        userService.uploadAvatar(userId,multipartFile);
        return ResponseEntity.status(200).body("Upload avatar thành công");
    }
}
