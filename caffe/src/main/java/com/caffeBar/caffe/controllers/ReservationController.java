package com.caffeBar.caffe.controllers;

import com.caffeBar.caffe.model.Reservation;
import com.caffeBar.caffe.repo.ReservationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost", "http://localhost:8080"})
@RequestMapping("/reservation/v1")
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    @GetMapping ("/tst")
    ResponseEntity<String> testController(){
        return ResponseEntity.ok().body("test");
    }

    @Transactional
    @PostMapping("/save")
    ResponseEntity<String> saveReservation(@RequestBody Reservation reservation) {
        reservation.setApproved(false);
        // Create a Date object representing the current date and time
        String currentDate = reservation.getReservationDate();

        if (reservationRepository.findByReservationDateAndApprovedTrue(currentDate).isEmpty()) {
            reservationRepository.save(reservation);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/getAll")
    ResponseEntity<List<Reservation>> getAllReservations() {
        if (reservationRepository.findAll().size() > 0)
            return ResponseEntity.ok().body(reservationRepository.findAll());
        else
            return ResponseEntity.notFound().build();

    }

    @GetMapping("/getNotApproved")
    ResponseEntity<List<Reservation>> getAllNotApprovedReservations() {
        if(reservationRepository.findAllByApprovedFalse().size() > 0)
            return ResponseEntity.ok().body(reservationRepository.findAllByApprovedFalse());
        return ResponseEntity.ok().body(new ArrayList<>());
    }

    @Transactional
    @SneakyThrows
    @PostMapping("/approveReservation")
    ResponseEntity<String> approveReservation(@RequestBody String reservationId) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(reservationId);

        // Extract the value of reservationId from the JSON
        Long id = jsonNode.get("reservationId").asLong();

        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            reservation.get().setApproved(true);
            reservationRepository.save(reservation.get());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllApproved")
    ResponseEntity<List<Reservation>> getAllApproved() {
        if (reservationRepository.findAllByApprovedTrue().size() > 0)
            return ResponseEntity.ok().body(reservationRepository.findAllByApprovedTrue());
        return ResponseEntity.ok().body(new ArrayList<>());
    }
}
