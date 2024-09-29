package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
