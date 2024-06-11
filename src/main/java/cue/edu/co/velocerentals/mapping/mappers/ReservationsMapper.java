package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.dto.ReservationsDTO;
import cue.edu.co.velocerentals.domain.models.Reservation;

// Mapper class for converting between Reservations and ReservationsDTo objects.
public class ReservationsMapper {

    // Method to map from Reservations model to ReservationsDTo DTO.
    public static ReservationsDTO mapFromModel(Reservation reservations) {
        return new ReservationsDTO(reservations.getId(), reservations.getUser(), reservations.getVehicle(),
                reservations.getStartDate(), reservations.getEndDate(), reservations.getTotalCost());
    }

    // Method to map from ReservationsDTo DTO to Reservations model.
    public static Reservation mapFromDTO(ReservationsDTO reservationsDTo) {
        return Reservation.builder()
                .id(reservationsDTo.id())
                .user(reservationsDTo.user())
                .vehicle(reservationsDTo.vehicle())
                .startDate(reservationsDTo.startDate())
                .endDate(reservationsDTo.endDate())
                .totalCost(reservationsDTo.totalCost())
                .build();
    }

}
