package com.mycommunity.socialcommunity.application.service;

import com.mycommunity.socialcommunity.application.dto.UserDto;
import com.mycommunity.socialcommunity.controller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

   private final UserRepository userRepository;

   private final BCryptPasswordEncoder encoder;  //비밀번호 암호화 메서드를 가진 클래스


   @Transactional
   public void joinUser(UserDto.Request dto){
      dto.setPassword(encoder.encode(dto.getPassword()));
      userRepository.save(dto.toEntity());
   }


}
