package com.kamal.gighaven.web;

import com.kamal.gighaven.dtos.BaseResponse;
import com.kamal.gighaven.dtos.ProfileDto;
import com.kamal.gighaven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
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
}
