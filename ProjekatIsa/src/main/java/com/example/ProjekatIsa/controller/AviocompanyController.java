package com.example.ProjekatIsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.AviocompanyDTO;
import com.example.ProjekatIsa.DTO.DestinationDTO;
import com.example.ProjekatIsa.model.Aviocompany;
import com.example.ProjekatIsa.model.Destination;
import com.example.ProjekatIsa.model.Flight;
import com.example.ProjekatIsa.model.Hotel;
import com.example.ProjekatIsa.repository.RatingHotelRepository;
import com.example.ProjekatIsa.service.AviocompanyService;
import com.example.ProjekatIsa.service.DestinationService;
import com.example.ProjekatIsa.service.HotelService;


@RestController
@RequestMapping(value="/avioCompany",produces = MediaType.APPLICATION_JSON_VALUE)

public class AviocompanyController {
	
	
	@Autowired
	private AviocompanyService avioService;
	
	@Autowired
	private DestinationService destinationService;
	
	@RequestMapping(
			value = "/getAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Aviocompany>  getAvioCompany() {
		
		System.out.println("Number of company: " + avioService.getAll().size());
		
		return avioService.getAll();
}
	
	
	@GetMapping(value="/{id}")
	public Aviocompany getCompanytByID(@PathVariable("id") Long id){
		
		return avioService.getCompanyByID(id);
	}
	
	
	@GetMapping(value="flight/{id}")
	public Aviocompany getCompany(@PathVariable("id") Long id){
		
		return avioService.getCompanyByFlightId(id);
	}
	
	
	 @PostMapping
	 public ResponseEntity<Aviocompany> addAirline(@RequestBody AviocompanyDTO airlineDTO){
//PREPRAVITI AKO SE BUDE ADRESA PRAVILA KAO NOVA KLASA ZBOG MAPA, DODATI ADRESU U POSEBNU TABELU PRVO PA ONDA VEZATI SA AVIOKOMPANIJOM
		 Aviocompany aviocompany = this.avioService.addAvioCompany(new Aviocompany(airlineDTO));
	        return ResponseEntity.ok(aviocompany);
	    }
	 
	 @PutMapping(value="/update")
	 public ResponseEntity<Aviocompany> updateAirline(@RequestBody Aviocompany airlineDTO){
		    if(airlineDTO == null){
	            return ResponseEntity.notFound().build();
	        }
	        this.avioService.updateAviocompany(airlineDTO);
	        return ResponseEntity.ok(airlineDTO);
	 }
	 
	 @DeleteMapping(value = "/{id}")
	 
	    public ResponseEntity deleteAirline(@PathVariable Long id){
	        Aviocompany aviocompany = this.avioService.getCompanyByID(id);
	        if(aviocompany == null) {
	            return ResponseEntity.notFound().build();
	        }
	        boolean success = this.avioService.deleteAirline(aviocompany);

	        if (success) {
	            return ResponseEntity.ok().build();
	        } else {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	        }
	    }

	
	 
}
