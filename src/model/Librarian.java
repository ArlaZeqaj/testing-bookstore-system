package model;

import java.time.LocalDate;

public class Librarian extends Employee {
    public Librarian(String name, String surname, LocalDate birthDate, String phoneNumber, double salary) {
        super(name, surname, birthDate, phoneNumber, salary, AccessLevel.SIMPLE);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}

