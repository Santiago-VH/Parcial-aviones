package com.example.demo.repository;

import com.example.demo.model.Airline;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAirlineRepository extends JpaRepository<Airline, Long> {
    List<Airline> findDistinctByAirplanesFlightsOriginAirportNameAndAirplanesFlightsDestinationAirportName(
            String origenNombre, String destinoNombre);

    List<Airline> findDistinctByAirplanesFlightsOriginAirportCityOrAirplanesFlightsDestinationAirportCity(
            String ciudadOrigen, String ciudadDestino);

    List<Airline> findDistinctByAirplanesFlightsArrivalDateBetweenAndAirplanesFlightsDestinationAirportName(
            Timestamp inicio, Timestamp fin, String aeropuertoNombre);
}
