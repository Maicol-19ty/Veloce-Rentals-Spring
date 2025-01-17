package cue.edu.co.velocerentals.controllers;

import cue.edu.co.velocerentals.domain.enums.VehicleStatus;
import cue.edu.co.velocerentals.domain.enums.VehicleType;
import cue.edu.co.velocerentals.mapping.dto.VehiclesDTO;
import cue.edu.co.velocerentals.service.VehiclesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
public class VehiclesController { // Public class handling REST endpoints for vehicle-related operations

    @Autowired
    private VehiclesService vehiclesService;

    @PostMapping("create")
    public Map<String, String> createVehicle(@RequestBody @Valid VehiclesDTO vehiclesDTO, BindingResult result) {
        // Creates a new vehicle, returns validation errors if any, or success message
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );
            return errors;
        }
        vehiclesService.createVehicle(vehiclesDTO);
        return Map.of("message", "Vehicle created successfully");
    }

    @GetMapping("search/{id}")
    public ResponseEntity<VehiclesDTO> getVehicleById(@PathVariable Integer id) {
        // Retrieves a vehicle by its ID
        VehiclesDTO vehicles = vehiclesService.getVehicleById(id);
        return vehicles != null ? ResponseEntity.ok(vehicles) : ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<VehiclesDTO>> getAllVehicles() {
        // Retrieves all vehicles
        List<VehiclesDTO> vehicles = vehiclesService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<VehiclesDTO> updateVehicle(@PathVariable Integer id, @RequestBody VehiclesDTO vehiclesDTO) {
        // Updates an existing vehicle, returns the updated vehicle or not found status
        VehiclesDTO updatedVehicle = vehiclesService.updateVehicle(id, vehiclesDTO);
        return updatedVehicle != null ? ResponseEntity.ok(updatedVehicle) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        // Deletes a vehicle by its ID
        vehiclesService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter/type/{type}")
    public ResponseEntity<List<VehiclesDTO>> filterByType(@PathVariable VehicleType type) {
        // Filters vehicles by type
        List<VehiclesDTO> vehicles = vehiclesService.filterByType(type);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/filter/price/{minPrice}/{maxPrice}")
    public ResponseEntity<List<VehiclesDTO>> filterByPriceRange(@PathVariable BigDecimal minPrice, @PathVariable BigDecimal maxPrice) {
        // Filters vehicles by price range
        List<VehiclesDTO> vehicles = vehiclesService.filterByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/filter/status/{status}")
    public ResponseEntity<List<VehiclesDTO>> filterByStatus(@PathVariable VehicleStatus status) {
        // Filters vehicles by status
        List<VehiclesDTO> vehicles = vehiclesService.filterByStatus(status);
        return ResponseEntity.ok(vehicles);
    }

}
