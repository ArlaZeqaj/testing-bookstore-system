package model;

import model.AccessLevel;
import model.Employee;

import java.time.LocalDate;
import java.util.Date;

public class Librarian extends Employee {
    public Librarian(String name, String surname, LocalDate birthDate, String phoneNumber, String email, double salary) {
        super(name, surname, birthDate, phoneNumber, email, salary, AccessLevel.SIMPLE);
    }
}

@Override
public String toString() {
    return super.toString();
}