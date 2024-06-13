package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.dto.ReservationsDTO;
import cue.edu.co.velocerentals.domain.models.Reservation;
import cue.edu.co.velocerentals.repository.UserRepository;
import cue.edu.co.velocerentals.repository.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // Mapper class for converting between Reservations and ReservationsDTO objects.
public class ReservationsMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehiclesRepository vehiclesRepository;

    // Method to map from Reservations model to ReservationsDTO DTO.
    public ReservationsDTO mapFromModel(Reservation reservation) {
        return ReservationsDTO.builder()
                .userId(reservation.getUser().getId())
                .vehicleId(reservation.getVehicle().getId())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }

    // Method to map from ReservationsDTO DTO to Reservations model.
    public Reservation mapFromDTO(ReservationsDTO reservationsDTO) {
        return Reservation.builder()
                .user(userRepository.findById(reservationsDTO.userId()).orElseThrow(() -> new IllegalArgumentException("User not found")))
                .vehicle(vehiclesRepository.findById(reservationsDTO.vehicleId()).orElseThrow(() -> new IllegalArgumentException("Vehicle not found")))
                .startDate(reservationsDTO.startDate())
                .endDate(reservationsDTO.endDate())
                .build();
    }

}
