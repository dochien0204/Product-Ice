package com.example.kiemtra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String avt;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String firstName;

    private String lastName;

    private String telephone;

    private String address;

    @NotBlank
    @Email
    @Pattern(regexp = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1}$", message = "Địa chỉ email phải bắt đầu bằng 1 ký tự + địa chỉ email là tập hợp của các ký tự a-z, 0-9 và có thể có các ký tự như dấu chấm, dấu gạch dưới + độ dài tối thiểu của email là 5, độ dài tối đa là 32 + tên miền của email có thể là tên miền cấp 1")
    private String email;

}
