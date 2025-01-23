package test;

import model.Utility.FileWriterUtil;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileWriterTest {

    @Test
    void testWriteArrayListToFile() {
        List<String> testList = new ArrayList<>();
        testList.add("First");
        testList.add("Second");
        testList.add("Third");

        File tempFile = null;
        try {
            tempFile = File.createTempFile("testList", ".dat");
            tempFile.deleteOnExit();

            String filePath = tempFile.getAbsolutePath();
            FileWriterUtil.writeArrayListToFile(testList, filePath);

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                @SuppressWarnings("unchecked")
                List<String> resultList = (List<String>) ois.readObject();

                assertNotNull(resultList, "The result list should not be null.");
                assertEquals(testList.size(), resultList.size(), "The sizes of the original and deserialized lists should match.");
                assertEquals(testList, resultList, "The contents of the original and deserialized lists should match.");
            } catch (IOException | ClassNotFoundException e) {
                fail("Exception during file reading: " + e.getMessage());
            }

        } catch (IOException e) {
            fail("Failed to create a temporary file: " + e.getMessage());
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }
}

