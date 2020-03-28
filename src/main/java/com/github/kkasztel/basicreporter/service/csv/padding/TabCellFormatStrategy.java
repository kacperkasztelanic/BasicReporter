package com.github.kkasztel.basicreporter.service.csv.padding;

import com.github.kkasztel.basicreporter.model.ReportDefinition;
import com.github.kkasztel.basicreporter.service.common.ColumnLengthFinder;

import io.vavr.Function1;
import io.vavr.collection.Vector;

class TabCellFormatStrategy implements CellFormatStrategy {

    private final Function1<Integer, Integer> lengthFunction;

    public TabCellFormatStrategy(ReportDefinition.Table table) {
        this.lengthFunction = ColumnLengthFinder.getLengthFunction().curried().apply(table).memoized();
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
