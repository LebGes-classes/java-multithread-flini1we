package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class TasksReader {
    public static HashMap<String, Integer> tasksOpener(String filePath) {
        HashMap<String, Integer> tasksInfo = new HashMap<>();
        try (FileReader fr = new FileReader(filePath)){

            BufferedReader reader = new BufferedReader(fr);
            String line;

            while ((line = reader.readLine()) != null) {
                tasksInfo.put(line.split(";")[0],
                              Integer.parseInt(line.split(";")[1]));
            }

            return tasksInfo;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
