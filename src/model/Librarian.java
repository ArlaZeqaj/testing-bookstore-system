package model;

import java.time.LocalDate;

public class Librarian extends Employee {
    public Librarian(String name, String surname, LocalDate birthDate, String phoneNumber, double salary, String password) {
        super(name, surname, birthDate, phoneNumber, salary, AccessLevel.SIMPLE, password);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}

