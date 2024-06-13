package cue.edu.co.velocerentals.repository;

import cue.edu.co.velocerentals.domain.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.vehicle.id = :vehicleId AND " +
            "(r.startDate BETWEEN :startDate AND :endDate OR r.endDate BETWEEN :startDate AND :endDate)")
    List<Reservation> findByVehicleIdAndDateRange(Integer vehicleId, LocalDate startDate, LocalDate endDate);

    List<Reservation> findByUserId(Integer userId);
    List<Reservation> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

}
