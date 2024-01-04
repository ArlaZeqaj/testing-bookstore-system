package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarianList {
    private final List<Librarian> librarians;
    public LibrarianList(){
        librarians = new ArrayList<>();
        initializeLibrarians();
    }
    private void initializeLibrarians(){
        librarians.add(new Librarian("Sara", "Molla", LocalDate.of(1998, 5, 11), "+355697900872", 65000.0));
        librarians.add(new Librarian("Lisa", "Bardhi", LocalDate.of(1996, 11, 23), "+355682950125", 73000.0));
        librarians.add(new Librarian("Riku", "Dedaj", LocalDate.of(1999, 1, 17), "+355691045581", 62500.0));
    }
    public void printLibrarians() {
        System.out.println("List of Librarians:\n");
        for (Librarian librarian : librarians) {
            System.out.println(librarian);
            System.out.println();
        }
    }
}
