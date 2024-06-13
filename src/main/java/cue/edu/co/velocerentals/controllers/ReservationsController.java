package cue.edu.co.velocerentals.controllers;

import cue.edu.co.velocerentals.mapping.dto.ReservationsDTO;
import cue.edu.co.velocerentals.mapping.dto.VehiclesDTO;
import cue.edu.co.velocerentals.service.ReservationsService;
import cue.edu.co.velocerentals.service.VehiclesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsController {

    @Autowired
    private ReservationsService reservationsService;

    @PostMapping("create")
    public Map<String, String> createReservation(@RequestBody @Valid ReservationsDTO reservationsDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return errors;
        }
        reservationsService.createReservation(reservationsDTO);
        return Map.of("message", "Reservation created successfully");
    }

    @GetMapping("search/{id}")
    public ResponseEntity<ReservationsDTO> getReservationById(@PathVariable Integer id) {
        ReservationsDTO reservation = reservationsService.getReservationById(id);
        return reservation != null ? ResponseEntity.ok(reservation) : ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<ReservationsDTO>> getAllReservations() {
        List<ReservationsDTO> reservations = reservationsService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ReservationsDTO> updateReservation(@PathVariable Integer id, @RequestBody @Valid ReservationsDTO reservationsDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(null);
        }
        ReservationsDTO updatedReservation = reservationsService.updateReservation(id, reservationsDTO);
        return updatedReservation != null ? ResponseEntity.ok(updatedReservation) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        reservationsService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-availability")
    public ResponseEntity<String> checkAvailability(@RequestParam Integer vehicleId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        String availabilityMessage = reservationsService.selectRentalDates(vehicleId, startDate, endDate);
        return ResponseEntity.ok(availabilityMessage);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationsDTO>> getReservationsByUserId(@PathVariable Integer userId) {
        List<ReservationsDTO> reservations = reservationsService.getReservationsByUserId(userId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ReservationsDTO>> getReservationsByDateRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<ReservationsDTO> reservations = reservationsService.getReservationsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reservations);
    }

}
