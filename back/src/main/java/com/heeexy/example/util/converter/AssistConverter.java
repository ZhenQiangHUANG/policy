package com.heeexy.example.util.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @ClassName AssistConverter
 * @Description TODO
 * @Author Huangzhenqiang
 * @Date 2020/11/10 9:44
 * @Version 1.0
 **/
public class AssistConverter implements Converter<Integer> {

    private static final String YES = "是";
    private static final String NO  = "否";

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
        if(NO.equals(stringValue))
        {
            return 1;
        }else if(YES.equals(stringValue))
        {
            return 2;
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
