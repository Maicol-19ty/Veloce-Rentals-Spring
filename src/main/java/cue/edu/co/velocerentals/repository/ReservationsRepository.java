package cue.edu.co.velocerentals.repository;

import cue.edu.co.velocerentals.domain.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, Integer> {
}
