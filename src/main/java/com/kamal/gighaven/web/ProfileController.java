package com.kamal.gighaven.web;

import com.kamal.gighaven.dtos.BaseResponse;
import com.kamal.gighaven.dtos.ProfileDto;
import com.kamal.gighaven.entities.Job;
import com.kamal.gighaven.entities.User;
import com.kamal.gighaven.services.UserService;
import com.kamal.gighaven.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;
    private final Helper helper;

    @Autowired
    public ProfileController(UserService userService, Helper helper) {
        this.userService = userService;
        this.helper = helper;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getProfile(Principal principal){
        try {
            return ResponseEntity.ok(new BaseResponse(true, userService.getProfile(principal)));
        }catch (Exception e){
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> editProfile(@PathVariable("id") Long id, ProfileDto profileDto){
        try {
            return ResponseEntity.ok(new BaseResponse(true, userService.editProfile(id, profileDto)));
        }catch (Exception e){
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }


    @PostMapping
    public ResponseEntity<BaseResponse> saveProfile(Principal principal, ProfileDto profileDto){
        try {
            return ResponseEntity.ok(new BaseResponse(true, userService.saveProfile(principal, profileDto)));
        }catch (Exception e){
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }


    @GetMapping("/client/{id}")
    public ResponseEntity<BaseResponse> viewClientProfile(@PathVariable("id") Long userId){
        try {
            return ResponseEntity.ok(new BaseResponse(true, userService.getClientProfile(userId)));
        }catch (Exception e){
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }


}
