package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @description: TODO
 * @author Huangzhenqiang
 * @date 2020/10/28 10:38
 * @version 1.0
 */
public interface PolicyService {

    /**
     * 政策列表
     */
    JSONObject listPolicy(JSONObject jsonObject);

    /**
     * @description:  根据id查询对应的政策利用条款
     * @param:  * @param policyId
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/4 14:33
     */
    JSONObject listPolicyConditionById(JSONObject requestJson);

    /**
     * @description: 根据id查询对应的政策利用条款的应对措施
     * @param:  * @param policyId
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/4 14:33
     */
    JSONObject listPolicyMeasureById(JSONObject requestJson);

    /**
     * 新增政策
     */
    JSONObject addPolicy(JSONObject requestJson);

    /** 
     * @description: 修改政策的基本信息 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/2 11:45
     */
    JSONObject updatePolicy(JSONObject requestJson);

    /**
     * @description: 添加政策的可利用条件
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/2 15:59
     */
    JSONObject addPolicyCondition(JSONObject requestJson);

    /** 
     * @description: 修改政策对应的可利用条款 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/2 16:33
     */
    JSONObject updatePolicyCondition(JSONObject requestJson);

    /** 
     * @description: 添加政策对应的可利用条款的应对措施 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/2 16:59
     */
    JSONObject addPolicyMeasure(JSONObject requestJson);

    /** 
     * @description: 修改政策对应的可利用条款的应对措施 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/3 9:24
     */
    JSONObject updatePolicyMeasure(JSONObject requestJson);

    /** 
     * @description: 添加政策的批注信息
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/18 11:29
     */
    JSONObject addPolicyAnnotation(JSONObject requestJson);

    /**
     * @description: 修改政策的批注信息
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/18 11:35
     */
    JSONObject updatePolicyAnnotation(JSONObject requestJson);

    /** 
     * @description: 删除政策对应的可利用条款
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/3 9:28
     */
    JSONObject deletePolicyCondition(JSONObject requestJson);

    /** 
     * @description: 删除政策对应的可移动条款的应对措施
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/3 9:31
     */
    JSONObject deletePolicyMeasure(JSONObject requestJson);

    /** 
     * @description: 删除政策记录以及其下所有的可利用条款以及应对措施
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/3 16:03
     */
    JSONObject deletePolicy(JSONObject requestJson);

    /** 
     * @description: 根据id查询对应的政策批注信息 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/18 11:05
     */
    JSONObject listPolicyAnnotationById(JSONObject requestJson);

    /** 
     * @description: 查询当前用户未查看的批注数量 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/19 10:16
     */
    JSONObject getNewAnnotationsNum(JSONObject requestJson);

    /** 
     * @description: 获取批注列表的数据 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/20 15:22
     */
    JSONObject getAnnotationTableData(JSONObject requestJson);
}
