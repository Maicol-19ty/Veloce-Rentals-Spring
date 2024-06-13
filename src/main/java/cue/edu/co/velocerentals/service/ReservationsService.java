package cue.edu.co.velocerentals.service;

import cue.edu.co.velocerentals.domain.models.Reservation;
import cue.edu.co.velocerentals.mapping.dto.ReservationsDTO;
import cue.edu.co.velocerentals.mapping.mappers.ReservationsMapper;
import cue.edu.co.velocerentals.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationsService {

    @Autowired
    private ReservationsRepository reservationsRepository;

    public ReservationsDTO createReservation(ReservationsDTO reservationsDTO) {
        Reservation reservation = ReservationsMapper.mapFromDTO(reservationsDTO);
        reservation = reservationsRepository.save(reservation);
        return ReservationsMapper.mapFromModel(reservation);
    }

    public ReservationsDTO getReservationById(Integer id) {
        Optional<Reservation> reservation = reservationsRepository.findById(id);
        return reservation.map(ReservationsMapper::mapFromModel).orElse(null);
    }

    public List<ReservationsDTO> getAllReservations() {
        return reservationsRepository.findAll().stream()
                .map(ReservationsMapper::mapFromModel)
                .collect(Collectors.toList());
    }

    public ReservationsDTO updateReservation(Integer id, ReservationsDTO reservationsDTO) {
        Optional<Reservation> reservationOptional = reservationsRepository.findById((id));
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.setUser(reservationsDTO.user());
            reservation.setVehicle(reservationsDTO.vehicle());
            reservation.setStartDate(reservationsDTO.startDate());
            reservation.setEndDate(reservationsDTO.endDate());
            reservation.setTotalCost(reservationsDTO.totalCost());
            reservation = reservationsRepository.save(reservation);
            return ReservationsMapper.mapFromModel(reservation);
        } else {
            return null;
        }
    }

    public void deleteReservation(Integer id) {
        reservationsRepository.deleteById(id);
    }

}
