package main;

import model.AccessLevel;
import model.Employee;

import java.util.Date;

public class Librarian extends Employee {
    public Librarian(String name, Date birthDate, String phoneNumber, String email, double salary, AccessLevel accessLevel) {
        super(name, birthDate, phoneNumber, email, salary, accessLevel);
    }
}
