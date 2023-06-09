package com.kamal.gighaven.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String headline;
    private String summary;

    @OneToOne
    @JsonBackReference
    private User user;

    @OneToMany
    private List<Bid> bids;

    @OneToMany
    private List<Feedback> feedbacks;
}
