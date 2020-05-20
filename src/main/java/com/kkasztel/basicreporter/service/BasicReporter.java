package com.kkasztel.basicreporter.service;

import com.kkasztel.basicreporter.model.ReportingException;
import com.kkasztel.basicreporter.model.Report;
import com.kkasztel.basicreporter.model.ReportDefinition;

import io.vavr.control.Either;

import static java.util.function.Function.identity;

public interface BasicReporter {

    Either<ReportingException, Report> tryGenerate(ReportDefinition definition);

    default Report generate(ReportDefinition definition) {
        return tryGenerate(definition).getOrElseThrow(identity());
    }
}
