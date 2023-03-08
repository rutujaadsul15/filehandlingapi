package com.rutu.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class FileWriterServiceImpl implements FileWriterService {

    @Override
    public String writeToFile(String input) {
        try {
            File file = new File("output.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter.write(input);
            fileWriter.close();
            System.out.println("Successfully wrote to file.");
            return "Successfully wrote to file.";
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
            return "An error occurred while writing to file.";
        }
    }
}
