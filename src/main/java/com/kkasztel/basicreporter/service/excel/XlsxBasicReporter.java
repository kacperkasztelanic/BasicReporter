package com.kkasztel.basicreporter.service.excel;

import com.kkasztel.basicreporter.model.Report;
import com.kkasztel.basicreporter.model.ReportDefinition;

import io.vavr.control.Try;

import static com.kkasztel.basicreporter.model.ReportType.XLSX;
import static io.vavr.API.Try;
import static java.nio.charset.StandardCharsets.UTF_8;

public class XlsxBasicReporter extends AbstractExcelBasicReporter {

    public XlsxBasicReporter() {
        super(false, false);
    }

    public XlsxBasicReporter(boolean useAutosize, boolean bordered) {
        super(useAutosize, bordered);
    }

    @Override
    public Try<Report> generateReport(ReportDefinition definition) {
        return Try(() -> generateData(definition, true))
                .map(d -> Report.of(definition.getName(), d, XLSX, UTF_8));
    }
}
