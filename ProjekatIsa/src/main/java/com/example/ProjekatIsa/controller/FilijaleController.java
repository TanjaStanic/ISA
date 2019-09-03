package com.example.ProjekatIsa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.CarDTO;
import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.Filijale;
import com.example.ProjekatIsa.model.RentACar;
import com.example.ProjekatIsa.repository.CarRepository;
import com.example.ProjekatIsa.repository.FilijaleRepository;
import com.example.ProjekatIsa.service.FilijaleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value= "/filijale",produces = MediaType.APPLICATION_JSON_VALUE)
public class FilijaleController {
	
	@Autowired
	FilijaleService filService;
	
	@Autowired
	FilijaleRepository filRepository;
	
	@Autowired
	CarRepository carRepository;
	
	@RequestMapping(value="/addFilijale",method = RequestMethod.POST)
	public ResponseEntity<?> addNewFil(@RequestBody Filijale fil){
		System.out.println("dosaooo u addFil");
		Filijale nova = new Filijale();
		nova.setAdresa(fil.getAdresa());
		nova.setDrzava(fil.getDrzava());
		nova.setGrad(fil.getGrad());
		
		filRepository.save(nova);
		return new ResponseEntity<>(nova,HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value = "/getFilijales", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Filijale>  getFilijale() {
		System.out.println("Ukupno" + filService.getAll().size());	
		return filService.getAll();
}
	
	@RequestMapping(
			value = "/getCars", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCars(@RequestBody Long Id) {
		
		Filijale fil = filService.findOneById(Id);
		
		List<Car> returnList = new ArrayList<Car>();
		returnList = carRepository.findAllByFilijale(fil);
		System.out.println("Pronasao" + returnList);
		if (returnList==null) {
			return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Car>>(returnList, HttpStatus.OK);
		}	
		
}
	
	

}
