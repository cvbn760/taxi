package com.taxi.project.model;

import com.taxi.project.model.type.ClassCar;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "car", uniqueConstraints = {@UniqueConstraint(columnNames = {"car_id", "number_car", "vinNumber"}, name = "car_idx")})
public class Car {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id", insertable = false, updatable = false)
    private Driver driver;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "capacity", nullable = false)
    private int capacity; // Вместимость пассажиров

    @Column(name = "maxBaggageWeight", nullable = false)
    private float maxBaggageWeight; // Максимальная масса багажа

    @Column(name = "mileage", nullable = false)
    private float mileage; // Пробег

    @Column(name = "dateOfRelease", nullable = false)
    private LocalDateTime dateOfRelease; // Дата производства

    @Column(name = "number_car", nullable = false)
    private String number; // Номер машины

    @Column(name = "vinNumber", nullable = false)
    private String vinNumber;  // VIN номер

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "type_auto", joinColumns = @JoinColumn(name = "type_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"type_id", "auto"}, name = "type_auto_idx")})
    @Column(name = "auto")
    @ElementCollection(fetch = FetchType.EAGER)
    private ClassCar classcar; // Класс авто
}
