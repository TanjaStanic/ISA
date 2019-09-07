package com.example.ProjekatIsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjekatIsa.model.Car;
import com.example.ProjekatIsa.model.CarReservation;
import com.example.ProjekatIsa.model.User;


@Repository
public interface CarReservationRepository extends JpaRepository<CarReservation, Long> {
	CarReservation save(CarReservation c);

	List<CarReservation> findAllByCar(Car cr);
	List<CarReservation> findAllByUser(User user);
	

}