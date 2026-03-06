package org.example;

import jakarta.persistence.*;

//@Entity(name="my_alien") this will change the entity AND table name
@Entity
@Table(name = "alien_table")
public class Alien {
    @Id
    private int id;
    @Column(name = "alien_name")//to change sql column name without touching class
    private String name;
    private Address address;
    @OneToOne(fetch = FetchType.EAGER)//fetch laptop too when you fetch alien(not lazy)
    private Laptop laptop;

    @Transient//Transients aren't stored in db, just java
    private String tech;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", laptop=" + laptop +
                ", tech='" + tech + '\'' +
                '}';
    }
}
