package com.highload.socialNetwork;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ClientController {
    private final ClientService service;


    public ClientController(ClientService service) {
        this.service = service;
    }
    @GetMapping(value="/getAll", produces = APPLICATION_JSON_VALUE)
    public List<Client> getAll(){
        return service.getAll();
    }
}
