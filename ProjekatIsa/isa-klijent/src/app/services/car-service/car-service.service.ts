import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Car } from '../../models/Car';
import { CarReservation } from '../../models/CarReservation';
import { AuthServiceService } from '../auth-service.service';
import { SearchFormServices } from '../../models/SearchFormServices';
import { PricingCar } from '../../models/PricingCar';

@Injectable({
  providedIn: 'root'
})
export class CarServiceService {

  constructor(private http: HttpClient, private router : Router,private auth: AuthServiceService) { }
    
    addCar(car : Car) : Observable<any> {
      return this.http.post('http://localhost:8080/car/addCar',car,{headers: this.auth.createAuthorizationTokenHeader()}); 
        }
    
    deleteCar(id : number) : Observable<any> {
        return this.http.delete('http://localhost:8080/car/deleteCar/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 

        }
    
    getCars(id : number): Observable<any>{
  
        return this.http.post('http://localhost:8080/car/getCars',id,{headers: this.auth.createAuthorizationTokenHeader()});
        }
    
    getAll(id : number): Observable<any>{
  
        return this.http.post('http://localhost:8080/car/getAll',id,{headers: this.auth.createAuthorizationTokenHeader()});
        }

    
    changeCar(newCar: Car, id: number) : Observable<any> {
        return this.http.post('http://localhost:8080/car/changeCar/'+id,newCar,{headers: this.auth.createAuthorizationTokenHeader()}); 
        }
    
    searchCars(car : CarReservation, id : number, cenaOd : number,cenaDo:number) :Observable<any>{
        console.log('usao u pretraga servis');
        return this.http.post('//localhost:8080/car/searchCar/'+id+"/"+cenaOd+"/"+cenaDo,car,{headers: this.auth.createAuthorizationTokenHeader()});
    }
    
     getDiscountCars(id:number): Observable<any>{
  
        return this.http.get('http://localhost:8080/car/getDiscountCars/'+id,{headers: this.auth.createAuthorizationTokenHeader()});
        }
    
     getCarById(id : number): Observable<any>{
  
        return this.http.post('http://localhost:8080/car/getCarById',id,{headers: this.auth.createAuthorizationTokenHeader()});
        }
    
    searchDiscountCars(ss : SearchFormServices, id:number): Observable<any>{
      return this.http.post('http://localhost:8080/car/searchFast/'+id,ss);
        }
    
    getAllMyFlights(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/carReservation/getAllMyFlights/'+id,{headers: this.auth.createAuthorizationTokenHeader()}); 
        };
    
    chekIfFlightIsBooked(res : CarReservation, id : number) : Observable<any> {
      return this.http.post('http://localhost:8080/car/chekIfFlightIsBooked/'+id,res); 
        };
    
    getAllPricing(id: number): Observable<any> {
      return this.http.get('http://localhost:8080/pricingcar/getAllPricing/'+id); 
        };
    changePricing(pricing: PricingCar, id: number) : Observable<any> {
      return this.http.post('http://localhost:8080/pricingcar/changePricing/'+id,pricing); 
        };
    addPricing(newPricing: PricingCar, id:number): Observable<any>{
      return this.http.post('http://localhost:8080/pricingcar/addPricing/'+id,newPricing); 

  }
    
    
    
    
    
    
    
    
}
