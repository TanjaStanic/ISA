import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {Router} from '@angular/router';
import { Observable } from 'rxjs';
import { Hotel } from '../../models/Hotel';
import { Room } from '../../models/Room';
import { Pricing } from '../../models/Pricing';

import { ReservationRoom } from '../../models/ReservationRoom';
import { SearchFormServices } from '../../models/SearchFormServices';

import {AuthServiceService} from '../auth-service.service';
import { AdditionalServiceForHotel } from '../../models/AdditionalServiceForHotel';



@Injectable({
  providedIn: 'root'
})
export class HotelServiceService {

  constructor(private http: HttpClient, private router : Router,private auth: AuthServiceService) { }

  addHotel(hotel : Hotel) : Observable<any> {
      return this.http.post('http://localhost:8080/hotels/addHotel',hotel); 
  };
  
  changeHotel(newHotel: Hotel, id: number) : Observable<any> {
      return this.http.post('http://localhost:8080/hotels/changeHotel/'+id,newHotel); 
  }; 
  
  deleteHotel(id:number) : Observable<any> {
      return this.http.post('http://localhost:8080/hotels/deleteHotel',id); 
  };
  
  addService(newService: AdditionalServiceForHotel, id:number): Observable<any>{
      return this.http.post('http://localhost:8080/addServices/addService/'+id,newService); 

  }
  addRoom(newRoom: Room, id:number): Observable<any>{
      return this.http.post('http://localhost:8080/rooms/addRoom/'+id,newRoom); 

  }
  deleteService(id:number): Observable<any>{
      return this.http.get('http://localhost:8080/addServices/deleteService/'+id); 

  }
  changeService(service: AdditionalServiceForHotel, id: number) : Observable<any> {
      return this.http.post('http://localhost:8080/addServices/changeService/'+id,service); 
  };
  deleteRoom(id:number): Observable<any>{
      return this.http.get('http://localhost:8080/rooms/deleteRoom/'+id); 
  };
  
  changeRoom(room: Room, id: number) : Observable<any> {
      return this.http.post('http://localhost:8080/rooms/changeRoom/'+id,room); 
  };
  getAllPricing(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/pricing/getAllPricing/'+id); 
  };
  changePricing(pricing: Pricing, id: number) : Observable<any> {
      return this.http.post('http://localhost:8080/pricing/changePricing/'+id,pricing); 
  };
  addPricing(newPricing: Pricing, id:number): Observable<any>{
      return this.http.post('http://localhost:8080/pricing/addPricing/'+id,newPricing); 

  }
  searchRooms(res : ReservationRoom, id : number, cenaod : number, cenado: number) : Observable<any> {
      return this.http.post('http://localhost:8080/rooms/searchRooms/'+id + "/"+cenaod + "/" +cenado,res); 
  };
  chekIfFlightIsBooked(res : ReservationRoom, id : number) : Observable<any> {
      return this.http.post('http://localhost:8080/reservationRoom/chekIfFlightIsBooked/'+id,res); 
  };
  bookRoom(res : ReservationRoom, id: number) : Observable<any> {
      return this.http.post('http://localhost:8080/reservationRoom/bookRoom/'+id,res); 
  };
  
  getLastWeekReservations(id: number, dateToday : string): Observable<any> {
      return this.http.get('http://localhost:8080/hotels/getLastWeekReservations/'+id+"/"+dateToday); 
  };
  
  getAllReservations(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/hotels/getAllReservations/'+id); 
  };
  getAllMyFlights(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/reservationRoom/getAllMyFlights/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 
  };
  getAllRatingsHotel(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/hotels/getAllRatingsHotel/'+id); 
  };
  getRatingRoom(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/rooms/getRatingRoom/'+id); 
  };
  getHotelRevenue(id: number, od :string, Do : string) : Observable<any> {
      return this.http.get('http://localhost:8080/hotels/getHotelRevenue/'+id +'/' + od +'/' + Do); 
  };
  countAverageRating(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/rooms/countAverageRating/'+id); 
  };
  countAverageRatingHotel(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/hotels/countAverageRating/'+id); 
  };
  getDiscountRooms(city:any): Observable<any>{
      return this.http.get('http://localhost:8080/rooms/getDiscountRooms/'+city);
  };
  getDiscountRoomsid(id:number): Observable<any>{
      return this.http.get('http://localhost:8080/rooms/getDiscountRoomsid/'+id);
  };
  searchDiscountRooms(ss : SearchFormServices, id:number): Observable<any>{
      return this.http.post('http://localhost:8080/rooms/searchFast/'+id,ss);
  };
  
  isReserved(id:number): Observable<any>{
      return this.http.get('http://localhost:8080/rooms/isReserved/'+id);
  };
}
