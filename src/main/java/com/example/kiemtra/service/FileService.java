package com.example.kiemtra.service;

import com.example.kiemtra.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    List<File> createFile(MultipartFile[] files);
}
