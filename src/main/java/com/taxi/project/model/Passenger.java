package com.taxi.project.model;

import com.taxi.project.model.type.Role;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

// Класс пасажира
@Entity
@Table(name = "passenger", uniqueConstraints = {@UniqueConstraint(columnNames = {"passenger_id", "email", "phone"}, name = "passenger_idx")})
public class Passenger extends User {

    @Column(name = "weight", nullable = false)
    private float weight; // Вес

    @Column(name = "height", nullable = false)
    private float height; // Рост

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transfer")
    @OrderBy("begin_date_departure DESC")
    private List<Transfer> transfers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
    @OrderBy("reviewDateTime DESC")
    private List<Review> reviews;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "passenger_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"passenger_id", "role"}, name = "passenger_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 100)
    private Set<Role> roles;
}
