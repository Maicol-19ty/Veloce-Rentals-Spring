package cue.edu.co.velocerentals.repository;

import cue.edu.co.velocerentals.domain.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclesRepository extends JpaRepository<Vehicle, Integer> {
}
