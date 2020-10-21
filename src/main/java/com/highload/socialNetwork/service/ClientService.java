package com.highload.socialNetwork.service;

import com.highload.socialNetwork.model.Client;
import com.highload.socialNetwork.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@Service
public class ClientService {

    @Value("${fillData}")
    private String fillData="";
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll(int page, int size) {
        return clientRepository.getAll(page, size);
    }

    public void saveClient(Client client) {
        clientRepository.save(client);
    }
    @PostConstruct
    public void initData(){

        if(fillData.equals("true")){

            Random rand = new Random();

                List<Client> all = getAll(1, 100);

                all.forEach(el->{
                    for(int i=0;i<10000;i++){

                        String name = el.getName() + rand.nextInt(5000);
                        if(name.length()>15){
                            String subStringName = name.substring(0,6);
                            el.setName(subStringName);
                        }else{
                            el.setName(name);
                        }

                        String surName = el.getSurName() + rand.nextInt(5000);
                        if(surName.length()>15){
                            String substringSurname = surName.substring(0, 6);
                            el.setSurName(substringSurname);
                        }else{
                            el.setSurName(surName);
                        }
                        el.setInterest("SPORT");
                        saveClient(el);
                    }

                });
        }
        System.out.println("end fillData");
    }
}
