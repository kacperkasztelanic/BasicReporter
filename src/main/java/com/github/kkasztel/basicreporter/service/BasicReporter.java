package com.github.kkasztel.basicreporter.service;

import com.github.kkasztel.basicreporter.model.Report;
import com.github.kkasztel.basicreporter.model.ReportDefinition;

public interface BasicReporter {

    Report generate(ReportDefinition definition);
}
