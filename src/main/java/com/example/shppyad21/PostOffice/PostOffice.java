package com.example.shppyad21.PostOffice;

import com.example.shppyad21.Departure.Departure;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post_offices_table")
@Getter
@Setter
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "postOffice", cascade = CascadeType.ALL)
    private List<Departure> departureList = new ArrayList<>();

    public PostOffice() {}

    public PostOffice(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public void addDeparture(Departure departure) {
        this.departureList.add(departure);
    }
}
