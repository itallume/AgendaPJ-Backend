package com.ifpb.pwebProject.controller;


import com.ifpb.pwebProject.exceptions.*;
import com.ifpb.pwebProject.model.User;
import com.ifpb.pwebProject.model.dto.UserLoginRequestDTO;
import com.ifpb.pwebProject.model.dto.UserResponseDTO;
import com.ifpb.pwebProject.model.dto.UserResquestDTO;
import com.ifpb.pwebProject.controller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

//    @Autowired
//    UserService userService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getUser(@PathVariable Long userId){
//        try{
//           User user =  userService.getUser(userId);
//           return ResponseEntity.
//                   ok(new UserResponseDTO(user.getId(), user.getName(), user.getEmail()));
//        }catch (UserNotFound e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<Object> createUser(@RequestBody UserResquestDTO userResquestDto){
//        try{
//            User creadtedUser = userService.createUser(userResquestDto);
//            return ResponseEntity.status(HttpStatus.CREATED).
//                    body(new UserResponseDTO(creadtedUser.getId(), creadtedUser.getName(),creadtedUser.getEmail()));
//
//        } catch (InvalidUserName | InvalidPassword | InvalidEmail  e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//
//        }catch (EmailAlredyUsed e){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Object> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO){
//        try{
//            User user = userService.userLogin(userLoginRequestDTO);
//            return ResponseEntity.ok(new UserResponseDTO(user.getId(), user.getName(), user.getEmail()));
//        }catch (WrongEmailOrPassword e){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//
//    }
}
