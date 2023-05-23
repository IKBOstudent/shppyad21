package com.example.shppyad21.PostOffice;

import com.example.shppyad21.Departure.Departure;
import com.example.shppyad21.EmailService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class PostOfficeService {
    private final PostOfficeRepository postOfficeRepository;
    private final EmailService emailService;

    @Autowired
    public PostOfficeService(
            PostOfficeRepository postOfficeRepository,
            EmailService emailService
    ) {
        this.postOfficeRepository = postOfficeRepository;
        this.emailService = emailService;
    }

    public List<PostOffice> getAllPostOffices() {
        log.info("found all post offices");
        return postOfficeRepository.findAll();
    }

    public List<PostOffice> getPostOfficesByCity(String city) {
        log.info("found post offices by city");
        return postOfficeRepository.findByCity(city);
    }

    public List<PostOffice> getPostOfficesByName(String name) {
        log.info("found post offices by name");
        return postOfficeRepository.findByName(name);
    }

    public PostOffice getPostOfficeById(Long id) {
        Optional<PostOffice> postOfficeOptional = postOfficeRepository.findById(id);
        if (postOfficeOptional.isPresent()) {
            log.info("found post office by id");
            return postOfficeOptional.get();
        }
        log.error("post office search failed: invalid id");
        throw new IllegalStateException("no post office with id " + id);
    }

    public PostOffice createPostOffice(PostOffice postOffice) {
        postOfficeRepository.save(postOffice);
        System.out.println("trying to send email...");
        emailService.sendCreationEmail("maxDpon@yandex.ru", "Post Office");
        log.info("created new post office");
        return postOffice;
    }

    public void deletePostOfficeById(Long id) {
        if (postOfficeRepository.existsById(id)) {
            log.info("deleted post office");
            postOfficeRepository.deleteById(id);
            return;
        }

        log.error("post office deleting failed: invalid id");
        throw new IllegalStateException("no post office with id " + id);
    }

    public List<Departure> getDeparturesByPostOffice(Long id) {
        Optional<PostOffice> postOfficeOptional = postOfficeRepository.findById(id);
        if (postOfficeOptional.isPresent()) {
            log.info("found departures by post office id");
            return postOfficeOptional.get().getDepartureList();
        }
        log.error("departures search failed: invalid id");
        throw new IllegalStateException("no post office with id " + id);
    }
}
