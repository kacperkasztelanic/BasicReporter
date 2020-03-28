package com.kkasztel.basicreporter.service.csv.padding;

@FunctionalInterface
public interface CellFormatStrategy {

    String format(String input, int columnIndex);
}
