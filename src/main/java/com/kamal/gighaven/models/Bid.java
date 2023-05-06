package com.kamal.gighaven.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double price;

    private String deadline;

    @CreatedDate
    private LocalDateTime createdAt;

    @Column(length = 64000)
    private String proposal;

    private int accepted = 0;

    private int closed = 0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    private Job job;
}
