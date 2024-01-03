package model;

import java.time.LocalDate;
public class Manager extends Employee{
    public Manager(String name, String surname, LocalDate birthDate, String phoneNumber, double salary) {
        super(name, surname, birthDate, phoneNumber, salary, AccessLevel.ADVANCED);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
