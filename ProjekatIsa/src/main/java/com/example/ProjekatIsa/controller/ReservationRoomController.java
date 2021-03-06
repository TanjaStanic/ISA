package com.example.ProjekatIsa.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjekatIsa.DTO.ReservationRoomDTO;
import com.example.ProjekatIsa.model.DiscountHotel;
import com.example.ProjekatIsa.model.FlightReservation;
import com.example.ProjekatIsa.model.ReservationRoom;
import com.example.ProjekatIsa.model.Room;
import com.example.ProjekatIsa.model.User;
import com.example.ProjekatIsa.repository.DiscountHotelRepository;
import com.example.ProjekatIsa.repository.FlightReservationRepository;
import com.example.ProjekatIsa.repository.ReservationRoomRepository;
import com.example.ProjekatIsa.repository.RoomRepository;
import com.example.ProjekatIsa.repository.UserRepository;
import com.example.ProjekatIsa.service.ReservationRoomService;
import com.example.ProjekatIsa.service.RoomService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reservationRoom")
public class ReservationRoomController {
	@Autowired 
	private ReservationRoomRepository reservationRoomRepository;
	
	@Autowired 
	private ReservationRoomService reservationRoomService;

	@Autowired
	private FlightReservationRepository flightRepository;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private DiscountHotelRepository dhRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@RequestMapping(value="/bookRoom/{idRes}",
			method = RequestMethod.POST,
			consumes =MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> bookRoom(@RequestBody ReservationRoomDTO roomRes,
										@PathVariable Long idRes){
		System.out.println("Usao u bookRoom");
		
		Date startDate = null;
		Date endDate = null;
		
		startDate = roomRes.getStartDate();
		endDate = roomRes.getEndDate();
		
		Room room = roomRepository.findOneById(roomRes.getRoom().getId());
		User user = userRepository.findOneById(roomRes.getUser().getId());
		
		ReservationRoom returnValue = new ReservationRoom();
		
		if (!reserved(room, startDate, endDate)) {
			returnValue.setStartDate(roomRes.getStartDate());
			returnValue.setEndDate(roomRes.getEndDate());
			returnValue.setNumPeople(roomRes.getNumPeople());
			returnValue.setUser(user);
			returnValue.setTotalPrice(roomRes.getTotalPrice());
			returnValue.setCategory(roomRes.getCategory());
			returnValue.setRoom(room);
			
			returnValue = reservationRoomService.save(returnValue);
			
			//cuvam u let id rez
			FlightReservation help = new FlightReservation();	
			help = flightRepository.findOneById(idRes);
			System.out.println("mozes ocitati id :? "+returnValue.getId());
			help.setResRoomId(returnValue.getId());
			flightRepository.save(help);
			return new ResponseEntity<>(HttpStatus.CREATED);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		

	}
	public boolean reserved(Room r, Date startDate, Date endDate) {
				
		List<ReservationRoom> resRoom = reservationRoomService.findAllByRoom(r);
		
		for(ReservationRoom reservation : resRoom) {
			
			if(endDate.getTime() >= reservation.getStartDate().getTime() && endDate.getTime()<= reservation.getEndDate().getTime())
			{
				return true;
			} else if(startDate.getTime() >= reservation.getStartDate().getTime() && startDate.getTime() <= reservation.getEndDate().getTime())
			{
				return true;
			}
		}
		return false;
	}

	
	
	@RequestMapping(
			value="/fastReservationsHotel/{idFlight}/{idRoom}/{startDate}/{endDate}/{idUser}/{idRes}",
			method = RequestMethod.GET)
	public ResponseEntity<ReservationRoom> fastReservationsHotel(@PathVariable Long idFlight, 
																@PathVariable Long idRoom ,@PathVariable String startDate,
																@PathVariable String endDate, @PathVariable Long idUser,
																@PathVariable Long idRes) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date preuzimanje = null;
		Date vracanje = null;
		
		try {
			preuzimanje = dateFormat.parse(startDate);
			vracanje = dateFormat.parse(endDate);

			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		
		
		//FlightReservation flightReservation = flightRepository.findByFlightId(idFlight);//nadji tu rezervaciju leta
		Room room = roomRepository.findOneById(idRoom);
		DiscountHotel disc = dhRepository.findOneByRoom(room); //nadjem tu sobu na popustu
		User user = userRepository.findOneById(idUser);
		
		ReservationRoom fastRes = new ReservationRoom();
		fastRes.setRoom(room);
		fastRes.setEndDate(vracanje);
		fastRes.setStartDate(preuzimanje);
		double price = room.getPrice();
		double discountPrice = disc.getDiscountprice();
		double totalPrice = price - (price*discountPrice)/100;
		
		fastRes.setTotalPrice(totalPrice);
		fastRes.setCategory(room.getRoom_description());
		fastRes.setUser(user);
		
		fastRes = reservationRoomService.save(fastRes);
		//u flight res se treba postaviti i brza rez vozila
		if(idRes>0) {
			FlightReservation help = new FlightReservation();	
			
			help = flightRepository.findOneById(idRes);
			System.out.println("mozes ocitati id :? "+fastRes.getId());
			help.setResRoomId(fastRes.getId());
			flightRepository.save(help);
		}
		
		
		
		return new ResponseEntity<ReservationRoom>(fastRes,HttpStatus.OK);
		
	}
	//@PreAuthorize("hasAuthority('bookRoom')")
	@RequestMapping(
			value="/getAllMyFlights/{idUser}",
			method = RequestMethod.GET,
			consumes =MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllMyFlights(@PathVariable Long idUser){
		System.out.println("dosao u sve moje rezervacije");
		List<FlightReservation> helpList = new ArrayList<>();
		List<FlightReservation> returnList = new ArrayList<>();
		
		helpList = flightRepository.findAllByUserId(idUser);
		if (!helpList.isEmpty()) {
			for(FlightReservation f : helpList)
				if (f.getResRoomId()==null) {
					System.out.println("dodajem flight rez!");
					returnList.add(f);
				}
			System.out.println("broj help liste+ " + returnList.size());
			return new ResponseEntity<List<FlightReservation>>(returnList,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@RequestMapping(
			value="/chekIfFlightIsBooked/{idRes}",
			method = RequestMethod.POST,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public String chekIfFlightIsBooked(@RequestBody ReservationRoomDTO roomRes,
										@PathVariable Long idRes){
		
		System.out.println("dosao u cekiraj jel bukiran flajt tad");
		Date startDate = null;
		startDate = roomRes.getStartDate();
		
		FlightReservation help = new FlightReservation();
		
		help = flightRepository.findOneById(idRes);
		if (help!=null) {
			
			// provjeravamo da li je let prije rezervacije i provjeravamo broj putnika
			//datum pocetka rez hotela mora biti veci ili jednak datumu leta
			if (help.getDatum().getTime()>startDate.getTime()) {
				 //return new ResponseEntity<Boolean>(true, HttpStatus.OK);
				return Boolean.TRUE.toString();
			}
			//broj ljudi u letu morra biti veci ili jednak broju gostiju u hotelu
			if (roomRes.getNumPeople() > (double) help.getNumPass()) {
				//return new ResponseEntity<Boolean>(true, HttpStatus.OK);
				return Boolean.TRUE.toString();
			}
		}
		//return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		return Boolean.FALSE.toString();
		
	}
}
