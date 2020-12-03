package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.EasyExcelService;
import com.heeexy.example.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName EasyExcelController
 * @Description TODO
 * @Author Huangzhenqiang
 * @Date 2020/11/9 11:07
 * @Version 1.0
 **/
@RestController
@RequestMapping("/easyExcel")
public class EasyExcelController {

    @Autowired
    EasyExcelService easyExcelService;

    /** 
     * @description: 读取Excel添加表格 
     * @param:  * @param file 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/10 9:37
     */
    @RequiresPermissions("policy:add")
    @PostMapping("/addPolicyByEasyExcel")
    public JSONObject addArticle(MultipartFile file) {
        return easyExcelService.importSubjectData(file);
    }

    /** 
     * @description: 导入数据到Excel表格中
     * @param:  * @param requestJson 
     * @return: com.alibaba.fastjson.JSONObject 
     * @author Huangzhenqiang
     * @date: 2020/11/10 15:32
     */
    @RequiresPermissions("policy:list")
    @PostMapping("/exportPolicy")
    public  JSONObject exportPolicy(@RequestBody JSONObject requestJson) {
        return easyExcelService.exportPolicy(requestJson);
    }

}
