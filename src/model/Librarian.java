package model;

public class Librarian extends Employee {
    public Librarian(String name, String surname, String birthDate, String phoneNumber, int email, AccessLevel salary) {
        super(name, surname, birthDate, phoneNumber, email, salary, AccessLevel.SIMPLE);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

