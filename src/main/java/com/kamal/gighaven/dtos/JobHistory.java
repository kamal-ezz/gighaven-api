package com.kamal.gighaven.dtos;

import com.kamal.gighaven.entities.Bid;
import com.kamal.gighaven.entities.Feedback;
import com.kamal.gighaven.entities.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobHistory {
    private Bid bid;
    private List<Feedback> feedback;
    private Job job;
}
