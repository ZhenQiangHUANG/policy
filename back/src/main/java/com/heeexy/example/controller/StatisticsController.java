package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.StatisticsService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName StatisticsController
 * @Description TODO
 * @Author Huangzhenqiang
 * @Date 2020/11/6 11:43
 * @Version 1.0
 **/
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;


    /** 
     * @description: 每年所有欢乐谷各类别的政策数量
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/16 16:53
     */
    @RequiresPermissions("statistics:list")
    @PostMapping("/getGroupByCategoryPolicyNumPie")
    public JSONObject getGroupByCategoryPolicyNumPie(@RequestBody JSONObject requestJson) {
        return statisticsService.getGroupByCategoryPolicyNumPie(requestJson);
    }

    /** 
     * @description: 每年所有类别各欢乐谷的政策数量
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/25 16:14
     */
    @RequiresPermissions("statistics:list")
    @PostMapping("/getGroupByDeptPolicyNumPie")
    public JSONObject getGroupByDeptPolicyNumPie(@RequestBody JSONObject requestJson) {
        return statisticsService.getGroupByDeptPolicyNumPie(requestJson);
    }

    /** 
     * @description: 每年所有欢乐谷各类别政策创收金额占比
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/16 17:45
     */
    @RequiresPermissions("statistics:list")
    @PostMapping("/getGroupByCategoryPolicyRevenuePie")
    public JSONObject getGroupByCategoryPolicyRevenuePie(@RequestBody JSONObject requestJson) {
        return statisticsService.getGroupByCategoryPolicyRevenuePie(requestJson);
    }

    /** 
     * @description: 每年所有类别各欢乐谷政策创收金额占比 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/25 17:24
     */
    @RequiresPermissions("statistics:list")
    @PostMapping("/getGroupByDeptPolicyRevenuePie")
    public JSONObject getGroupByDeptPolicyRevenuePie(@RequestBody JSONObject requestJson) {
        return statisticsService.getGroupByDeptPolicyRevenuePie(requestJson);
    }


    /** 
     * @description: 图3(折线图),各欢乐谷一年内各月份发布政策数量对比 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/17 11:06
     */
    @RequiresPermissions("statistics:list")
    @PostMapping("/getCountByMonthPolicyNumLine")
    public JSONObject getCountByMonthPolicyNumLine(@RequestBody JSONObject requestJson) {
        return statisticsService.getCountByMonthPolicyNumLine(requestJson);
    }

    /** 
     * @description: 图4(折线图),每年所有欢乐谷各月份的政策创收 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/17 17:17
     */
    @RequiresPermissions("statistics:list")
    @PostMapping("/getCountByMonthPolicyRevenueLine")
    public JSONObject getCountByMonthPolicyRevenueLine(@RequestBody JSONObject requestJson) {
        return statisticsService.getCountByMonthPolicyRevenueLine(requestJson);
    }

    /** 
     * @description: 图5(折线图),所有欢乐谷每年的政策数量
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/20 17:11
     */
    @RequiresPermissions("statistics:list")
    @PostMapping("/getCountByYearPolicyNumLine")
    public JSONObject getCountByYearPolicyNumLine(@RequestBody JSONObject requestJson) {
        return statisticsService.getCountByYearPolicyNumLine(requestJson);
    }

    /**
     * @description: 图6(折线图),各欢乐谷一年发布政策的创收对比
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/20 15:35
     */
    @RequiresPermissions("statistics:list")
    @PostMapping("/getCountByYearPolicyRevenueLine")
    public JSONObject getCountByYearPolicyRevenueLine(@RequestBody JSONObject requestJson) {
        return statisticsService.getCountByYearPolicyRevenueLine(requestJson);
    }

}
