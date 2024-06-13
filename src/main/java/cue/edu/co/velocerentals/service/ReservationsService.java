package cue.edu.co.velocerentals.service;

import cue.edu.co.velocerentals.domain.models.Reservation;
import cue.edu.co.velocerentals.mapping.dto.ReservationsDTO;
import cue.edu.co.velocerentals.mapping.mappers.ReservationsMapper;
import cue.edu.co.velocerentals.repository.ReservationsRepository;
import cue.edu.co.velocerentals.repository.UserRepository;
import cue.edu.co.velocerentals.repository.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationsService {

    @Autowired
    private ReservationsRepository reservationsRepository;

    @Autowired
    private ReservationsMapper reservationsMapper;

    @Autowired
    private VehiclesRepository vehiclesRepository;

    @Autowired
    private UserRepository userRepository;

    public ReservationsDTO createReservation(ReservationsDTO reservationsDTO) {
        Reservation reservation = reservationsMapper.mapFromDTO(reservationsDTO);
        long days = ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate());
        BigDecimal totalCost = reservation.getVehicle().getPricePerDay().multiply(BigDecimal.valueOf(days));
        reservation.setTotalCost(totalCost);
        reservation = reservationsRepository.save(reservation);
        return reservationsMapper.mapFromModel(reservation);
    }

    public ReservationsDTO getReservationById(Integer id) {
        Optional<Reservation> reservation = reservationsRepository.findById(id);
        return reservation.map(reservationsMapper::mapFromModel).orElse(null);
    }

    public List<ReservationsDTO> getAllReservations() {
        return reservationsRepository.findAll().stream()
                .map(reservationsMapper::mapFromModel)
                .collect(Collectors.toList());
    }

    public ReservationsDTO updateReservation(Integer id, ReservationsDTO reservationsDTO) {
        Optional<Reservation> reservationOptional = reservationsRepository.findById(id);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.setUser(userRepository.findById(reservationsDTO.userId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found")));
            reservation.setVehicle(vehiclesRepository.findById(reservationsDTO.vehicleId())
                    .orElseThrow(() -> new IllegalArgumentException("Vehicle not found")));
            reservation.setStartDate(reservationsDTO.startDate());
            reservation.setEndDate(reservationsDTO.endDate());

            // Recalcular el costo total
            long days = ChronoUnit.DAYS.between(reservation.getStartDate(), reservation.getEndDate());
            BigDecimal totalCost = reservation.getVehicle().getPricePerDay().multiply(BigDecimal.valueOf(days));
            reservation.setTotalCost(totalCost);

            reservation = reservationsRepository.save(reservation);
            return reservationsMapper.mapFromModel(reservation);
        } else {
            return null;
        }
    }

    public void deleteReservation(Integer id) {
        reservationsRepository.deleteById(id);
    }

    public boolean isVehicleAvailable(Integer vehicleId, LocalDate startDate, LocalDate endDate) {
        List<Reservation> reservations = reservationsRepository.findByVehicleIdAndDateRange(vehicleId, startDate, endDate);
        return reservations.isEmpty();
    }

    public String selectRentalDates(Integer vehicleId, LocalDate startDate, LocalDate endDate) {
        if (isVehicleAvailable(vehicleId, startDate, endDate)) {
            return "El vehículo está disponible para las fechas seleccionadas.";
        } else {
            return "El vehículo no está disponible para las fechas seleccionadas.";
        }
    }

    public List<ReservationsDTO> getReservationsByUserId(Integer userId) {
        return reservationsRepository.findByUserId(userId).stream()
                .map(reservationsMapper::mapFromModel)
                .collect(Collectors.toList());
    }

    public List<ReservationsDTO> getReservationsByDateRange(LocalDate startDate, LocalDate endDate) {
        return reservationsRepository.findByStartDateBetween(startDate, endDate).stream()
                .map(reservationsMapper::mapFromModel)
                .collect(Collectors.toList());
    }

}
