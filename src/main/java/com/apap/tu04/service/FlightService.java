package com.apap.tu04.service;

import java.util.List;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;

public interface FlightService {
	void addFlight(FlightModel flight);
	List<FlightModel> getFlightListByPilot(PilotModel plt);
	List<FlightModel> getAllFlight();
	FlightModel getFlightDetailByFlightNumber(String flightNumber);
	void deleteFlight(String flightNumber);
}
