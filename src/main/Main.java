package main;
import model.*;

import java.time.LocalDate;
import java.util.Date;

import java.time.Year;

public class Main {
    public static void main(String[] args) {
        /*
        // testing the creation of Book objects
        Book book1 = new Book("978-0-12345-0123456-7-0", "The Secret History", new Category("Mystery"), "Penguin Books", Year.of(2018), 12.5, 18.0, 16.5, new Author("Donna", "", "Tartt"), 27);
        System.out.println(book1);
        */
        //testing Manager objects
        /*
        Manager manager1 = new Manager("Erion", "Molla", LocalDate.of(1998, 5, 11), "+355697900872", 65000.0);
        System.out.println(manager1);
        */
        Employee employee = new Librarian("Erion", "Molla", LocalDate.of(1998, 5, 11), "+355697900872", 65000.0);
        ManagerList managerList = new ManagerList();
        managerList.printManagers();
    }
}