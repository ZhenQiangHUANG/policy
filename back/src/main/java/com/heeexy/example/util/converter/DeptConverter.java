package com.heeexy.example.util.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * @ClassName DeptConverter 将所属欢乐谷转换成对应的dept
 * @Description TODO
 * @Author Huangzhenqiang
 * @Date 2020/11/9 17:44
 * @Version 1.0
 **/
public class DeptConverter implements Converter<Integer> {

    /*  本部-0 深圳-1 北京-2  成都-3 上海-4 武汉-5 天津-6 重庆-7 南京-8  */
    private static final String BENBU     = "本部";
    private static final String SHENZHEN  = "深圳";
    private static final String BEIJING   = "北京";
    private static final String CHENGDU   = "成都";
    private static final String SHANGHAI  = "上海";
    private static final String WUHAN     = "武汉";
    private static final String TIANJIN   = "天津";
    private static final String CHONGQING = "重庆";
    private static final String NANJING   = "南京";


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
        if (BENBU.equals(stringValue)){
            return 0;
        }else if(SHENZHEN.equals(stringValue)) {
            return 1;
        }else if(BEIJING.equals(stringValue)) {
            return 2;
        }else if(CHENGDU.equals(stringValue)) {
            return 3;
        }else if(SHANGHAI.equals(stringValue)) {
            return 4;
        }else if(WUHAN.equals(stringValue)) {
            return 5;
        }else if(TIANJIN.equals(stringValue)) {
            return 6;
        }else if(CHONGQING.equals(stringValue)) {
            return 7;
        }else if(NANJING.equals(stringValue))
        {
            return 8;
        }else {
            return -1;
        }
    }

    @Override
    public CellData convertToExcelData(Integer integer, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }
}
