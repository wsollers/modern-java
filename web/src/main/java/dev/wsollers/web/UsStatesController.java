package dev.wsollers.web.controller;

import dev.wsollers.northwinds.domain.UsState;
import dev.wsollers.northwinds.repository.UsStateRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/us-states")
public class UsStatesController {

    private final UsStateRepository usStateRepository;

    public UsStatesController(UsStateRepository usStateRepository) {
        this.usStateRepository = usStateRepository;
    }

    @GetMapping(value = "/list", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<UsState> list() {
        return usStateRepository.findAll();
    }
}