package com.apap.tu04.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "flight")
public class FlightModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlightModel(String fn, String or, String dest, Date tm, PilotModel p) {
		this.flightNumber = fn;
		this.origin = or;
		this.destination = dest;
		this.time = tm;
		this.pilot = p;
	}
	public FlightModel() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "flight_number", nullable = false)
	private String flightNumber;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "origin", nullable = false)
	private String origin;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "destination", nullable = false)
	private String destination;
	
	@NotNull
	@Column(name = "time")
	private Date time;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pilot_licensenumber", referencedColumnName = "license_number", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PilotModel pilot;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public PilotModel getPilot() {
		return pilot;
	}

	public void setPilot(PilotModel pilot) {
		this.pilot = pilot;
	}
}
