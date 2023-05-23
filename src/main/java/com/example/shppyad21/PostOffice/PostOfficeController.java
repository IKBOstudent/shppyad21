package com.example.shppyad21.PostOffice;

import com.example.shppyad21.Departure.Departure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/postoffices")
public class PostOfficeController {
    private final PostOfficeService postOfficeService;

    @Autowired
    public PostOfficeController(PostOfficeService postOfficeService) {
        this.postOfficeService = postOfficeService;
    }

    @GetMapping
    public ResponseEntity<List<PostOffice>> getPostOffices(
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "name", required = false) String name) {
        if (city != null) {
            return ResponseEntity.ok(postOfficeService.getPostOfficesByCity(city));
        }
        if (name != null) {
            return ResponseEntity.ok(postOfficeService.getPostOfficesByName(name));
        }
        return ResponseEntity.ok(postOfficeService.getAllPostOffices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostOffice> getPostOfficeById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(postOfficeService.getPostOfficeById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}/departures")
    public ResponseEntity<List<Departure>> getDeparturesByPostOffice(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(postOfficeService.getDeparturesByPostOffice(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<PostOffice> createPostOffice(@RequestBody PostOffice postOffice) {
        return ResponseEntity.ok(postOfficeService.createPostOffice(postOffice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostOfficeById(@PathVariable("id") Long id) {
        try {
            postOfficeService.deletePostOfficeById(id);
            return ResponseEntity.ok("Post office deleted.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid id");
        }
    }
}
