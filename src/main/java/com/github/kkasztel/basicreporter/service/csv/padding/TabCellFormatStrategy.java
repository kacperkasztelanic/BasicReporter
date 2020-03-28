package com.github.kkasztel.basicreporter.service.csv.padding;

import com.github.kkasztel.basicreporter.model.ReportDefinition;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.collection.Vector;

public class TabCellFormatStrategy implements CellFormatStrategy {

    private static final Function2<ReportDefinition.Table, Integer, Integer> LENGTH_FUNCTION =//
            (t, i) -> t.getDataColumn(i).append(t.getTitleRow().get(i))//
                    .map(String::trim)//
                    .map(String::length)//
                    .max()//
                    .getOrElse(0);

    private final Function1<Integer, Integer> lengthFunction;

    public TabCellFormatStrategy(ReportDefinition.Table table) {
        this.lengthFunction = LENGTH_FUNCTION.curried().apply(table).memoized();
    }

    @Override
    public String format(String input, int columnIndex) {
        String trimmed = input.trim();
        return String.format("%s%s\t", trimmed, spaces(lengthFunction.apply(columnIndex) - trimmed.length()));
    }

    private static String spaces(int n) {
        return Vector.fill(n, " ").mkString();
    }
}
