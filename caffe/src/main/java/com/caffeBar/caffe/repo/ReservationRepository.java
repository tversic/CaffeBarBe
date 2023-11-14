package com.caffeBar.caffe.repo;

import com.caffeBar.caffe.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAll();
    List<Reservation> findAllByApprovedFalse();
    List<Reservation> findAllByApprovedTrue();
    Optional<Reservation> findByReservationDateAndApprovedTrue(String formattedDate);
}
