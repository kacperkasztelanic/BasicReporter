package com.kkasztel.basicreporter.service.excel;

import com.kkasztel.basicreporter.model.Report;
import com.kkasztel.basicreporter.model.ReportDefinition;
import com.kkasztel.basicreporter.model.ReportType;

import java.nio.charset.StandardCharsets;

import io.vavr.control.Try;

public class XlsBasicReporter extends AbstractExcelBasicReporter {

    public XlsBasicReporter() {
        super(false, false);
    }

    public XlsBasicReporter(boolean useAutosize, boolean bordered) {
        super(useAutosize, bordered);
    }

    @Override
    public Report generate(ReportDefinition definition) {
        return Try.of(() -> generateData(definition, false))//
                .map(d -> Report.of(//
                        definition.getName(),//
                        d,//
                        ReportType.XLS,//
                        StandardCharsets.UTF_8//
                        )//
                )//
                .getOrElseThrow(t -> new RuntimeException(t.getMessage(), t));
    }
}
