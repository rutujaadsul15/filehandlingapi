package com.rutu.contoller;

import com.rutu.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filehandling")

public class FileHandlerController {
    @Autowired
    private FileService fileWriterService;

    @PostMapping("/writeToFile")
    public String writeToFile(@RequestBody String input) {
        return fileWriterService.writeToFile(input);
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
