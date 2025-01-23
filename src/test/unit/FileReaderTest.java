package test;

import model.Utility.FileReaderUtil;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    private static final String TEST_FILE_PATH = "testFile2.dat";

    @Test
    void readArrayListFromFile_success() throws IOException, ClassNotFoundException {
        ArrayList<String> originalList = new ArrayList<>();
        originalList.add("Item1");
        originalList.add("Item2");

        // Write the ArrayList to the file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TEST_FILE_PATH))) {
            oos.writeObject(originalList);
        }

        ArrayList<String> readList = FileReaderUtil.readArrayListFromFile(TEST_FILE_PATH);

        assertNotNull(readList);
        assertEquals(originalList, readList);
    }

    @Test
    void readArrayListFromFile_emptyFile() throws IOException {
        File emptyFile = new File(TEST_FILE_PATH);
        if (!emptyFile.exists()) {
            emptyFile.createNewFile();
        }

        ArrayList<String> readList = FileReaderUtil.readArrayListFromFile(TEST_FILE_PATH);

        assertNotNull(readList);
        assertTrue(readList.isEmpty());
    }
    @Test
    void readArrayListFromFile_fileNotFound() {
        String nonExistentFilePath = "nonExistentFile.dat";

        ArrayList<String> readList = FileReaderUtil.readArrayListFromFile(nonExistentFilePath);

        assertNotNull(readList);
        assertTrue(readList.isEmpty());
    }

}

