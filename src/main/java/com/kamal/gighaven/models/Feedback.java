package com.kamal.gighaven.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bid_id", nullable = true)
    private Bid bid;

    private Integer clientRate;

    private String clientFeedback;

    private Integer contractorRate;

    private String contractorFeedback;
}
