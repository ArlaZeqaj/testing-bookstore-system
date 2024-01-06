package model;

import model.Utility.ValidationUtil;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Employee implements User, Serializable {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String phoneNumber;
    private String email;
    private double salary;
    private AccessLevel accessLevel;
    private String username;
    private String password;

    public Employee(String name, String surname, LocalDate birthDate, String phoneNumber, double salary, AccessLevel accessLevel, String password) {
        if(ValidationUtil.isValid(name, ValidationUtil.STRING_REGEX))
            this.name = name;
        if(ValidationUtil.isValid(name, ValidationUtil.STRING_REGEX))
            this.surname = surname;
        this.birthDate = birthDate;
        if (ValidationUtil.isValid(phoneNumber, ValidationUtil.PHONE_REGEX))
            this.phoneNumber = phoneNumber;
        this.email = name.toLowerCase() + "." + surname.toLowerCase() + "@bookstore.com"; //auto-generated email
        this.salary = salary;
        this.accessLevel = accessLevel;
        this.username = name.toLowerCase() + surname.toLowerCase(); //auto-generated username
        this.password = password;
    }

    public Employee(String name, String surname, String birthDate, String phoneNumber, int email, AccessLevel salary, AccessLevel simple, String password) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name, String surname) {
        this.email = name + "." + surname + "@bookstore.com";
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee data \n" +
                "name: " + name + '\n' +
                "surname: " + surname + '\n' +
                "birthDate: " + birthDate + '\n' +
                "phoneNumber: " + phoneNumber + '\n' +
                "email: " + email + '\n' +
                "salary: " + salary + '\n' +
                "accessLevel: " + accessLevel;
    }
}
