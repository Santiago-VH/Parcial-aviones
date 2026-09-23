package com.example.demo.controller;

import com.example.demo.model.Airline;
import com.example.demo.repository.IAirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final IAirlineRepository airlineRepository;

    @GetMapping
    public List<Airline> findAllAirlines() {
        return airlineRepository.findAll();
    }

    @GetMapping("/by-route")
    public List<Airline> findByRoute(
            @RequestParam String origen,
            @RequestParam String destino) {
        return airlineRepository
                .findDistinctByAirplanesFlightsOriginAirportNameAndAirplanesFlightsDestinationAirportName(origen,
                        destino);

    }

    @GetMapping("/by-city")
    public List<Airline> findByCity(
            @RequestParam String ciudad) {
        return airlineRepository
                .findDistinctByAirplanesFlightsOriginAirportCityOrAirplanesFlightsDestinationAirportCity(ciudad,
                        ciudad);

    }

    @GetMapping("/by-arrival-range")
    public List<Airline> findByArrivalRange(
            @RequestParam String inicio,
            @RequestParam String fin,
            @RequestParam String aeropuerto) {
        Timestamp desde = Timestamp.valueOf(inicio);
        Timestamp hasta = Timestamp.valueOf(fin);
        return airlineRepository
                .findDistinctByAirplanesFlightsArrivalDateBetweenAndAirplanesFlightsDestinationAirportName(
                        desde, hasta, aeropuerto);
    }

}
