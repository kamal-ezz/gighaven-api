package com.kamal.gighaven.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private String username;
    private String email;
    private String phone;
    private String headline;
    private String summary;
}
