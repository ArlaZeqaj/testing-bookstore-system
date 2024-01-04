package model;

import java.time.LocalDate;
public class Manager extends Employee{
    public Manager(String name, String surname, LocalDate birthDate, String phoneNumber, double salary, String password) {
        super(name, surname, birthDate, phoneNumber, salary, AccessLevel.ADVANCED, password);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
