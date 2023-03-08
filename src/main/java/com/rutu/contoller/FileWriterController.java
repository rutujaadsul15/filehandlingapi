package com.rutu.contoller;

import com.rutu.service.FileWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class FileWriterController {
    @Autowired
    private FileWriterService fileWriterService;

    @PostMapping("/writeToFile")
    public String writeToFile(@RequestBody String input) {
        return fileWriterService.writeToFile(input);
    }
}
