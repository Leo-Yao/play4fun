package com.play4fun.handler;

import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvRowHandler;

public class CustomeCsvRowHandler implements CsvRowHandler {
    private Long rowNum;

    @Override
    public void handle(CsvRow row) {
        rowNum++;
    }

    public Long getRowNum() {
        return rowNum;
    }
}
