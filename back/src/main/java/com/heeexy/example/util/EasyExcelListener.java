package com.heeexy.example.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.heeexy.example.pojo.PolicyData;
import com.heeexy.example.service.EasyExcelService;
import com.heeexy.example.service.PolicyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SubjectExcelListener
 * @Description 读取Excel监听器
 * @Author Huangzhenqiang
 * @Date 2020/11/9 10:48
 * @Version 1.0
 **/
public class EasyExcelListener extends AnalysisEventListener<PolicyData> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    List<PolicyData> list = new ArrayList<PolicyData>();

    public EasyExcelService easyExcelService;

    public EasyExcelListener() {

    }

    public EasyExcelListener(EasyExcelService easyExcelService) {
        this.easyExcelService = easyExcelService;
    }

    @Override
    public void invoke(PolicyData policy, AnalysisContext analysisContext) {
        System.out.println(policy);
        //对批量添加的政策记录的4个属性：dept level assist RevenueWay进行检测，如果输入不符合要求，则不插入该政策记录
        if(!CommonUtil.CheckedPolicyParams(policy))
        {
            return ;
        }
        list.add(policy);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            easyExcelService.addPolicyList(list);
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        easyExcelService.addPolicyList(list);
        // 存储完成清理 list
        list.clear();
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }
}
