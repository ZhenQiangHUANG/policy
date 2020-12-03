package com.heeexy.example.util.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @ClassName LevelConverter
 * @Description TODO
 * @Author Huangzhenqiang
 * @Date 2020/11/10 9:23
 * @Version 1.0
 **/
public class LevelConverter implements Converter<Integer> {

    /* 一般-★ 积极-★★ 重点-★★★ 强烈-★★★★*/
    private static final String GENERAL  = "★";
    private static final String POSITIVE = "★★";
    private static final String FOCUS    = "★★★";
    private static final String STRONG   = "★★★★";

    @Override
    public Class supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String stringValue = cellData.getStringValue();
        if(GENERAL.equals(stringValue))
        {
            return 1;
        }else if(POSITIVE.equals(stringValue))
        {
            return 2;
        }else if(FOCUS.equals(stringValue))
        {
            return 3;
        }else if(STRONG.equals(stringValue))
        {
            return 4;
        }else
        {
            return -1;
        }
    }

    @Override
    public CellData convertToExcelData(Integer integer, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }
}
