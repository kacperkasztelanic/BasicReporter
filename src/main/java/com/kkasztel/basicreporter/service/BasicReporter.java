package com.kkasztel.basicreporter.service;

import com.kkasztel.basicreporter.model.Report;
import com.kkasztel.basicreporter.model.ReportDefinition;

public interface BasicReporter {

    Report generate(ReportDefinition definition);
}
