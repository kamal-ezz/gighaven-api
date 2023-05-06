package com.kamal.gighaven.repositories;

import com.kamal.gighaven.models.Bid;
import com.kamal.gighaven.models.Feedback;
import com.kamal.gighaven.models.Job;
import com.kamal.gighaven.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByBid(Bid bid);

    @Query("SELECT f"
            + " FROM Feedback f "
            + " JOIN f.bid b "
            + " JOIN b.job j "
            + " JOIN j.author u"
            + " WHERE u = :user ")
    List<Feedback> findByClient(@Param("user") User user);

    @Query("SELECT f"
            + " FROM Feedback f "
            + " JOIN f.bid b "
            + " JOIN b.job j "
            + " WHERE j = :job ")
    List<Feedback> findByJob(@Param("job") Job job);

    //List<Feedback> findByBid(List<Bid> bids);

}
