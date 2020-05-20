package com.kkasztel.basicreporter.service.excel;

import com.kkasztel.basicreporter.model.Report;
import com.kkasztel.basicreporter.model.ReportDefinition;
import com.kkasztel.basicreporter.model.ReportType;

import java.nio.charset.StandardCharsets;

import io.vavr.control.Try;

import static io.vavr.API.Try;

public class XlsxBasicReporter extends AbstractExcelBasicReporter {

    public XlsxBasicReporter() {
        super(false, false);
    }

    public XlsxBasicReporter(boolean useAutosize, boolean bordered) {
        super(useAutosize, bordered);
    }

    @Override
    public Try<Report> generateReport(ReportDefinition definition) {
        return Try(() -> generateData(definition, true))//
                .map(d -> Report.of(//
                        definition.getName(),//
                        d,//
                        ReportType.XLSX,//
                        StandardCharsets.UTF_8//
                        )//
                );
    }
}
