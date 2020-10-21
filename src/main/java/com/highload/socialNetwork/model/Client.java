package com.highload.socialNetwork.model;


import java.util.Objects;

public class Client {
    private Long id;
    private String name;
    private String surName;
    private Integer age;
    private String gender;
    private String interest;
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Client(
    ) {

    }

    public Client(long id,
                  String name,
                  String surName,
                  int age,
                  String gender,
                  String interest,
                  String city
    ) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.gender = gender;
        this.interest = interest;
        this.city = city;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id) &&
                name.equals(client.name) &&
                surName.equals(client.surName) &&
                age.equals(client.age) &&
                gender.equals(client.gender) &&
                interest.equals(client.interest) &&
                city.equals(client.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surName, age, gender, interest, city);
    }
}
