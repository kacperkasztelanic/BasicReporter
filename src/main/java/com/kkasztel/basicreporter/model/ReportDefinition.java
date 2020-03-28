package com.kkasztel.basicreporter.model;

import io.vavr.collection.IndexedSeq;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static io.vavr.API.Vector;

@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
@ToString(of = { "name" })
public class ReportDefinition {

    @Getter
    private final String name;
    @Getter
    private final IndexedSeq<Sheet> sheets;

    public static ReportDefinition of(String name, Table data) {
        return new ReportDefinition(name, Vector(Sheet.of(name, data)));
    }

    @AllArgsConstructor(staticName = "of")
    @EqualsAndHashCode
    @ToString(of = { "name" })
    public static final class Sheet {

        @Getter
        private final String name;
        @Getter
        private final Table data;
    }

    @EqualsAndHashCode
    @ToString(of = { "titleRow" })
    public static final class Table {

        @Getter
        private final IndexedSeq<String> titleRow;
        @Getter
        private final IndexedSeq<IndexedSeq<String>> data;

        public static Table of(IndexedSeq<String> titleRow, IndexedSeq<IndexedSeq<String>> data) {
            return new Table(titleRow, data);
        }

        private Table(IndexedSeq<String> titleRow, IndexedSeq<IndexedSeq<String>> data) {
            validate(titleRow, data);
            this.titleRow = titleRow;
            this.data = data;
        }

        public String getColumnTitle(int index) {
            validateColumnIndex(index);
            return titleRow.get(index);
        }

        public IndexedSeq<String> getDataRow(int index) {
            validateRowIndex(index);
            return data.get(index);
        }

        public IndexedSeq<String> getDataColumn(int index) {
            validateColumnIndex(index);
            return data.map(r -> r.get(index));
        }

        public String getDataCell(int row, int column) {
            validateRowIndex(row);
            validateColumnIndex(column);
            return data.get(row).get(column);
        }

        private void validateRowIndex(int row) {
            if (row >= data.size()) {
                throw new IllegalArgumentException("Max row index: " + (data.size() - 1));
            }
        }

        private void validateColumnIndex(int column) {
            if (column >= titleRow.size()) {
                throw new IllegalArgumentException("Max column index: " + (titleRow.size() - 1));
            }
        }

        private static void validate(IndexedSeq<String> titleRow, IndexedSeq<IndexedSeq<String>> data) {
            if (data.find(r -> r.size() != titleRow.size()).isDefined()) {
                throw new IllegalArgumentException(
                        "Numer of columns in each row must be equal to the number of columns in the title row: " //
                                + titleRow.size());
            }
        }
    }
}
