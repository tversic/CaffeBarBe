package com.caffeBar.caffe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue
    Long reservationId;
    @Column
    String firstName;
    @Column
    String lastName;
    @Column
    String email;
    @Column
    String reservationDate;
    @Column
    boolean approved;
}
