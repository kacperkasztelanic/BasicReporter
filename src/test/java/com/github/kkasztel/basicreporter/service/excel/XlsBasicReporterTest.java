package com.github.kkasztel.basicreporter.service.excel;

import com.github.kkasztel.basicreporter.model.Report;
import com.github.kkasztel.basicreporter.model.ReportDefinition;
import com.github.kkasztel.basicreporter.model.ReportType;
import com.github.kkasztel.basicreporter.service.BasicReporter;
import com.github.kkasztel.basicreporter.service.TestDataProvider;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XlsBasicReporterTest {

    @Test
    public void successfullyGenerateXls() {
        BasicReporter reporter = new XlsBasicReporter(true, true);
        ReportDefinition report = TestDataProvider.getMultiSheetReportDefinition();
        Report actual = reporter.generate(report);
        assertEquals(report.getName(), actual.getName());
        assertEquals(ReportType.XLS, actual.getType());
    }
}
