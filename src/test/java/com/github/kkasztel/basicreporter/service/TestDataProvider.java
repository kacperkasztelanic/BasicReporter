package com.github.kkasztel.basicreporter.service;

import com.github.kkasztel.basicreporter.model.ReportDefinition;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.vavr.API.Vector;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataProvider {

    public static ReportDefinition getReportDefinition() {
        return ReportDefinition.of(//
                "report",//
                ReportDefinition.Table.of(//
                        Vector("Col1", "Column2", "C3"),//
                        Vector(//
                                Vector("Shannon", "volutpat.nunc.sit@Etiamlaoreetlibero.ca", "AMX46MEU3VV"),//
                                Vector("", "non.dui.nec@Suspendisse.com", "MJV03INX9VG"),//
                                Vector("Wing", "ut.eros@nostraper.ca", ""),//
                                Vector("Tate", "elit.dictum.eu@Seddiamlorem.co.uk", "TVL01WPM8IV"),//
                                Vector("Clare", "", "DZH15PEQ1OE")//
                        )//
                )//
        );
    }
}
