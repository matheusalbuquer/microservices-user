package com.ms.user.services;

import com.ms.user.dtos.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.repositories.UserRepository;

import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.List;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save(UserModel userModel){
        return userRepository.save(userModel);
    }

    @Transactional
    public List<UserModel> findAll(){
      return userRepository.findAll();
    }

    @Transactional
    public void delete (UserModel user){
         userRepository.delete(user);
    }

}
