package com.rutu.service;

public interface FileService {
    boolean writeToFile(String input);

    String readFromFile(String fileName);

    String readRecordFromFile(String fileName, String recordKey);
}
