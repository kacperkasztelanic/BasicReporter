package com.github.kkasztel.basicreporter.model;

public enum ReportType {

    CSV, XLSX, XLS;

    public String getExtension() {
        return name().toLowerCase();
    }
}
