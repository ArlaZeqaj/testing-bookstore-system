package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerList {
    private final List<Manager> managers;
    public ManagerList(){
        managers = new ArrayList<>();
        initializeManagers();
    }
    private void initializeManagers(){
        managers.add(new Manager("Erion", "Molla", LocalDate.of(1998, 5, 11), "+355697900872", 65000.0));
        managers.add(new Manager("Megi", "Bardhi", LocalDate.of(1996, 11, 23), "+355682950125", 73000.0));
        managers.add(new Manager("Prishila", "Dedaj", LocalDate.of(1999, 1, 17), "+355691045581", 62500.0));
    }
    public void printManagers() {
        System.out.println("List of Managers:\n");
        for (Manager manager : managers) {
            System.out.println(manager);
            System.out.println();
        }
    }
}
