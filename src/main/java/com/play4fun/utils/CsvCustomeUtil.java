package com.play4fun.utils;

import cn.hutool.core.text.csv.CsvReadConfig;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvRowHandler;
import cn.hutool.core.text.csv.CsvUtil;
import com.play4fun.handler.CustomeCsvRowHandler;
import lombok.extern.slf4j.Slf4j;
import java.io.FileReader;

@Slf4j
public class CsvCustomeUtil {

    public static void getCsvRow(String path, CsvRowHandler csvRowHandler) {
        try {
            CsvReader reader = CsvUtil.getReader(new FileReader(path), new CsvReadConfig().setContainsHeader(true));
            reader.read(csvRowHandler);
        } catch (Exception ex) {
            log.error("CsvCustomeUtil#getCsvRow error, path:{}", path, ex);
        }
    }

    public static Long getCsvRowNum(String path, CustomeCsvRowHandler customeCsvRowHandler) {
        getCsvRow(path, customeCsvRowHandler);
        return customeCsvRowHandler.getRowNum();
    }

    public <T> T csvRowToBean(CsvRow row, Class<T> clazz) {
        return row.toBean(clazz);
    }


}
