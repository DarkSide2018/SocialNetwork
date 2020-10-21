package com.highload.socialNetwork.service;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.highload.socialNetwork.model.Client;
import com.highload.socialNetwork.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
@DependsOn("clientRepository")
public class ClientService {

    @Value("${fillData}")
    private String fillData="";
    private final ClientRepository clientRepository;
    Random random = new Random();
    Faker faker = new Faker();
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll(int page, int size) {
        return clientRepository.getAll(page, size);
    }

    public void saveClients(List<Client> clients) {
        clientRepository.batchSave(clients);
    }
    @PostConstruct
    public void initData(){

        if(fillData.equals("true")){
            if(clientRepository.checkCount()>=1000000){
                System.out.println("database filled");
                return;
            }
            System.out.println("start fill data");
            List<Client>clientList = new ArrayList<>(5000);
                    for(int i=0;i<1000000;i++){
                        Client client = new Client();
                        System.out.println("iteration = " + i);
                        String name = faker.name().firstName();
                        System.out.println("fakeNAme="+ name);
                        client.setName(name);
                        client.setSurName(faker.name().lastName());
                        client.setInterest(faker.chuckNorris().fact());
                        client.setAge(random.nextInt(50));
                        client.setCity(faker.address().city());
                        client.setGender(faker.demographic().sex());
                        clientList.add(client);
                        if(clientList.size()>=5000){
                            saveClients(clientList);
                            clientList = new ArrayList<>(5000);
                        }
                    }

        }
        System.out.println("end fillData");
    }
}
