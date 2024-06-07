package cue.edu.co.velocerentals.mapping.mappers;

import cue.edu.co.velocerentals.mapping.DTO.ReservationsDTo;
import cue.edu.co.velocerentals.models.Reservation;

// Mapper class for converting between Reservations and ReservationsDTo objects.
public class ReservationsMapper {

    // Method to map from Reservations model to ReservationsDTo DTO.
    public static ReservationsDTo mapFromModel(Reservation reservations) {
        return new ReservationsDTo(reservations.getId(), reservations.getUser(), reservations.getVehicle(),
                reservations.getStartDate(), reservations.getEndDate(), reservations.getTotalCost());
    }

    // Method to map from ReservationsDTo DTO to Reservations model.
    public static Reservation mapFromDTO(ReservationsDTo reservationsDTo) {
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
