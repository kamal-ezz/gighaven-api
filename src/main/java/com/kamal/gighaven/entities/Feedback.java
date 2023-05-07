package com.kamal.gighaven.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
