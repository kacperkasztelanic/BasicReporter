package com.kkasztel.basicreporter.service.excel;

import com.kkasztel.basicreporter.model.Report;
import com.kkasztel.basicreporter.model.ReportDefinition;

import io.vavr.control.Try;

import static com.kkasztel.basicreporter.model.ReportType.XLS;
import static io.vavr.API.Try;
import static java.nio.charset.StandardCharsets.UTF_8;

public class XlsBasicReporter extends AbstractExcelBasicReporter {

    public XlsBasicReporter() {
        super(false, false);
    }

    public XlsBasicReporter(boolean useAutosize, boolean bordered) {
        super(useAutosize, bordered);
    }

    @Override
    public Try<Report> generateReport(ReportDefinition definition) {
        return Try(() -> generateData(definition, false))
                .map(d -> Report.of(definition.getName(), d, XLS, UTF_8));
    }
}
