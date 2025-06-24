package com.ifpb.pwebProject.controller.service;

import com.ifpb.pwebProject.exceptions.*;
import com.ifpb.pwebProject.model.User;
import com.ifpb.pwebProject.model.dto.UserLoginRequestDTO;
import com.ifpb.pwebProject.model.dto.UserResquestDTO;
import com.ifpb.pwebProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   public User getUser(UUID userId){
       Optional<User> user = userRepository.findById(userId);
       if (user.isEmpty()){
           throw new UserNotFound("usuário não encontrado");
       }
       return user.get();
   }

   public User createUser(UserResquestDTO userResquestDto){

       if (userResquestDto.name().length() < 2){
           throw new InvalidUserName("Nome inválido");
       }

       if (userResquestDto.password().length() < 6){
           throw new InvalidPassword("Senha deve ter 6 ou mais caracteres");
       }

       if (!userResquestDto.email().matches("^(?!\\s*$)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
           throw new InvalidEmail("Formato de email inválido");
       }

       if (userRepository.emailInUse(userResquestDto.email())){
           throw new EmailAlredyUsed("Email já está em uso");
       }

       User user = new User();
       user.setName(userResquestDto.name());
       user.setEmail(userResquestDto.email());
       user.setPassword(userResquestDto.password());
       
       return userRepository.save(user);
   }

   public User userLogin(UserLoginRequestDTO userLoginRequestDTO){
       User user = userRepository.login(userLoginRequestDTO.email(), userLoginRequestDTO.password());

       if (user == null){
           throw new WrongEmailOrPassword("Email ou senha errades");
       }
       return user;
   }
}
