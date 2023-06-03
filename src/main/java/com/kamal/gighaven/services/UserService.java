package com.kamal.gighaven.services;

import com.kamal.gighaven.dtos.ClientProfileDto;
import com.kamal.gighaven.dtos.JobHistory;
import com.kamal.gighaven.dtos.ProfileDto;
import com.kamal.gighaven.dtos.SignupRequest;
import com.kamal.gighaven.entities.Feedback;
import com.kamal.gighaven.entities.Job;
import com.kamal.gighaven.entities.Profile;
import com.kamal.gighaven.entities.User;
import com.kamal.gighaven.enums.Role;
import com.kamal.gighaven.exceptions.NotFoundException;
import com.kamal.gighaven.repositories.ProfileRepository;
import com.kamal.gighaven.repositories.UserRepository;
import com.kamal.gighaven.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;
    private Helper helper;
    private final JobService jobService;
    private final FeedbackService feedbackService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       ProfileRepository profileRepository, JobService jobService, FeedbackService feedbackService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileRepository = profileRepository;
        this.jobService = jobService;
        this.feedbackService = feedbackService;
    }


    @Lazy
    @Autowired
    public void setHelper(Helper helper){
        this.helper = helper;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("User with email %s not found", email)));
    }

    public User addUser(SignupRequest userDto) {
        User user1 = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .role(Role.valueOf(userDto.getRole()))
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user1);
    }

    public ProfileDto getProfile(Principal principal){
        User user = helper.getCurrentUser(principal);
        return ProfileDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .headline(user.getProfile().getHeadline())
                .summary(user.getProfile().getSummary())
                .build();
    }


    public ProfileDto getProfileByUserId(Long id){
        User user = getUserById(id);
        return ProfileDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .headline(user.getProfile().getHeadline())
                .summary(user.getProfile().getSummary())
                .build();
    }

    public Profile editProfile(Long id, ProfileDto profileDto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Profile with id %d not found", id)));

        profile.setSummary(profileDto.getSummary());
        profile.setHeadline(profileDto.getHeadline());
        return profile;
    }


    public User saveProfile(Principal principal, ProfileDto profileDto){
        User me = helper.getCurrentUser(principal);
        me.setUsername(profileDto.getUsername());
        me.setEmail(profileDto.getEmail());
        me.setPhone(profileDto.getPhone());
        me.getProfile().setHeadline(profileDto.getHeadline());
        me.getProfile().setSummary(profileDto.getSummary());

        return userRepository.save(me);
    }


    public ClientProfileDto getClientProfile(Long userId){
        User user = getUserById(userId);

        ProfileDto profileDto = getProfileByUserId(userId);

        List<Job> clientJobs = jobService.findByAuthor(user);

        List<JobHistory> jobHistory = new ArrayList<>();

        int totalJobs = 0;
        int hiredJobs = jobService.findHiredJobsByAuthor(user).size();

        for(Job j : clientJobs) {
            totalJobs++;
            JobHistory jh = new JobHistory();
            List<Feedback> feedback = feedbackService.findByJob(j);
            jh.setJob(j);
            jh.setFeedback(feedback);
            jobHistory.add(jh);
        }

        return ClientProfileDto.builder()
                .profileDto(profileDto)
                .totalJobs(totalJobs)
                .hiredJobs(hiredJobs)
                .jobHistory(jobHistory)
                .build();
    }
}
