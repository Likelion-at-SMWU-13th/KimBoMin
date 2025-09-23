package com.example.demo.service;

import com.example.demo.dto.JoinDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bcryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO) {

        // DB에 같은 username이 존재하는지 확인
        if(userRepository.existsByUsername(joinDTO.getUsername())){
            return;
        }

        // joinDTO를 User 엔티티로 변경
        User user = new User();
        user.setUsername(joinDTO.getUsername());
        user.setPassword(bcryptPasswordEncoder.encode(joinDTO.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);

    }
}