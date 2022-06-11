package com.example.kiemtra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorRespone {
    private Integer status;
    private String message;

}
