package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.PolicyService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName PolicyController
 * @Description 政策相关Controller
 * @Author Huangzhenqiang
 * @Date 2020/10/28 10:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    /**
     * 查询列表
     */
    @RequiresPermissions("policy:list")
    @GetMapping("/listPolicy")
    public JSONObject listPolicy(HttpServletRequest request){

        return policyService.listPolicy(CommonUtil.request2Json(request));

    }

    /** 
     * @description: 根据id查询对应的政策利用条款 
     * @param:  * @param policyId 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/4 14:29
     */
    @RequiresPermissions("policy:list")
    @PostMapping("/listPolicyConditionById")
    public JSONObject listPolicyConditionById(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "policyId");
        return policyService.listPolicyConditionById(requestJson);

    }

    /** 
     * @description: 根据id查询对应的政策利用条款的应对措施
     * @param:  * @param policyId 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/4 14:29
     */
    @RequiresPermissions("policy:list")
    @PostMapping("/listPolicyMeasureById")
    public JSONObject listPolicyMeasureById(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "policyId");
        return policyService.listPolicyMeasureById(requestJson);

    }

    /**
     * @description: 根据id查询对应的政策批注信息
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/18 11:06
     */
    @RequiresPermissions("policy:list")
    @PostMapping("/listPolicyAnnotationById")
    public JSONObject listPolicyAnnotationById(@RequestBody JSONObject requestJson){
        CommonUtil.hasAllRequired(requestJson, "policyId");
        return policyService.listPolicyAnnotationById(requestJson);

    }

    /**
     * 新增政策
     */
    @RequiresPermissions("policy:add")
    @PostMapping("/addPolicy")
    public JSONObject addPolicy(@RequestBody JSONObject requestJson) {
        //验证是否含有全部必填字段
        CommonUtil.hasAllRequired(requestJson, "policyTitle");
        return policyService.addPolicy(requestJson);
    }

    /**
     * @description: 修改政策基本信息
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/2 11:44
     */
    @RequiresPermissions("policy:update")
    @PostMapping("/updatePolicy")
    public  JSONObject updatePolicy(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "id,policyTitle,policyUrl,policyId,category,policyLevel,policyResume,releaseChannel,governmentBranch,releaseTime,collectTime,collector,revenueWay,revenue,assist,department,acceptor,accpetorPhone,schedule");
        return policyService.updatePolicy(requestJson);
    }

    /** 
     * @description: 添加政策对应的可利用条款
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/2 15:58
     */
    @RequiresPermissions("policy:add")
    @PostMapping("/addPolicyCondition")
    public  JSONObject addPolicyCondition(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "policyId,condition");
        return policyService.addPolicyCondition(requestJson);
    }

    /**
     * @description: 修改政策对应的可利用条款
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/2 16:32
     */
    @RequiresPermissions("policy:update")
    @PostMapping("/updatePolicyCondition")
    public  JSONObject updatePolicyCondition(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "id,conditions");
        return policyService.updatePolicyCondition(requestJson);
    }

    /**
     * @description: 添加政策对应的可利用条款的应对措施
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/2 15:58
     */
    @RequiresPermissions("policy:add")
    @PostMapping("/addPolicyMeasure")
    public  JSONObject addPolicyMeasure(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "policyId,measure");
        return policyService.addPolicyMeasure(requestJson);
    }

    /**
     * @description: 修改政策对应的可利用条款的应对措施
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/2 16:32
     */
    @RequiresPermissions("policy:update")
    @PostMapping("/updatePolicyMeasure")
    public  JSONObject updatePolicyMeasure(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "id,measure");
        return policyService.updatePolicyMeasure(requestJson);
    }

    /** 
     * @description: 添加政策的批注信息
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/18 11:27
     */
    @RequiresPermissions("policy:annotate")
    @PostMapping("/addPolicyAnnotation")
    public  JSONObject addPolicyAnnotation(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "policyId,annotation");
        return policyService.addPolicyAnnotation(requestJson);
    }

    /** 
     * @description: 修改政策的批注信息
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/18 11:53
     */
    @RequiresPermissions("policy:annotate")
    @PostMapping("/updatePolicyAnnotation")
    public  JSONObject updatePolicyAnnotation(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "id,annotation");
        return policyService.updatePolicyAnnotation(requestJson);
    }

    /**
     * @description: 删除政策对应的可利用条款
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/3 9:27
     */
    @RequiresPermissions("policy:update")
    @PostMapping("/deletePolicyCondition")
    public JSONObject deletePolicyCondition(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "deleteId");
        return policyService.deletePolicyCondition(requestJson);
    }

    /**
     * @description: 删除政策对应的可移动条款的应对措施
     * @param:  * @param requestJson
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/3 9:27
     */
    @RequiresPermissions("policy:update")
    @PostMapping("/deletePolicyMeasure")
    public JSONObject deletePolicyMeasure(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "deleteId");
        return policyService.deletePolicyMeasure(requestJson);
    }

    /** 
     * @description: 删除政策记录以及其下所有的可利用条款以及应对措施 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/3 16:03
     */
    @RequiresPermissions("policy:update")
    @PostMapping("/deletePolicy")
    public JSONObject deletePolicy(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "id");
        return policyService.deletePolicy(requestJson);
    }

    /** 
     * @description: 查询当前用户未查看的批注数量
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/18 16:41
     */
    @RequiresPermissions("policy:list")
    @PostMapping("/getNewAnnotationsNum")
    public JSONObject getNewAnnotationsNum(@RequestBody JSONObject requestJson) {
        return policyService.getNewAnnotationsNum(requestJson);
    }

    /** 
     * @description: 获取批注列表的数据 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/19 11:05
     */
    @RequiresPermissions("policy:list")
    @PostMapping("/getAnnotationTableData")
    public JSONObject getAnnotationTableData(@RequestBody JSONObject requestJson) {
        return policyService.getAnnotationTableData(requestJson);
    }

}
