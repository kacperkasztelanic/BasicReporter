package com.kkasztel.basicreporter.service.excel;

import com.kkasztel.basicreporter.model.Report;
import com.kkasztel.basicreporter.model.ReportDefinition;
import com.kkasztel.basicreporter.service.BasicReporter;
import com.kkasztel.basicreporter.service.TestDataProvider;

import org.junit.jupiter.api.Test;

import static com.kkasztel.basicreporter.model.ReportType.XLS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class XlsBasicReporterTest {

    @Test
    void successfullyGenerateXls() {
        BasicReporter reporter = new XlsBasicReporter(true, true);
        ReportDefinition report = TestDataProvider.getMultiSheetReportDefinition();
        Report actual = reporter.generate(report);
        assertEquals(report.getName(), actual.getName());
        assertEquals(XLS, actual.getType());
    }
}
