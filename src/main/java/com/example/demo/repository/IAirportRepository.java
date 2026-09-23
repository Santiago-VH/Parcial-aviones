package com.example.demo.repository;

import com.example.demo.model.Airport;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAirportRepository extends JpaRepository<Airport, Long> {
    List<Airport> findDistinctByDestinationFlightsOriginAirportName(String origenNombre);

    // List<Airport>
    // findDistinctTop5ByDestinationFlightsArrivalDateBetweenOrderByDestinationFlightsEstimatedPassengersDesc(
    // Timestamp inicio, Timestamp fin);

    List<Airport> findTop5ByDestinationFlightsArrivalDateBetweenOrderByDestinationFlightsEstimatedPassengersDesc(
            Timestamp inicio, Timestamp fin);
}
