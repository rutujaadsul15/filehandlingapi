package com.rutu.service;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;




@Service
public class FileServiceImpl implements FileService {

    private Writer writer;

    @Override
    public boolean writeToFile(String input) {


        try {
            File file = new File("new1.txt"); //represents a file on the system with the specified path
            FileWriter fileWriter = new FileWriter(file, true); // writes character data to the specified file, true arg means
                                                                        // filewriter append the file if content already exist
            if(!file.exists()){
                file.createNewFile();
                System.out.println("New file created");
            }
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(input);
            bw.close();
            System.out.println("Successfully wrote to file.");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
            return false;
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
        ObjectMapper objectMapper = new ObjectMapper();  //converting JSON data to and from Java objects.
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());  //loop reads in the input data line-by-line
            }
            scanner.close();
            JsonNode rootNode = objectMapper.readTree(sb.toString());

            //This line converts the JSON-formatted string stored
            // in sb into a JsonNode object using an ObjectMapper
            Map<String, String> records = objectMapper.convertValue(rootNode, new TypeReference<Map<String, String>>() {});
           //This line converts the JsonNode object rootNode into a Map object called records,
            // where the keys are strings and the values are also strings.

            if (records.containsKey(recordKey)) {
                return records.get(recordKey);
            } else {
                return "Record not found!";  //for error
            }
        } catch (FileNotFoundException e) {
            return "File not found!";
        } catch (IOException e) {
            return " getting Error while reading file!";
        }
    }
}
