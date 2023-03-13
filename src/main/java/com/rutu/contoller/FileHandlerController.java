package com.rutu.contoller;

import com.fasterxml.jackson.databind.JsonNode;
import com.rutu.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filehandling")

public class FileHandlerController {
    @Autowired
    private FileService fileWriterService;

    @PostMapping("/writeToFile")
    public ResponseEntity<String> writeToFile(@RequestBody JsonNode json) {
        boolean result = fileWriterService.writeToFile(json.toString());
        if(result) {
            return ResponseEntity.ok("Data written to file successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to write data to file.");
        }
    }


    @GetMapping("/readFile/{fileName}")
    public String readFile(@PathVariable String fileName) {
        return fileWriterService.readFromFile(fileName);
    }

    @GetMapping("/readRecord/{fileName}/{recordKey}")
    public String readRecord(@PathVariable String fileName, @PathVariable String recordKey) {
        return fileWriterService.readRecordFromFile(fileName, recordKey);
    }
}
