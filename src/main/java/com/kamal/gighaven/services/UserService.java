package com.kamal.gighaven.services;

import com.kamal.gighaven.dtos.ProfileDto;
import com.kamal.gighaven.entities.Profile;
import com.kamal.gighaven.entities.User;
import com.kamal.gighaven.exceptions.NotFoundException;
import com.kamal.gighaven.repositories.ProfileRepository;
import com.kamal.gighaven.repositories.UserRepository;
import com.kamal.gighaven.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;
    private final Helper helper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ProfileRepository profileRepository, Helper helper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileRepository = profileRepository;
        this.helper = helper;
    }

    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("User with email %s not found", email)));
    }

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Profile getProfile(Principal principal){
        User user = helper.getCurrentUser(principal);
        return profileRepository.findByUser(user);
    }

    public Profile editProfile(Long id, ProfileDto profileDto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Profile with id %d not found", id)));

        profile.setSummary(profileDto.getSummary());
        profile.setHeadline(profileDto.getHeadline());
        return profile;
    }
}
