package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.dto.ReservationsDTO;
import cue.edu.co.velocerentals.domain.models.Reservation;

// Mapper class for converting between Reservations and ReservationsDTO objects.
public class ReservationsMapper {

    // Method to map from Reservations model to ReservationsDTO DTO.
    public static ReservationsDTO mapFromModel(Reservation reservations) {
        return new ReservationsDTO(reservations.getUser(), reservations.getVehicle(),
                reservations.getStartDate(), reservations.getEndDate(), reservations.getTotalCost());
    }

    // Method to map from ReservationsDTO DTO to Reservations model.
    public static Reservation mapFromDTO(ReservationsDTO reservationsDTO) {
        return Reservation.builder()
                .user(reservationsDTO.user())
                .vehicle(reservationsDTO.vehicle())
                .startDate(reservationsDTO.startDate())
                .endDate(reservationsDTO.endDate())
                .totalCost(reservationsDTO.totalCost())
                .build();
    }

}
