package com.example.kiemtra;

import com.example.kiemtra.entity.Role;
import com.example.kiemtra.entity.User;
import com.example.kiemtra.repostitory.RoleRepository;
import com.example.kiemtra.repostitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class KiemTraApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Bean
    CommandLineRunner init() {
        return args -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (roleRepository.count() == 0) {
                roleRepository.save(new Role("ROLE_ADMIN", null));
                roleRepository.save(new Role("ROLE_USER", null));
            }

            if (userRepository.count() == 0) {
                User account = new User("Admin",
                        passwordEncoder.encode("admin"), Set.copyOf(roleRepository.findAll()));
                userRepository.save(account);
            }
        };
    }


    public static void main(String[] args) {
        SpringApplication.run(KiemTraApplication.class, args);
    }


}
