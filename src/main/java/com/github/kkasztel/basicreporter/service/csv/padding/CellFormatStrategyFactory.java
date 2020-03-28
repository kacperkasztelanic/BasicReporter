package com.github.kkasztel.basicreporter.service.csv.padding;

import com.github.kkasztel.basicreporter.model.ReportDefinition;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.is;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CellFormatStrategyFactory {

    public static CellFormatStrategy createCellFormatStrategy(String separator, ReportDefinition.Table table) {
        CellFormatStrategy simpleStrategy = (s, i) -> s;
        return Match(separator).of(//
                Case($(is("\t")), new TabCellFormatStrategy(table)),//
                Case($(), simpleStrategy)//
        );
    }
}
