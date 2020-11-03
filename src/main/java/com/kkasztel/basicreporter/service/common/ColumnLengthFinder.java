package com.kkasztel.basicreporter.service.common;

import com.kkasztel.basicreporter.model.ReportDefinition.Table;

import io.vavr.Function2;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ColumnLengthFinder {

    public static Function2<Table, Integer, Integer> getLengthFunction() {
        return (t, i) -> t.getDataColumn(i).append(t.getTitleRow().get(i))//
                .map(String::trim)//
                .map(String::length)//
                .max()//
                .getOrElse(0);
    }
}
