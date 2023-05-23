package com.kamal.gighaven.services;

import com.kamal.gighaven.entities.Profile;
import com.kamal.gighaven.entities.User;
import com.kamal.gighaven.exceptions.NotFoundException;
import com.kamal.gighaven.repositories.ProfileRepository;
import com.kamal.gighaven.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileRepository = profileRepository;
    }

    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Profile getProfileById(Long id){
        return profileRepository.findById(id).
                orElseThrow(() -> new NotFoundException(String.format("Profile with id %d not found", id)));
    }

}
