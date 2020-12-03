package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

public interface StatisticsService {

    /**
     * @description: 每年所有欢乐谷各类别的政策数量
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/16 16:47
     */
    JSONObject getGroupByCategoryPolicyNumPie(JSONObject requestJson);

    /** 
     * @description: 每年所有类别各欢乐谷的政策数量
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/25 16:14
     */
    JSONObject getGroupByDeptPolicyNumPie(JSONObject requestJson);

    /**
     * @description: 每年所有欢乐谷各类别政策创收金额占比
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/16 17:45
     */
    JSONObject getGroupByCategoryPolicyRevenuePie(JSONObject requestJson);

    /**
     * @description:  每年所有类别各欢乐谷政策创收金额占比
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/25 17:19
     */
    JSONObject getGroupByDeptPolicyRevenuePie(JSONObject requestJson);

    /** 
     * @description: 图3(折线图),各欢乐谷一年内各月份发布政策数量对比
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/17 10:55
     */
    JSONObject getCountByMonthPolicyNumLine(JSONObject requestJson);

    /** 
     * @description: 图4(折线图),每年所有欢乐谷各月份的政策创收
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/17 17:17
     */
    JSONObject getCountByMonthPolicyRevenueLine(JSONObject requestJson);

    /**
     * @description: 图5(折线图),所有欢乐谷每年的政策数量
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/20 17:11
     */
    JSONObject getCountByYearPolicyNumLine(JSONObject requestJson);

    /** 
     * @description: 图6(折线图),各欢乐谷一年发布政策的创收对比 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/20 16:39
     */
    JSONObject getCountByYearPolicyRevenueLine(JSONObject requestJson);

}
