package com.apap.tu04.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;

@Repository
public interface FlightDb extends JpaRepository<FlightModel, Long> {
	List <FlightModel> findByPilot(PilotModel pilot);
	FlightModel findByFlightNumber(String flightNumber);
	void deleteByFlightNumber(String flightNumber);
}
