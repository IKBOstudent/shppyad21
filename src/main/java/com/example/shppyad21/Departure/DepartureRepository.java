package com.example.shppyad21.Departure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartureRepository extends JpaRepository<Departure, Long> {
    List<Departure> findByDate(String date);
    List<Departure> findByType(String type);
}
