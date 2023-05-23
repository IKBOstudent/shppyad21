package com.example.shppyad21.PostOffice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
    List<PostOffice> findByCity(String city);
    List<PostOffice> findByName(String name);
}
