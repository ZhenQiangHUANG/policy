package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PolicyDao;
import com.heeexy.example.dao.UserDao;
import com.heeexy.example.service.PolicyService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName PolicyServiceImpl
 * @Description TODO
 * @Author Huangzhenqiang
 * @Date 2020/10/28 10:40
 * @Version 1.0
 **/
@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyDao policyDao;

    @Autowired
    private UserDao userDao;

    @Override
    public JSONObject listPolicy(JSONObject jsonObject) {

        CommonUtil.fillPageParam(jsonObject);
        CommonUtil.processGetPolicyParam(jsonObject);
        int count = policyDao.countPolicy(jsonObject);
        List<JSONObject> list = policyDao.listPolicy(jsonObject);

        return CommonUtil.successPage(jsonObject, list, count);
    }

    @Override
    public JSONObject listPolicyConditionById(JSONObject requestJson) {

        List<JSONObject> list = policyDao.listPolicyConditionById(requestJson);

        return CommonUtil.successPage(list);
    }

    @Override
    public JSONObject listPolicyMeasureById(JSONObject requestJson) {

        List<JSONObject> list = policyDao.listPolicyMeasureById(requestJson);
        return CommonUtil.successPage(list);
    }

    @Override
    public JSONObject listPolicyAnnotationById(JSONObject requestJson) {
        JSONObject result = policyDao.listPolicyAnnotationById(requestJson);
        return CommonUtil.successPage(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addPolicy(JSONObject requestJson) {

        String policyId = CommonUtil.getPolicyId(requestJson.getInteger("dept"),policyDao);
        requestJson.put("policyId",policyId);

        //获取用户信息
        JSONObject user = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        requestJson.put("creator",user.getString("nickName"));
        policyDao.addPolicyBase(requestJson);
        return CommonUtil.successJson();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updatePolicy(JSONObject requestJson) {

        //获取用户信息
        JSONObject user = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        requestJson.put("modifier",user.getString("nickName"));

        policyDao.updatePolicyBase(requestJson);

        return CommonUtil.successJson();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addPolicyCondition(JSONObject requestJson) {

        //获取用户信息
        JSONObject user = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        requestJson.put("creator",user.getString("nickName"));

        policyDao.addPolicyCondition(requestJson);
        return CommonUtil.successJson();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updatePolicyCondition(JSONObject requestJson) {

        //获取用户信息
        JSONObject user = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        requestJson.put("modifier",user.getString("nickName"));

        policyDao.updatePolicyCondition(requestJson);
        return CommonUtil.successJson();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addPolicyMeasure(JSONObject requestJson) {

        //获取用户信息
        JSONObject user = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        requestJson.put("creator",user.getString("nickName"));

        policyDao.addPolicyMeasure(requestJson);
        return CommonUtil.successJson();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updatePolicyMeasure(JSONObject requestJson) {

        //获取用户信息
        JSONObject user = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        requestJson.put("modifier",user.getString("nickName"));

        policyDao.updatePolicyMeasure(requestJson);
        return CommonUtil.successJson();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject addPolicyAnnotation(JSONObject requestJson) {
        //获取用户信息
        JSONObject user = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        requestJson.put("creator",user.getString("nickName"));

        policyDao.addPolicyAnnotation(requestJson);
        return CommonUtil.successJson();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updatePolicyAnnotation(JSONObject requestJson) {
        //获取用户信息
        JSONObject user = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        requestJson.put("modifier",user.getString("nickName"));

        policyDao.updatePolicyAnnotation(requestJson);
        return CommonUtil.successJson();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject deletePolicyCondition(JSONObject requestJson) {

        policyDao.deletePolicyCondition(requestJson);
        return CommonUtil.successJson();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject deletePolicyMeasure(JSONObject requestJson) {

        policyDao.deletePolicyMeasure(requestJson);
        return CommonUtil.successJson();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject deletePolicy(JSONObject requestJson) {

        policyDao.deletePolicyMeasureByPolicyId(requestJson);
        policyDao.deletePolicyConditionByPolicyId(requestJson);
        policyDao.deletePolicyAnnotationByPolicyId(requestJson);
        policyDao.deletePolicy(requestJson);
        return CommonUtil.successJson();

    }

    @Override
    public JSONObject getNewAnnotationsNum(JSONObject requestJson) {
        //查询当前用户上一次查看批注的时间
        JSONObject time = userDao.getLastCheckAnnotationTime(requestJson);
        JSONObject num = policyDao.getNewAnnotationsNum(time);
        return CommonUtil.successPage(num);
    }

    @Override
    public JSONObject getAnnotationTableData(JSONObject requestJson) {
        String num = requestJson.getString("unCheckedNum");
        JSONObject result = new JSONObject();
        if(null == num || num.equals(""))
        {
            //获取用户信息
            JSONObject user = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
            requestJson.put("username",user.getString("nickName"));
            JSONObject time = userDao.getLastCheckAnnotationTime(requestJson);
            JSONObject unCheckedNum = policyDao.getNewAnnotationsNum(time);
            result.put("unCheckedNum",unCheckedNum.getString("newAnnotationsNum"));
        }
        CommonUtil.fillPageParam(requestJson);
        List<JSONObject> tableData = policyDao.getAnnotationTableData(requestJson);
        result.put("annotationTable",tableData);
        //获取用户信息
        JSONObject user = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        userDao.updateLastCheckAnnotationTime(user);
        return  CommonUtil.successPage(result);
    }

}
