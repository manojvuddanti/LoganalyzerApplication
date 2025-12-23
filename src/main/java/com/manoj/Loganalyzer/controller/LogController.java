package com.manoj.Loganalyzer.controller;

import com.manoj.Loganalyzer.model.LogSummary;
import com.manoj.Loganalyzer.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validatelogsapi/logs")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/analyze")
    public LogSummary analyze(@RequestBody String logs) {
        return logService.analyzeLogs(logs);
    }
}