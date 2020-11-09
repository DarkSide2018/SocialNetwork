package com.highload.socialNetwork.service;

import com.github.javafaker.Faker;
import com.highload.socialNetwork.model.Client;
import com.highload.socialNetwork.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@DependsOn("liquibase")
public class ClientService {

    @Value("${fillData}")
    private String fillData = "true";
    @Value("${startDataCount}")
    private Integer startDataCount = 300;
    private final ClientRepository clientRepository;
    private Random random = new Random();
    private Faker faker = new Faker();

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll(int page, int size) {
        return clientRepository.getAll(page, size);
    }

    public List<Client> getByFirstNameAndSecondNamePrefix(String name, String surname) {
        return clientRepository.findByPrefixFirstNameAndSecondName(name, surname);
    }
    public List<Client> getByName(String name) {
        return clientRepository.getClientByName(name);
    }

    public void saveClients(List<Client> clients) {
        clientRepository.batchSave(clients);
    }

    @PostConstruct
    public void initData() {

        if (!fillData.equals("true")) return;

        Integer count = clientRepository.checkCount();
        if(count == null) return;
        if (count >= startDataCount) {
            System.out.println("database filled");
            return;
        }
        System.out.println("start fill data");
        List<Client> clientList = new ArrayList<>(5000);
        for (int i = 0; i < startDataCount; i++) {
            Client client = new Client();
            String name = faker.name().firstName();
            client.setName(name);
            client.setSurName(faker.name().lastName());
            client.setInterest(faker.lorem().sentence(10));
            client.setAge(random.nextInt(50));
            client.setCity(faker.address().city());
            client.setGender(faker.demographic().sex());
            System.out.println("client was created  with name -> " + client.getName() + " and surname -> " + client.getSurName());
            clientList.add(client);
            if (clientList.size() >= 5000) {
                saveClients(clientList);
                clientList.clear();
                Integer counter = clientRepository.checkCount();
                if(counter == null) return;
                if (counter >= startDataCount) {
                    System.out.println("database filled");
                    return;
                }
            }else if(i>=startDataCount-1){
                saveClients(clientList);
            }
        }
        System.out.println("end fillData");
    }
}

