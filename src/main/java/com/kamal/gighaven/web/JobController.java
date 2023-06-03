package com.kamal.gighaven.web;

import com.kamal.gighaven.dtos.BaseResponse;
import com.kamal.gighaven.entities.Job;
import com.kamal.gighaven.entities.User;
import com.kamal.gighaven.services.BidService;
import com.kamal.gighaven.services.FeedbackService;
import com.kamal.gighaven.services.JobService;
import com.kamal.gighaven.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {
    private final BidService bidService;
    private final JobService jobService;
    private final FeedbackService feedbackService;
    private final Helper helper;

    @Value( "${gighaven.job.page_size}" )
    private int jobPageSize;
    @Autowired
    public JobController(BidService bidService, JobService jobService, FeedbackService feedbackService, Helper helper) {
        this.bidService = bidService;
        this.jobService = jobService;
        this.feedbackService = feedbackService;
        this.helper = helper;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getJobs(){
        try {
            return ResponseEntity.ok(new BaseResponse(true, jobService.getAllJobs()));
        } catch (Exception ex){
            return ResponseEntity.ok(new BaseResponse(false, ex.getMessage()));
        }

    }
}
