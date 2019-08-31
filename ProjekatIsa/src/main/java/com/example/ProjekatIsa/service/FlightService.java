package com.example.ProjekatIsa.service;

import java.util.List;

import com.example.ProjekatIsa.DTO.FlightDTO;
import com.example.ProjekatIsa.model.Flight;

public interface FlightService {
	
	List<Flight> getAllFlights();
    Flight getFlightById(Long id);
    Flight addFlight(Flight flight);
    Flight updateFlight(FlightDTO flight);
    Flight saveFlight(Flight flight);
    boolean deleteFlight(Flight flight);

}
