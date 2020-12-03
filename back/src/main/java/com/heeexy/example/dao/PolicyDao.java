package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.pojo.PolicyConditionData;
import com.heeexy.example.pojo.PolicyData;
import com.heeexy.example.pojo.PolicyMeasureData;

import java.util.List;

/**
 * @description: TODO
 * @author Huangzhenqiang
 * @date 2020/10/28 10:44
 * @version 1.0
 */
public interface PolicyDao {
    /**
     * 统计政策总数
     */
    int countPolicy(JSONObject jsonObject);

    /**
     * 政策列表
     */
    List<JSONObject> listPolicy(JSONObject jsonObject);

    /**
     * 根据id查询对应的政策利用条款
     */
    List<JSONObject> listPolicyConditionById(JSONObject requestJson);

    /**
     * 根据id查询对应的政策利用条款的应对措施
     */
    List<JSONObject> listPolicyMeasureById(JSONObject requestJson);

    /**
     * @description:  根据id查询对应的政策批注信息
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/18 11:10
     */
    JSONObject listPolicyAnnotationById(JSONObject requestJson);

    /** 
     * @description: 统计某个省份的欢乐谷今天已上传政策记录数，用于生成政策的编号 
     * @param:  * @param  
     * @return: int 
     * @author Huangzhenqiang
     * @date: 2020/11/2 9:33
     */
    int countPolicyCityToday(int city,String now);

    /** 
     * @description: 添加政策的基础信息 
     * @param:  * @param requestJson 
     * @return: void 
     * @author Huangzhenqiang
     * @date: 2020/11/2 10:24
     */
    void addPolicyBase(JSONObject requestJson);

    /** 
     * @description: 更新政策的基本信息
     * @param:  * @param requestJson 
     * @return: void 
     * @author Huangzhenqiang
     * @date: 2020/11/2 11:51
     */
    void updatePolicyBase(JSONObject requestJson);

    /** 
     * @description: 添加政策的可利用条件 
     * @param:  * @param requestJson 
     * @return: void 
     * @author Huangzhenqiang
     * @date: 2020/11/2 16:11
     */
    void addPolicyCondition(JSONObject requestJson);

    /** 
     * @description: 修改政策对应的可利用条款
     * @param:  * @param requestJson 
     * @return: void 
     * @author Huangzhenqiang
     * @date: 2020/11/2 16:34
     */
    void updatePolicyCondition(JSONObject requestJson);

    /**
     * @description:  添加政策对应的可利用条款的应对措施
     * @param:  * @param requestJson
     * @return: void
     * @author Huangzhenqiang
     * @date: 2020/11/2 17:00
     */
    void addPolicyMeasure(JSONObject requestJson);

    /**
     * @description: 修改政策对应的可利用条款的应对措施
     * @param:  * @param requestJson
     * @return: void
     * @author Huangzhenqiang
     * @date: 2020/11/2 17:00
     */
    void updatePolicyMeasure(JSONObject requestJson);

    /** 
     * @description: 添加政策的批注信息 
     * @param:  * @param requestJson 
     * @return: void 
     * @author Huangzhenqiang
     * @date: 2020/11/18 11:30
     */
    void addPolicyAnnotation(JSONObject requestJson);

    /** 
     * @description:  修改政策的批注信息
     * @param:  * @param requestJson 
     * @return: void 
     * @author Huangzhenqiang
     * @date: 2020/11/18 11:37
     */
    void updatePolicyAnnotation(JSONObject requestJson);

    /**
     * @description:  删除政策对应的可利用条款
     * @param:  * @param requestJson
     * @return: void
     * @author Huangzhenqiang
     * @date: 2020/11/3 9:30
     */
    void deletePolicyCondition(JSONObject requestJson);

    /**
     * @description:  删除政策对应的可利用条款的应对措施
     * @param:  * @param requestJson
     * @return: void
     * @author Huangzhenqiang
     * @date: 2020/11/3 9:30
     */
    void deletePolicyMeasure(JSONObject requestJson);

    /** 
     * @description: 根据政策的policy_id删除其对应的可利用条款 
     * @param:  * @param requestJson 
     * @return: void 
     * @author Huangzhenqiang
     * @date: 2020/11/3 10:18
     */
    void deletePolicyConditionByPolicyId(JSONObject requestJson);

    /** 
     * @description: 根据政策的policy_id删除其对应的可利用条款的应对措施 
     * @param:  * @param requestJson 
     * @return: void 
     * @author Huangzhenqiang
     * @date: 2020/11/3 10:18
     */
    void deletePolicyMeasureByPolicyId(JSONObject requestJson);

    /** 
     * @description: 根据政策的policy_id删除其对应的批注信息
     * @param:  * @param requestJson 
     * @return: void 
     * @author Huangzhenqiang
     * @date: 2020/12/1 11:15
     */
    void deletePolicyAnnotationByPolicyId(JSONObject requestJson);

    /** 
     * @description: 删除政策记录 
     * @param:  * @param requestJson 
     * @return: void 
     * @author Huangzhenqiang
     * @date: 2020/11/3 16:03
     */
    void deletePolicy(JSONObject requestJson);

    /**
     * @description: 批量添加政策记录
     * @param:  * @param list
     * @return: void
     * @author Huangzhenqiang
     * @date: 2020/11/9 15:34
     */
    void addPolicyList(List<PolicyData> list);

    /**
     * @description:  批量添加政策的利用条款
     * @param:  * @param conditions
     * @return: void
     * @author Huangzhenqiang
     * @date: 2020/11/9 16:03
     */
    void addPolicyConditionList(List<PolicyConditionData> conditions);

    /**
     * @description:  批量添加政策的利用条款的应对措施
     * @param:  * @param measures
     * @return: void
     * @author Huangzhenqiang
     * @date: 2020/11/9 16:03
     */
    void addPolicyMeasureList(List<PolicyMeasureData> measures);

    /** 
     * @description: 读取要写入到Excel表格的数据
     * @param:  * @param  
     * @return: void 
     * @author Huangzhenqiang
     * @date: 2020/11/10 15:35
     */
    List<JSONObject> exportPolicy(JSONObject requestJson);

    /**
     * @description: 获取数据库中所有的可利用条件
     * @param:  * @param
     * @return: java.util.List<com.heeexy.example.pojo.PolicyConditionData>
     * @author Huangzhenqiang
     * @date: 2020/11/10 15:51
     */
    List<JSONObject> exportPolicyCondition(JSONObject requestJson);

    /**
     * @description: 获取数据库中所有的应对措施
     * @param:  * @param
     * @return: java.util.List<com.heeexy.example.pojo.PolicyMeasureData>
     * @author Huangzhenqiang
     * @date: 2020/11/10 15:51
     */
    List<JSONObject> exportPolicyMeasure(JSONObject requestJson);

    /** 
     * @description: 查询当前用户未查看的批注数量 
     * @param:  * @param user 
     * @return: JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/18 16:47
     */
    JSONObject getNewAnnotationsNum(JSONObject user);

    /** 
     * @description: 获取批注列表的数据
     * @param:  * @param requestJson 
     * @return: java.util.List<com.alibaba.fastjson.JSONObject> 
     * @author Huangzhenqiang
     * @date: 2020/11/19 10:26
     */
    List<JSONObject> getAnnotationTableData(JSONObject requestJson);
    
}
