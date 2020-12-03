package com.heeexy.example.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PolicyDao;
import com.heeexy.example.pojo.PolicyConditionData;
import com.heeexy.example.pojo.PolicyData;
import com.heeexy.example.pojo.PolicyMeasureData;
import com.heeexy.example.service.EasyExcelService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.EasyExcelListener;
import com.heeexy.example.util.constants.Constants;
import com.heeexy.example.util.constants.ErrorEnum;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EasyExcelImpl
 * @Description TODO
 * @Author Huangzhenqiang
 * @Date 2020/11/9 10:43
 * @Version 1.0
 **/
@Service
public class EasyExcelImpl implements EasyExcelService {

    @Autowired
    private PolicyDao policyDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject importSubjectData(MultipartFile file) {
        try {
            //1 获取文件输入流
            InputStream inputStream = file.getInputStream();

            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, PolicyData.class, new EasyExcelListener(this)).sheet().doRead();
        }catch(Exception e) {
            e.printStackTrace();
            return CommonUtil.errorJson(ErrorEnum.E_30001);
        }
        return CommonUtil.successJson();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPolicyList(List<PolicyData> list) {
        if(list.size() <= 0)
        {
            return;
        }
        //获取用户信息
        JSONObject user = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        String creator = user.getString("nickName");
        int[] num = new int[9];
        for(PolicyData p : list)
        {
            int dept = p.getDept();
            if(num[dept] == 0)
            {
                num[dept] = CommonUtil.getPolicyCount(dept,policyDao);
            }
            else
            {
                num[dept]++;
            }
            p.setPolicyId(CommonUtil.getPolicyId(p.getDept(),num[dept],policyDao));
            p.setCreator(creator);
        }

        policyDao.addPolicyList(list);

        List<PolicyConditionData> conditions = new ArrayList<>();
        List<PolicyMeasureData> measures = new ArrayList<>();

        for(PolicyData p : list)
        {
            conditions.clear();
            measures.clear();
            String condition = p.getCondition();
            String measure = p.getMeasure();
            String id= p.getId();
            if( null != condition && !"".equals(condition))
            {
                String[] c_item = condition.split("&&");
                for(String c : c_item)
                {
                    PolicyConditionData pc = new PolicyConditionData(id,c,creator);
                    conditions.add(pc);
                }
                policyDao.addPolicyConditionList(conditions);
            }

            if( null != measure && !"".equals(measure))
            {
                String[] m_item = measure.split("&&");
                for(String m : m_item)
                {
                    PolicyMeasureData pm = new PolicyMeasureData(id,m,creator);
                    measures.add(pm);
                }
                policyDao.addPolicyMeasureList(measures);
            }
        }

    }

    @Override
    public JSONObject exportPolicy(JSONObject requestJson) {

        CommonUtil.processParamNull(requestJson,"dept");
        CommonUtil.processParamNull(requestJson,"policyLevel");
        CommonUtil.processParamNull(requestJson,"minRevenue");
        CommonUtil.processParamNull(requestJson,"maxRevenue");
        CommonUtil.processParamLike(requestJson,"category");
        CommonUtil.processParamLike(requestJson,"policyTitle");
        CommonUtil.processParamLike(requestJson,"schedule");

        List<JSONObject> list = policyDao.exportPolicy(requestJson);
        if(requestJson.getString("ids") != null)
        {
            StringBuilder policyIds = new StringBuilder();
            policyIds.append("( ");
            for(int i = 0; i < list.size(); i++)
            {
                if( i != 0)
                {
                    policyIds.append(" , ");
                }
                policyIds.append(list.get(i).getString("id"));
            }
            policyIds.append(" ) ");
            requestJson.put("policyIds",policyIds.toString());
        }
        List<JSONObject> conditionList = policyDao.exportPolicyCondition(requestJson);
        List<JSONObject> measureList = policyDao.exportPolicyMeasure(requestJson);

        StringBuilder condition = new StringBuilder();
        StringBuilder measure = new StringBuilder();
        for(JSONObject pd : list)
        {
            String id = pd.getString("id");
            condition.setLength(0);
            int index = 0;
            int startIndex = -1;
            for(int i = 0; i < conditionList.size(); i++)
            {
                if(conditionList.get(i).getString("policyId").equals(id)) {
                    if(index == 0)
                    {
                        startIndex = i;
                    }
                    index++;
                    condition.append(index);
                    condition.append(". ");
                    condition.append(conditionList.get(i).getString("conditions"));
                    condition.append(" ");
                }
                else if(index != 0)
                {
                    break;
                }
            }
            pd.put("condition",condition.toString());
            //注意remove之后下一个的下表又变为了startIndex
            for(int i = 0; i < index; i++)
            {
                conditionList.remove(startIndex);
            }

            index = 0;
            startIndex = -1;
            measure.setLength(0);
            for(int i = 0; i < measureList.size(); i++)
            {
                if(measureList.get(i).getString("policyId").equals(id)) {
                    if(index == 0)
                    {
                        startIndex = i;
                    }
                    index++;
                    measure.append(index);
                    measure.append(". ");
                    measure.append(measureList.get(i).getString("measure"));
                    measure.append(" ");
                }
                else if(index != 0)
                {
                    break;
                }
            }
            pd.put("measure",measure.toString());
            for(int i = 0; i < index; i++)
            {
                measureList.remove(startIndex);
            }
        }
        for(JSONObject pd : list)
        {
            System.out.println(pd);
        }
        return CommonUtil.successPage(list);
    }
}
