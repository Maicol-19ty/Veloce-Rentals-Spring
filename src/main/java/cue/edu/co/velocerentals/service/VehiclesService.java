package cue.edu.co.velocerentals.service;

import cue.edu.co.velocerentals.domain.enums.VehicleStatus;
import cue.edu.co.velocerentals.domain.enums.VehicleType;
import cue.edu.co.velocerentals.domain.models.Vehicle;
import cue.edu.co.velocerentals.mapping.dto.VehiclesDTO;
import cue.edu.co.velocerentals.mapping.mappers.VehiclesMapper;
import cue.edu.co.velocerentals.repository.VehiclesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiclesService {

    @Autowired
    private VehiclesRepository vehiclesRepository;

    public VehiclesDTO createVehicle(VehiclesDTO vehiclesDTO) {
        Vehicle vehicle = VehiclesMapper.mapFromDTO(vehiclesDTO);
        vehicle = vehiclesRepository.save(vehicle);
        return VehiclesMapper.mapFromModel(vehicle);
    }

    public VehiclesDTO getVehicleById(Integer id) {
        Optional<Vehicle> vehicle = vehiclesRepository.findById(id);
        return vehicle.map(VehiclesMapper::mapFromModel).orElse(null);
    }

    public List<VehiclesDTO> getAllVehicles() {
        return vehiclesRepository.findAll().stream()
                .map(VehiclesMapper::mapFromModel)
                .collect(Collectors.toList());
    }

    public VehiclesDTO updateVehicle(Integer id, VehiclesDTO vehiclesDTO) {
        Optional<Vehicle> vehicleOptional = vehiclesRepository.findById((id));
        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            vehicle.setType(vehiclesDTO.type());
            vehicle.setMake(vehiclesDTO.make());
            vehicle.setModel(vehiclesDTO.model());
            vehicle.setYear(vehiclesDTO.year());
            vehicle.setPricePerDay(vehiclesDTO.pricePerDay());
            vehicle.setStatus(vehiclesDTO.status());
            vehicle = vehiclesRepository.save(vehicle);
            return VehiclesMapper.mapFromModel(vehicle);
        } else {
            return null;
        }
    }

    public void deleteVehicle(Integer id) {
        vehiclesRepository.deleteById(id);
    }

    public List<VehiclesDTO> filterByType(VehicleType type) {
        return vehiclesRepository.findByType(type).stream()
                .map(VehiclesMapper::mapFromModel)
                .collect(Collectors.toList());
    }

    public List<VehiclesDTO> filterByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return vehiclesRepository.findByPricePerDayBetween(minPrice, maxPrice).stream()
                .map(VehiclesMapper::mapFromModel)
                .collect(Collectors.toList());
    }

    public List<VehiclesDTO> filterByStatus(VehicleStatus status) {
        return vehiclesRepository.findByStatus(status).stream()
                .map(VehiclesMapper::mapFromModel)
                .collect(Collectors.toList());
    }

}
