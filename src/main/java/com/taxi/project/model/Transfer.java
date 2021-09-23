package com.taxi.project.model;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

// Объект поездки
@Entity
@Table(name = "transfer", uniqueConstraints = {@UniqueConstraint(columnNames = {"transfer_id"}, name = "transfer_idx")})
public class Transfer {
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "car_id", insertable = false, updatable = false)
    private Car car;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id", referencedColumnName = "passenger_id", insertable = false, updatable = false)
    private Passenger passenger;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id", insertable = false, updatable = false)
    private Driver driver;

    @Column(name = "addressExtraction", nullable = false)
    private String addressExtraction;             // Адрес отправления

    @Column(name = "addressDeparture", nullable = false)
    private String addressDeparture;              // Адрес прибытия

    @Column(name = "begin_date_arrival", nullable = false)
    private LocalDateTime begin_date_arrival;     // Начало диапазона времени подачи автомобиля

    @Column(name = "end_date_arrival", nullable = false)
    private LocalDateTime end_date_arrival;       // Окончание диапазона времени подачи автомобиля

    @Column(name = "begin_date_departure", nullable = false)
    private LocalDateTime begin_date_departure;   // Начало диапазона времени отправления

    @Column(name = "end_date_departure", nullable = false)
    private LocalDateTime end_date_departure;     // Окончание диапазона времени отправления

    @Column(name = "begin_date_reservation", nullable = false)
    private LocalDateTime begin_date_reservation; // Начало дипазона времени бронирования

    @Column(name = "end_date_reservation", nullable = false)
    private LocalDateTime end_date_reservation;   // Окончание дипазона времени бронирования

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
    @OrderBy("reviewDateTime DESC")
    private List<String> reviewsTransfer;    // Отзывы

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "distance", nullable = false)
    private float distance;
}
