package com.example.kiemtra.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.kiemtra.entity.File;
import com.example.kiemtra.repostitory.FileRepository;
import com.example.kiemtra.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private Cloudinary cloudinary;
    @Override
    public List<File> createFile(MultipartFile[] files) {
        List<File> fileList = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                File fileDB = new File();
                Map<?, ?> map = null;
                map = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                fileDB.setImageUrl(map.get("secure_url").toString());
                fileDB.setImagePublic(map.get("public_id").toString());
                fileList.add(fileDB);
            }
            }catch (IOException e) {
                e.printStackTrace();
            }
            return fileRepository.saveAll(fileList);
    }
}
