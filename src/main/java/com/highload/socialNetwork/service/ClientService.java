package com.highload.socialNetwork.service;

import com.highload.socialNetwork.model.Client;
import com.highload.socialNetwork.repos.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll(int page,int size){
        return clientRepository.getAll(page, size);
    }

    public void saveClient(Client client){

    }
}
