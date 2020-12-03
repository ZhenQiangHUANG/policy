package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.pojo.PolicyData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EasyExcelService {

    /**
     * @description: 读取Excel添加政策记录
     * @param:  * @param file
     * @return: com.alibaba.fastjson.JSONObject
     * @author Huangzhenqiang
     * @date: 2020/11/9 15:35
     */
    JSONObject importSubjectData(MultipartFile file);

    /**
     * @description: 批量添加政策记录
     * @param:  * @param list
     * @return: void
     * @author Huangzhenqiang
     * @date: 2020/11/9 15:34
     */
    void addPolicyList(List<PolicyData> list);

    /** 
     * @description: 导出数据库数据到Excel表格 
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/10 15:32
     */
    JSONObject exportPolicy(JSONObject requestJson);
}
