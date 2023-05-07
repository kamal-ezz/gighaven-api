package com.kamal.gighaven.services;

import com.kamal.gighaven.entities.Bid;
import com.kamal.gighaven.entities.Feedback;
import com.kamal.gighaven.entities.Job;
import com.kamal.gighaven.entities.User;
import com.kamal.gighaven.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Optional<Feedback> get(Long id) {
        return feedbackRepository.findById(id);
    }

    public List<Feedback> findByBid(Bid bid){
        return feedbackRepository.findByBid(bid);
    }

    public List<Feedback> findByClient(User user) {
        return feedbackRepository.findByClient(user);
    }

    public List<Feedback> findByJob(Job job) {
        return feedbackRepository.findByJob(job);
    }


}
