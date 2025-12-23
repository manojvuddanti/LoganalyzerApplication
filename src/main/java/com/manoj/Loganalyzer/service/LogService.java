package com.manoj.Loganalyzer.service;

import com.manoj.Loganalyzer.model.LogSummary;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LogService {

    public LogSummary analyzeLogs(String logs) {

        int errorCount = 0;
        int warningCount = 0;
        String lastErrorTimestamp = null;

        Map<String, Integer> errorFrequency = new HashMap<>();

        Pattern errorPattern = Pattern.compile("(ERROR)\\s+(.*)");
        Pattern warningPattern = Pattern.compile("(WARN|WARNING)");

        String[] lines = logs.split("\\n");

        for (String line : lines) {

            Matcher errorMatcher = errorPattern.matcher(line);
            Matcher warningMatcher = warningPattern.matcher(line);

            if (errorMatcher.find()) {
                errorCount++;

                String errorMessage = errorMatcher.group(2).trim();
                errorFrequency.put(errorMessage, errorFrequency.getOrDefault(errorMessage, 0) + 1);

                lastErrorTimestamp = extractTimestamp(line);
            }

            if (warningMatcher.find()) {
                warningCount++;
            }
        }

        String mostFrequentError = errorFrequency.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        return new LogSummary(errorCount, warningCount, mostFrequentError, lastErrorTimestamp);
    }

    private String extractTimestamp(String line) {
        Pattern timestampPattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}");
        Matcher matcher = timestampPattern.matcher(line);
        return matcher.find() ? matcher.group() : null;
    }
}