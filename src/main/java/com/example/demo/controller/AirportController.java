package com.example.demo.controller;

import com.example.demo.model.Airline;
import com.example.demo.model.Airport;
import com.example.demo.repository.IAirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportController {

    private final IAirportRepository airportRepository;

    @GetMapping
    public List<Airport> findAllAirports() {
        return airportRepository.findAll();
    }

    @GetMapping("/by-origin")
    public List<Airport> findByOrigin(@RequestParam String origen) {
        return airportRepository.findDistinctByDestinationFlightsOriginAirportName(origen);
    }

    /*
     * @GetMapping("/top-arrivals")
     * public List<Airport> topArrivals(
     * 
     * @RequestParam String inicio,
     * 
     * @RequestParam String fin) {
     * return airportRepository.
     * findDistinctTop5ByDestinationFlightsArrivalDateBetweenOrderByDestinationFlightsEstimatedPassengersDesc(
     * Timestamp.valueOf(inicio), Timestamp.valueOf(fin)
     * );
     * }
     */

    @GetMapping("/top-arrivals")
    public List<Airport> topArrivals(
            @RequestParam String inicio,
            @RequestParam String fin) {

        List<Airport> resultados = airportRepository
                .findTop5ByDestinationFlightsArrivalDateBetweenOrderByDestinationFlightsEstimatedPassengersDesc(
                        Timestamp.valueOf(inicio), Timestamp.valueOf(fin));

        List<Airport> sinDuplicados = new ArrayList<>();
        for (Airport a : resultados) {
            boolean yaEsta = false;
            for (Airport existente : sinDuplicados) {
                if (existente.getId().equals(a.getId())) {
                    yaEsta = true;
                    break;
                }
            }
            if (!yaEsta) {
                sinDuplicados.add(a);
            }
        }

        return sinDuplicados;
    }

}
