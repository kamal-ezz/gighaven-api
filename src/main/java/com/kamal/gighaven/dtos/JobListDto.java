package com.kamal.gighaven.dtos;

import com.kamal.gighaven.entities.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobListDto {
    private boolean isMyJobsPage;
    private Page<Job> jobsPage;
}
