package cue.edu.co.velocerentals.domain.models;

import cue.edu.co.velocerentals.config.VehicleEnumConverter;
import cue.edu.co.velocerentals.domain.enums.VehicleStatus;
import cue.edu.co.velocerentals.domain.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "vehicles", schema = "veloce_rentals")
public class Vehicle {
    @Id
    @Column(name = "vehicle_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    @Convert(converter = VehicleEnumConverter.class)
    private VehicleType type;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "price_per_day", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerDay;

    @ColumnDefault("'available'")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Convert(converter = VehicleEnumConverter.class)
    private VehicleStatus status;

}