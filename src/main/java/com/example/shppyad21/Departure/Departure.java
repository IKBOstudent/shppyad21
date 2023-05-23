package com.example.shppyad21.Departure;

import com.example.shppyad21.PostOffice.PostOffice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "departures_table")
@Getter
@Setter
public class Departure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @Column(name = "date")
    private String date;

    @ManyToOne
    @JsonIgnore
    private PostOffice postOffice;

    public Departure() {}

    public Departure(String type, String date) {
        this.type = type;
        this.date = date;
    }
}