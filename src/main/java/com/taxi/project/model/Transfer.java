package com.taxi.project.model;

import java.time.LocalDateTime;

// Объект поездки
public class Transfer {
    private long id; 
    private Car car;
    private Passenger passenger;
    private LocalDateTime begin_date_arrival;     // Начало диапазона времени подачи автомобиля
    private LocalDateTime end_date_arrival;       // Окончание диапазона времени подачи автомобиля
    private LocalDateTime begin_date_departure;   // Начало диапазона времени отправления
    private LocalDateTime end_date_departure;     // Окончание диапазона времени отправления
    private LocalDateTime begin_date_reservation; // Начало дипазона времени бронирования
    private LocalDateTime end_date_reservation;   // Окончание дипазона времени бронирования
    private float price;
    private float distance;
}
