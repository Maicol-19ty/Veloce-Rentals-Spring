package cue.edu.co.velocerentals.repository;

import cue.edu.co.velocerentals.domain.enums.VehicleStatus;
import cue.edu.co.velocerentals.domain.enums.VehicleType;
import cue.edu.co.velocerentals.domain.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VehiclesRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findByType(VehicleType type);
    List<Vehicle> findByPricePerDayBetween(BigDecimal minPrice, BigDecimal maxPrice);
    List<Vehicle> findByStatus(VehicleStatus status);

}
