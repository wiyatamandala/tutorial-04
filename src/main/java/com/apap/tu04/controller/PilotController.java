package com.apap.tu04.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tu04.model.FlightModel;
import com.apap.tu04.model.PilotModel;
import com.apap.tu04.service.FlightService;
import com.apap.tu04.service.PilotService;

@Controller
public class PilotController {
	PilotModel plt;
	@Autowired
	private PilotService pilotService;
	
	@Autowired
	private FlightService flightService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		plt = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		List<FlightModel> lFlight= flightService.getFlightListByPilot(plt);
		model.addAttribute("license_number", plt.getLicenseNumber().toString());
		model.addAttribute("name", plt.getName().toString());
		model.addAttribute("fly_hour", plt.getFlyHour());
		model.addAttribute("found", plt);
		model.addAttribute("fList",lFlight);
		return "view-pilot";
	}
	
	@RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
	public String deletePilot(@PathVariable("licenseNumber") String licenseNumber, @ModelAttribute PilotModel pilot) {
		plt = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        if (plt == null) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND).toString();
        }
		pilotService.deletePilot(licenseNumber);
		return "home";
	}
	
	@RequestMapping(value = "/pilot/update/", method = RequestMethod.POST)
	public String updatePilotSubmit(@RequestParam("licenseNumber") String licenseNumber,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "fly_hour", required = true) int fly_hour, Model model) {
		plt = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        if (plt != null) {
        	plt.setName(name);
    		plt.setFlyHour(fly_hour);
    		pilotService.updatePilot(plt);
    		
    		List<FlightModel> lFlight= flightService.getFlightListByPilot(plt);
    		model.addAttribute("license_number", plt.getLicenseNumber().toString());
    		model.addAttribute("name", plt.getName().toString());
    		model.addAttribute("fly_hour", plt.getFlyHour());
    		model.addAttribute("found", plt);
    		model.addAttribute("fList",lFlight);
    		return "view-pilot";
        }
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND).toString();
	}
	
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String updatePilot(@PathVariable("licenseNumber") String licenseNumber, Model model) {
		plt = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("license_number", plt.getLicenseNumber().toString());
		model.addAttribute("name", plt.getName().toString());
		model.addAttribute("fly_hour", plt.getFlyHour());
		model.addAttribute("found", plt);
		return "update-pilot";
	}
}
