package com.kamal.gighaven.services;

import com.kamal.gighaven.entities.Bid;
import com.kamal.gighaven.entities.Job;
import com.kamal.gighaven.entities.User;
import com.kamal.gighaven.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    public Optional<Bid> get(Long id) {
        return bidRepository.findById(id);
    }

    public Bid getUsersBidByJob(User user, Job job) {

        List<Bid> bids = bidRepository.findByUserIdAndJobId(user.getId(), job.getId());

        if(bids.isEmpty()){
            return null;
        }

        if(bids.size() > 1){
            throw new IllegalStateException("ERROR: found more more than 1 user's bids for a job.");
        }

        return bids.get(0);
    }

    public List<Bid> findByUser(User user){
        return bidRepository.findByUser(user);
    }

    public List<Bid> findByJob(Job job) {
        return bidRepository.findByJob(job);
    }

    public boolean acceptBid(Bid bid) {
        bid.setAccepted(1);
        save(bid);
        return true;
    }

    public List<Bid> findByUserJobs(User user) {
        return bidRepository.findByUserJobs(user);
    }

    public List<Bid> findByClosedAndUser(int closed, User user) {
        return bidRepository.findByClosedAndUser(closed, user);
    }

}
