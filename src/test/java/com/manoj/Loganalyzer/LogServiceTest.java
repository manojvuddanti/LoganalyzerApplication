package com.manoj.Loganalyzer;

import com.manoj.Loganalyzer.service.LogService;
import com.manoj.Loganalyzer.model.LogSummary;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LogServiceTest {

	@Test
	void testAnalyzeLogs() {
		String logs = """
                2024-12-21T10:00:00 ERROR Something broke
                2024-12-21T10:05:00 WARN Slow network
                2024-12-21T10:10:00 ERROR Something broke
                """;

		LogService service = new LogService();
		LogSummary summary = service.analyzeLogs(logs);

		assertThat(summary.getErrorCount()).isEqualTo(2);
		assertThat(summary.getWarningCount()).isEqualTo(1);
		assertThat(summary.getMostFrequentError()).isEqualTo("Something broke");
		assertThat(summary.getLastErrorTimestamp()).isEqualTo("2024-12-21T10:10:00");
	}
}