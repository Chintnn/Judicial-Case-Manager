package main.model;

import java.sql.Date;

public class Lawyer extends Person{

    private int personId;
    private String name;
    private Date dateOfBirth;
    private String contact;
    private String address;
    private String nationalId;
    private String licenseNumber;

    // No-argument constructor
    public Lawyer() {
        // This constructor is empty and allows you to create an object without setting any field initially
    }

    // Parameterized constructor
    public Lawyer(int personId, String name, Date dateOfBirth, String contact, String address, String nationalId, String licenseNumber) {
        this.personId = personId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.contact = contact;
        this.address = address;
        this.nationalId = nationalId;
        this.licenseNumber = licenseNumber;
    }

    // Getters and Setters
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    // You can also override toString(), equals(), hashCode(), etc., if needed
}
