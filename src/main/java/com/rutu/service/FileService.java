package com.rutu.service;

public interface FileService {
    String writeToFile(String input);

    String readFromFile(String fileName);

    String readRecordFromFile(String fileName, String recordKey);
}
