package model;

import model.Utility.FilePrinterUtil;
import model.Utility.FileReaderUtil;
import model.Utility.FileWriterUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerList {
    private final List<Manager> managers;
    private ArrayList<Manager> readManagers;
    public static String filePath = "data/binaryFiles/mangers.bin";
    public ManagerList(){
        managers = new ArrayList<>();
        //initializeManagers();
        readManagers = FileReaderUtil.readArrayListFromFile(filePath);
        FilePrinterUtil.printArrayList(readManagers);
    }
    private void initializeManagers(){
        managers.add(new Manager("Erion", "Molla", LocalDate.of(1998, 5, 11), "+355697900872", 65000.0,"4444"));
        managers.add(new Manager("Megi", "Bardhi", LocalDate.of(1996, 11, 23), "+355682950125", 73000.0, "5555"));
        managers.add(new Manager("Prishila", "Dedaj", LocalDate.of(1999, 1, 17), "+355691045581", 62500.0, "2211"));
        FileWriterUtil.writeArrayListToFile(managers, filePath);
        System.out.println("ArrayList of managers has been written to " + filePath);
    }

    public ArrayList<Manager> getReadManagers() {
        return readManagers;
    }

    public void setReadManagers(ArrayList<Manager> readManagers) {
        this.readManagers = readManagers;
    }

    public void printManagers() {
        System.out.println("List of Managers:\n");
        for (Manager manager : managers) {
            System.out.println(manager);
            System.out.println();
        }
    }
}
