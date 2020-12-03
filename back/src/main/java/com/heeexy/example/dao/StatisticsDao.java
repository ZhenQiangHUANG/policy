package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface StatisticsDao {
    
    /** 
     * @description: 每年所有欢乐谷各类别的政策数量
     * @param:  * @param requestJson 
     * @return: java.util.List<com.alibaba.fastjson.JSONObject> 
     * @author Huangzhenqiang
     * @date: 2020/11/16 17:08
     */
    List<JSONObject> getGroupByCategoryPolicyNumPie(JSONObject requestJson);

    /** 
     * @description: 每年所有类别各欢乐谷的政策数量
     * @param:  * @param requestJson 
     * @return: java.util.List<com.alibaba.fastjson.JSONObject> 
     * @author Huangzhenqiang
     * @date: 2020/11/25 16:15
     */
    List<JSONObject> getGroupByDeptPolicyNumPie(JSONObject requestJson);

    /** 
     * @description: 每年所有欢乐谷各类别政策创收金额占比
     * @param:  * @param requestJson 
     * @return: java.util.List<com.alibaba.fastjson.JSONObject> 
     * @author Huangzhenqiang
     * @date: 2020/11/16 17:48
     */
    List<JSONObject> getGroupByCategoryPolicyRevenuePie(JSONObject requestJson);

    /** 
     * @description: 每年所有类别各欢乐谷政策创收金额占比 
     * @param:  * @param requestJson 
     * @return: java.util.List<com.alibaba.fastjson.JSONObject> 
     * @author Huangzhenqiang
     * @date: 2020/11/25 17:24
     */
    List<JSONObject> getGroupByDeptPolicyRevenuePie(JSONObject requestJson);

    /** 
     * @description: 图3(折线图),各欢乐谷一年内各月份发布政策数量对比
     * @param:  * @param requestJson 
     * @return: java.util.List<com.alibaba.fastjson.JSONObject> 
     * @author Huangzhenqiang
     * @date: 2020/11/17 11:06
     */
    List<JSONObject> getCountByMonthPolicyNumLine(JSONObject requestJson);

    /** 
     * @description: 图4(折线图),每年所有欢乐谷各月份的政策创收 
     * @param:  * @param requestJson 
     * @return: java.util.List<com.alibaba.fastjson.JSONObject> 
     * @author Huangzhenqiang
     * @date: 2020/11/17 15:22
     */
    List<JSONObject> getCountByMonthPolicyRevenueLine(JSONObject requestJson);
    
    /** 
     * @description: 图5(折线图),所有欢乐谷每年的政策数量
     * @param:  * @param requestJson 
     * @return: java.util.List<com.alibaba.fastjson.JSONObject> 
     * @author Huangzhenqiang
     * @date: 2020/11/23 9:54
     */
    List<JSONObject> getCountByYearPolicyNumLine(JSONObject requestJson);

    /** 
     * @description: 图6(折线图),各欢乐谷一年发布政策的创收对比 
     * @param:  * @param requestJson 
     * @return: java.util.List<com.alibaba.fastjson.JSONObject> 
     * @author Huangzhenqiang
     * @date: 2020/11/20 16:02
     */
    List<JSONObject> getCountByYearPolicyRevenueLine(JSONObject requestJson);

   
}
