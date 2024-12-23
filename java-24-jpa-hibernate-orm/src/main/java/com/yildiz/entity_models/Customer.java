package com.yildiz.entity_models;

import jakarta.persistence.*;
// entity ile model arasında fark entity anatasyonu verildi ise o tablosu veritabanında mutkala aoluşur modelde ise yapmak zorunda değilsin
@Entity
@Table(name = "CUSTOMERS")
public class Customer {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "CUSTOMER_İD")
     private int id;
    @Column(name = "FIRST_NAME")
     private String firstName ;
    @Column(name = "LAST_NAME")
     private String lastName ;

    public Customer() {
    }
    public Customer(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
