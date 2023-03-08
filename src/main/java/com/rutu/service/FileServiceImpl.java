package com.rutu.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;


@Service
public class FileServiceImpl implements FileService {

    @Override
    public String writeToFile(String input) {
        try {
            File file = new File("C:\\Users\\Admin\\Desktop/output1.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            if(!file.exists()){
                file.createNewFile();
                System.out.println("New file created");
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

    @Override
    public String readFromFile(String fileName) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                contentBuilder.append(line).append("\n");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return "File not found!";
        }
        return contentBuilder.toString();
    }

    public String readRecordFromFile(String fileName, String recordKey) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            StringBuilder contentBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                contentBuilder.append(scanner.nextLine());
            }
            scanner.close();
            JsonNode rootNode = objectMapper.readTree(contentBuilder.toString());
            Map<String, String> records = objectMapper.convertValue(rootNode, new TypeReference<Map<String, String>>() {});
            if (records.containsKey(recordKey)) {
                return records.get(recordKey);
            } else {
                return "Record not found!";
            }
        } catch (FileNotFoundException e) {
            return "File not found!";
        } catch (IOException e) {
            return "Error reading file!";
        }
    }
}
