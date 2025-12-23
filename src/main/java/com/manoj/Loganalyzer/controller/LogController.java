package com.manoj.Loganalyzer.controller;

import com.manoj.Loganalyzer.model.LogSummary;
import com.manoj.Loganalyzer.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/validatelogsapi/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/analyze")
    public LogSummary analyze(@RequestBody String logs) {
        return logService.analyzeLogs(logs);
    }
    @PostMapping("/upload")
    public ResponseEntity<LogSummary> uploadLogFile(@RequestParam("file") MultipartFile file) {
        try {
            String content = new String(file.getBytes());
            LogSummary summary = logService.analyzeLogs(content);
            return ResponseEntity.ok(summary);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}