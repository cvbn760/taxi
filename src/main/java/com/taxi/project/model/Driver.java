package com.taxi.project.model;

import com.taxi.project.model.type.Role;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

// Класс водителя
@Entity
@Table(name = "driver", uniqueConstraints = {@UniqueConstraint(columnNames = {"driver_id", "phone", "email"}, name = "driver_idx")})
public class Driver extends User {

    @Column(name = "driving_experience", nullable = false)
    private float drivingExperience; // Стаж вождения

    @Column(name = "rating", nullable = false)
    private float rating;            // Рейтинг

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
    @OrderBy("reviewDateTime DESC")
    private List<String> reviewsDriver;    // Отзывы

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "car")
    @Column(name = "car", nullable = false)
    private Car car;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "driver_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"driver_id", "role"}, name = "driver_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 100)
    private Set<Role> roles;
}
