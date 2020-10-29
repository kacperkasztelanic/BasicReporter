package com.kkasztel.basicreporter.service.csv;

import com.kkasztel.basicreporter.model.Report;
import com.kkasztel.basicreporter.model.ReportType;
import com.kkasztel.basicreporter.service.BasicReporter;
import com.kkasztel.basicreporter.service.TestDataProvider;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import io.vavr.control.Try;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CsvBasicReporterTest {

    @Test
    void successfullyGeneratesCsv() {
        BasicReporter reporter = new CsvBasicReporter(",", System.lineSeparator(), StandardCharsets.UTF_8);
        Report actual = reporter.generate(TestDataProvider.getReportDefinition());
        Try.of(() -> IOUtils.resourceToByteArray("input.csv", getClass().getClassLoader()))//
                .map(a -> Report.of("report", a, ReportType.CSV, StandardCharsets.UTF_8))//
                .peek(r -> assertEquals(r, actual))//
                .orElseRun(t -> fail());
    }

    @Test
    void successfullyGeneratesTsv() {
        BasicReporter reporter = new CsvBasicReporter("\t", System.lineSeparator(), StandardCharsets.UTF_8);
        Report actual = reporter.generate(TestDataProvider.getReportDefinition());
        Try.of(() -> IOUtils.resourceToByteArray("input.tsv", getClass().getClassLoader()))//
                .map(a -> Report.of("report", a, ReportType.CSV, StandardCharsets.UTF_8))//
                .peek(r -> assertEquals(r, actual))//
                .orElseRun(t -> fail());
    }
}
