package com.kkasztel.basicreporter.service.csv;

import com.kkasztel.basicreporter.model.ReportingException;
import com.kkasztel.basicreporter.model.Report;
import com.kkasztel.basicreporter.model.ReportDefinition;
import com.kkasztel.basicreporter.model.ReportType;
import com.kkasztel.basicreporter.service.BasicReporter;
import com.kkasztel.basicreporter.service.csv.padding.CellFormatStrategy;
import com.kkasztel.basicreporter.service.csv.padding.CellFormatStrategyFactory;

import java.nio.charset.Charset;

import io.vavr.control.Either;

import static io.vavr.API.Try;

public class CsvBasicReporter implements BasicReporter {

    private final String separator;
    private final String lineSeparator;
    private final Charset charset;

    public CsvBasicReporter(String separator, String lineSeparator, Charset charset) {
        this.separator = separator;
        this.lineSeparator = lineSeparator;
        this.charset = charset;
    }

    @Override
    public Either<ReportingException, Report> tryGenerate(ReportDefinition definition) {
        return Try(() -> generate(definition))//
                .toEither()//
                .mapLeft(t -> new ReportingException(t.getMessage(), t));
    }

    @Override
    public Report generate(ReportDefinition definition) {
        if (definition.getSheets().size() > 1) {
            throw new IllegalArgumentException("Creation of csv file with multiple sheets is not possible");
        }
        return prepare(definition);
    }

    private Report prepare(ReportDefinition definition) {
        return Report.of(//
                definition.getSheets().head().getName(),//
                prepareData(definition.getSheets().head().getData()),//
                ReportType.CSV,//
                charset//
        );
    }

    private byte[] prepareData(ReportDefinition.Table data) {
        CellFormatStrategy formatStrategy = CellFormatStrategyFactory.createCellFormatStrategy(separator, data);
        return data.getData().prepend(data.getTitleRow())//
                .map(r -> r.zipWithIndex().map(p -> formatStrategy.format(p._1, p._2)))//
                .map(r -> r.mkString(separator))//
                .filter(s -> !s.trim().isEmpty())//
                .mkString(lineSeparator)//
                .getBytes(charset);
    }
}
