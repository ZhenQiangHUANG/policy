package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.StatisticsDao;
import com.heeexy.example.service.StatisticsService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StatisticsServiceImpl
 * @Description TODO
 * @Author Huangzhenqiang
 * @Date 2020/11/6 11:45
 * @Version 1.0
 **/
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;

    @Override
    public JSONObject getGroupByCategoryPolicyNumPie(JSONObject requestJson) {
        CommonUtil.processParamNull(requestJson,"dept");
        CommonUtil.processParamNull(requestJson,"schedule");
        CommonUtil.processParamNull(requestJson,"collectTime");

        List<JSONObject> list = statisticsDao.getGroupByCategoryPolicyNumPie(requestJson);
        JSONObject result = new JSONObject();
        result.put("groupByCategoryPolicyNumPieData",list);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject getGroupByDeptPolicyNumPie(JSONObject requestJson) {
        CommonUtil.processParamNull(requestJson,"category");
        CommonUtil.processParamNull(requestJson,"schedule");
        CommonUtil.processParamNull(requestJson,"collectTime");

        List<JSONObject> list = statisticsDao.getGroupByDeptPolicyNumPie(requestJson);
        JSONObject result = new JSONObject();
        result.put("groupByDeptPolicyNumPieData",list);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject getGroupByCategoryPolicyRevenuePie(JSONObject requestJson) {
        CommonUtil.processParamNull(requestJson,"dept");
        CommonUtil.processParamNull(requestJson,"schedule");
        CommonUtil.processParamNull(requestJson,"collectTime");
        List<JSONObject> list = statisticsDao.getGroupByCategoryPolicyRevenuePie(requestJson);
        JSONObject result = new JSONObject();
        result.put("groupByCategoryPolicyRevenuePieData",list);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject getGroupByDeptPolicyRevenuePie(JSONObject requestJson) {
        CommonUtil.processParamNull(requestJson,"category");
        CommonUtil.processParamNull(requestJson,"schedule");
        CommonUtil.processParamNull(requestJson,"collectTime");

        List<JSONObject> list = statisticsDao.getGroupByDeptPolicyRevenuePie(requestJson);
        JSONObject result = new JSONObject();
        result.put("groupByDeptPolicyRevenuePieData",list);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject getCountByMonthPolicyNumLine(JSONObject requestJson) {
        CommonUtil.processParamNull(requestJson,"category");
        CommonUtil.processParamNull(requestJson,"schedule");
        CommonUtil.processParamNull(requestJson,"collectTime");
        List<JSONObject> list = statisticsDao.getCountByMonthPolicyNumLine(requestJson);
        List<JSONObject> newList = CommonUtil.getLineChartMonthData(list,"发布量");

        JSONObject result = new JSONObject();
        result.put("countByMonthPolicyNumLine",newList);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject getCountByMonthPolicyRevenueLine(JSONObject requestJson) {
        CommonUtil.processParamNull(requestJson,"category");
        CommonUtil.processParamNull(requestJson,"schedule");
        CommonUtil.processParamNull(requestJson,"collectTime");
        List<JSONObject> list = statisticsDao.getCountByMonthPolicyRevenueLine(requestJson);
        List<JSONObject> newList = CommonUtil.getLineChartMonthData(list,"创收金额");

        JSONObject result = new JSONObject();
        result.put("countByMonthPolicyRevenueLine",newList);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject getCountByYearPolicyNumLine(JSONObject requestJson) {
        CommonUtil.processParamNull(requestJson,"category");
        CommonUtil.processParamNull(requestJson,"schedule");

        List<JSONObject> list = statisticsDao.getCountByYearPolicyNumLine(requestJson);
        List<JSONObject> newList = CommonUtil.getLineChartYearData(requestJson,list,"发布量","num");
        JSONObject result = new JSONObject();
        result.put("countByYearPolicyNumLine",newList);
        return CommonUtil.successJson(result);
    }

    @Override
    public JSONObject getCountByYearPolicyRevenueLine(JSONObject requestJson) {
        CommonUtil.processParamNull(requestJson,"category");
        CommonUtil.processParamNull(requestJson,"schedule");

        List<JSONObject> list = statisticsDao.getCountByYearPolicyRevenueLine(requestJson);
        List<JSONObject> newList = CommonUtil.getLineChartYearData(requestJson,list,"创收金额","revenue");
        JSONObject result = new JSONObject();
        result.put("countByYearPolicyRevenueLine",newList);
        return CommonUtil.successJson(result);
    }


}
