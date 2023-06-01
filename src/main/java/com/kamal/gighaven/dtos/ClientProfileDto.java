package com.kamal.gighaven.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientProfileDto {
    private ProfileDto profileDto;
    private int totalJobs;
    private int hiredJobs;
    private List<JobHistory> jobHistory;
}
