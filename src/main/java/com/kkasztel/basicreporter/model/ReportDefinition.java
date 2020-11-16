package com.kkasztel.basicreporter.model;

import io.vavr.collection.IndexedSeq;
import lombok.ToString;
import lombok.Value;

import static io.vavr.API.Vector;

@Value(staticConstructor = "of")
@ToString(of = { "name" })
public class ReportDefinition {

    String name;
    IndexedSeq<Sheet> sheets;

    public static ReportDefinition of(String name, Table data) {
        return new ReportDefinition(name, Vector(Sheet.of(name, data)));
    }

    @Value(staticConstructor = "of")
    @ToString(of = { "name" })
    public static class Sheet {

        String name;
        Table data;
    }

    @Value
    @ToString(of = { "titleRow" })
    public static class Table {

        IndexedSeq<String> titleRow;
        IndexedSeq<IndexedSeq<String>> data;

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
            if (data.exists(r -> r.size() != titleRow.size())) {
                throw new IllegalArgumentException(
                        "Numer of columns in each row must be equal to the number of columns in the title row: " //
                                + titleRow.size());
            }
        }
    }
}
