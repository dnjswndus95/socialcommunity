package com.mycommunity.socialcommunity.application.service;

import com.mycommunity.socialcommunity.application.dto.UserDto;
import com.mycommunity.socialcommunity.repository.UserRepository;
import com.mycommunity.socialcommunity.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

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

   @Transactional(readOnly = true)
   public Map<String, String> validateHandling(Errors errors){
      Map<String, String> validatorResult = new HashMap<>();

      for(FieldError error : errors.getFieldErrors()){
         String validKeyName = String.format("valid_%s", error.getField());
         validatorResult.put(validKeyName, error.getDefaultMessage());
      }
      return validatorResult;
   }

   @Transactional
   public void modify(UserDto.Request dto){
      User user = userRepository.findById(dto.toEntity().getId()).orElseThrow( () ->
              new IllegalArgumentException("해당 회원은 존재하지 않습니다."));

      String encPassword = encoder.encode(dto.getPassword());
      user.update(dto.getNickname(), encPassword);
   }


}
