package com.kamal.gighaven.web;

import com.kamal.gighaven.dtos.BaseResponse;
import com.kamal.gighaven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getProfile(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(new BaseResponse(true, userService.getProfileById(id)));
        }catch (Exception e){
            return ResponseEntity.ok(new BaseResponse(false, e.getMessage()));
        }
    }
}
