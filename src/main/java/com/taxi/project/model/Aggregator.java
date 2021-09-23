package com.taxi.project.model;

import javax.persistence.*;
import java.util.List;

// Объект агрегатора
@Entity
@Table(name = "aggregator", uniqueConstraints = {@UniqueConstraint(columnNames = {"aggregator_id", "email"}, name = "aggregator_idx")})
public class Aggregator {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "passenger")
    @OrderBy("first_name DESC")
    private List<Passenger> passengers; // Список пасажиров использующих агрегатор

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    @OrderBy("title DESC")
    private List<Car> cars;  // Список машин у этого агрегатора

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    @OrderBy("first_name DESC")
    private List<Driver> drivers; // Список водителей у этого агрегатора

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transfer")
    @OrderBy("begin_date_arrival DESC")
    private List<Transfer> transfers;
}
