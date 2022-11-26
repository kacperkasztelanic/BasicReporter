package com.kkasztel.basicreporter.service.csv;

import com.kkasztel.basicreporter.model.Report;
import com.kkasztel.basicreporter.service.BasicReporter;
import com.kkasztel.basicreporter.service.TestDataProvider;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import io.vavr.control.Try;

import static com.kkasztel.basicreporter.model.ReportType.CSV;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CsvBasicReporterTest {

    @Test
    void successfullyGeneratesCsv() {
        BasicReporter reporter = new CsvBasicReporter(",", System.lineSeparator(), UTF_8);
        Report actual = reporter.generate(TestDataProvider.getReportDefinition());
        Try.of(() -> IOUtils.resourceToByteArray("input.csv", getClass().getClassLoader()))
                .map(a -> Report.of("report", a, CSV, UTF_8))
                .peek(r -> assertEquals(r, actual))
                .orElseRun(t -> fail());
    }

    @Test
    void successfullyGeneratesTsv() {
        BasicReporter reporter = new CsvBasicReporter("\t", System.lineSeparator(), UTF_8);
        Report actual = reporter.generate(TestDataProvider.getReportDefinition());
        Try.of(() -> IOUtils.resourceToByteArray("input.tsv", getClass().getClassLoader()))
                .map(a -> Report.of("report", a, CSV, UTF_8))
                .peek(r -> assertEquals(r, actual))
                .orElseRun(t -> fail());
    }
}
