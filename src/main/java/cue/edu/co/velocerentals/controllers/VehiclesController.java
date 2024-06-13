package cue.edu.co.velocerentals.controllers;

import cue.edu.co.velocerentals.mapping.dto.VehiclesDTO;
import cue.edu.co.velocerentals.service.VehiclesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
public class VehiclesController {

    @Autowired
    private VehiclesService vehiclesService;

    @PostMapping("create")
    public Map<String, String> createVehicle(@RequestBody @Valid VehiclesDTO vehiclesDTO, BindingResult result) {
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
        VehiclesDTO vehicles = vehiclesService.getVehicleById(id);
        return vehicles != null ? ResponseEntity.ok(vehicles) : ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    public ResponseEntity<List<VehiclesDTO>> getAllVehicles() {
        List<VehiclesDTO> vehicles = vehiclesService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<VehiclesDTO> updateVehicle(@PathVariable Integer id, @RequestBody VehiclesDTO vehiclesDTO) {
        VehiclesDTO updatedVehicle = vehiclesService.updateVehicle(id, vehiclesDTO);
        return updatedVehicle != null ? ResponseEntity.ok(updatedVehicle) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        vehiclesService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

}
