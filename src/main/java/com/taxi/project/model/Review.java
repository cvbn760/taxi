package com.taxi.project.model;

import javax.persistence.*;
import java.time.LocalDateTime;

// Объект отзыва
@Entity
@Table(name = "review", uniqueConstraints = {@UniqueConstraint(columnNames = {"review_id"}, name = "review_idx")})
public class Review {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private long id;

    @Column(name = "review")
    private String review;

    @Column(name = "reviewDateTime")
    private LocalDateTime reviewDateTime;
}
