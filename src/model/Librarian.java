package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Librarian extends Employee implements Serializable {
    public Librarian(String name, String surname, LocalDate birthDate, String phoneNumber, double salary, String password) {
        super(name, surname, birthDate, phoneNumber, salary, AccessLevel.SIMPLE, password);
    }
    @Override
    public String toString() {
        return super.toString();
    }
}

