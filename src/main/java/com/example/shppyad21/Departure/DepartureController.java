package com.example.shppyad21.Departure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DepartureController {
    private final DepartureService departureService;

    @Autowired
    public DepartureController(DepartureService departureService) {
        this.departureService = departureService;
    }

    @GetMapping("/departures")
    public ResponseEntity<List<Departure>> getDepartures(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "type", required = false) String type) {
        if (date != null) {
            return ResponseEntity.ok(departureService.getDeparturesByDate(date));
        }
        if (type != null) {
            return ResponseEntity.ok(departureService.getDeparturesByType(type));
        }
        return ResponseEntity.ok(departureService.getAllDepartures());
    }

    @GetMapping("/departures/{id}")
    public ResponseEntity<Departure> getDepartureById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(departureService.getDepartureById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/postoffices/{officeId}/departures")
    public ResponseEntity<Departure> addDeparture(@PathVariable("officeId") Long officeId, @RequestBody Departure departure) {
        try {
            return ResponseEntity.ok(departureService.addDeparture(officeId, departure));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/departures/{id}")
    public ResponseEntity<String> deleteDepartureById(@PathVariable("id") Long id) {
        try {
            departureService.deleteDepartureById(id);
            return ResponseEntity.ok("Departure deleted.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid id");
        }
    }
}