package com.apap.tu04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;
import com.apap.tu04.service.FlightService;
import com.apap.tu04.service.PilotService;

@Controller
public class FlightController {
	FlightModel fli;
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
		
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "/flight/view/", method = RequestMethod.GET)
    public String viewAll(Model model) {
        model.addAttribute("fList", flightService.getAllFlight());
        return "view-flight";
    }
	
	@RequestMapping(value = "/flight/delete/{flightNumber}", method = RequestMethod.GET)
	public String deleteFlight(@PathVariable("flightNumber") String flightNumber, @ModelAttribute FlightModel flight) {
		fli = flightService.getFlightDetailByFlightNumber(flightNumber);
        if (fli == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND).toString();
        }
		flightService.deleteFlight(flightNumber);
		return "home";
	}
	
}
