package com.example.kiemtra.service.Impl;

import com.example.kiemtra.dto.UserDTO;
import com.example.kiemtra.entity.User;
import com.example.kiemtra.exception.DuplicateException;
import com.example.kiemtra.exception.NotFoundException;
import com.example.kiemtra.repostitory.CartRepository;
import com.example.kiemtra.repostitory.RoleRepository;
import com.example.kiemtra.repostitory.UserRepository;
import com.example.kiemtra.service.UserService;
import com.example.kiemtra.util.UploadFile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UploadFile uploadFile;


    @Autowired
    private ModelMapper modelMapper;


    public boolean checkEmailExistsByEmail(String email)
    {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        return user.isPresent();
    }

    public boolean checkEmailExistsByUsername(String username)
    {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        return user.isPresent();
    }

    public boolean checkUserExists(Long userId)
    {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new NotFoundException("Không tìm thấy thông tin user này");
        return user.get();
    }

    @Override
    public void createNewUserAccount(UserDTO userDTO) {
        if(checkEmailExistsByEmail(userDTO.getEmail()))
            throw new DuplicateException("User đã tồn tại với gmail này, vui lòng chọn email khác !");
        else if(checkEmailExistsByUsername(userDTO.getUsername()))
            throw new DuplicateException("User đã tồn tại với username này, vui lòng nhập username khác");
        User user = modelMapper.map(userDTO,User.class);
        user.setRoles(Set.of(roleRepository.findByRoleName("ROLE_USER")));
        userRepository.save(user);
//        Cart cart = new Cart();
//        cart.setCartName(user.getUsername());
//        cart.setUser(userRepository.findByUsername(user.getUsername()));
//        cartRepository.save(cart);
    }

    @Override
    public void updateUserById(Long userId, UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new  NotFoundException("Không tìm thấy user có id " + userId);
        modelMapper.map(userDTO,user.get());
        userRepository.save(user.get());
    }

    @Override
    public void deleteUserById(Long userId) {
        if(checkUserExists(userId))
            userRepository.deleteById(userId);
        else
            throw new NotFoundException("Không tìm thấy user có Id" + userId);
    }

    @Override
    public void uploadAvatar(Long id, MultipartFile multipartFile) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new NotFoundException("Không tìm thấy user có id " + id);
        if(user.get().getAvt() != null) {
            uploadFile.removeFileFromUrl(user.get().getAvt());
        }
        user.get().setAvt(uploadFile.getUrlFromFile(multipartFile));
        userRepository.save(user.get());
    }


}
