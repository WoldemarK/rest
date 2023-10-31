package com.example.restapi.service;

import com.example.restapi.model.User;
import com.example.restapi.repository.apiRepository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryImpl userRepository;
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }
    public boolean deleteUserById(Integer id) {
        userRepository.deleteById(id);
        return true;
    }
    public User getByIdUsers(Integer id) {
        return userRepository.getId(id);
    }
    public User updateUserById(User update, Integer id) {
        return userRepository.update(update, id);
    }

    public User createNewUser(User create){
        return userRepository.save(create);
    }
}
