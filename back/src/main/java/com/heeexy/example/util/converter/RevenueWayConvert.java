package com.heeexy.example.util.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @ClassName RevenueConvert
 * @Description TODO
 * @Author Huangzhenqiang
 * @Date 2020/11/10 9:37
 * @Version 1.0
 **/
public class RevenueWayConvert implements Converter<Integer> {

    private static final String INDIRECT = "间接创效";
    private static final String DIRECT =   "直接创效";

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
        if(INDIRECT.equals(stringValue))
        {
            return 1;
        }else if(DIRECT.equals(stringValue)) {
            return 2;
        }else{
            return -1;
        }
    }

    @Override
    public CellData convertToExcelData(Integer integer, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }
}
