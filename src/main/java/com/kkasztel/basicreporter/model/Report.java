package com.kkasztel.basicreporter.model;

import java.nio.charset.Charset;

import lombok.Value;

@Value(staticConstructor = "of")
public class Report {

    String name;
    byte[] bytes;
    ReportType type;
    Charset charset;

    public String getFullName() {
        return String.format("%s.%s", name, type.getExtension());
    }

    @Override
    public String toString() {
        return String.format("Report(%s, %s)", getFullName(), charset.displayName());
    }
}
