package com.heeexy.example.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.heeexy.example.util.converter.AssistConverter;
import com.heeexy.example.util.converter.DeptConverter;
import com.heeexy.example.util.converter.LevelConverter;
import com.heeexy.example.util.converter.RevenueWayConvert;

/**
 * @ClassName PolicyData
 * @Description 政策记录对应的实体类
 * @Author Huangzhenqiang
 * @Date 2020/11/9 10:25
 * @Version 1.0
 **/
public class PolicyData {

    private String id;

    private String policyId;

    private String creator;

    @NumberFormat("###")
    @ExcelProperty(index = 0,converter = DeptConverter.class)
    private Integer dept;

    @ExcelProperty(index = 1)
    private String policyTitle;

    @ExcelProperty(index = 2)
    private String policyUrl;

    @ExcelProperty(index = 3)
    private String category;

    @ExcelProperty(index = 4,converter = LevelConverter.class)
    private Integer policyLevel;

    @ExcelProperty(index = 5)
    private String policyResume;

    @ExcelProperty(index = 6)
    private String releaseChannel;

    @ExcelProperty(index = 7)
    private String governmentBranch;

    @ExcelProperty(index = 8)
    private String releaseTime;

    @ExcelProperty(index = 9)
    private String collectTime;

    @ExcelProperty(index = 10)
    private String collector;

    @ExcelProperty(index = 11,converter = RevenueWayConvert.class)
    private Integer revenueWay;

    @ExcelProperty(index = 12)
    private String revenue;

    @ExcelProperty(index = 13)
    private String condition;

    @ExcelProperty(index = 14,converter = AssistConverter.class)
    private Integer assist;

    @ExcelProperty(index = 15)
    private String department;

    @ExcelProperty(index = 16)
    private String measure;

    @ExcelProperty(index = 17)
    private String acceptor;

    @ExcelProperty(index = 18)
    private String accpetorPhone;

    @ExcelProperty(index = 19)
    private String schedule;



    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor;
    }

    public String getAccpetorPhone() {
        return accpetorPhone;
    }

    public void setAccpetorPhone(String accpetorPhone) {
        this.accpetorPhone = accpetorPhone;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getPolicyTitle() {
        return policyTitle;
    }

    public void setPolicyTitle(String policyTitle) {
        this.policyTitle = policyTitle;
    }

    public String getPolicyUrl() {
        return policyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getDept() {
        return dept;
    }

    public void setDept(Integer dept) {
        this.dept = dept;
    }

    public Integer getPolicyLevel() {
        return policyLevel;
    }

    public void setPolicyLevel(Integer policyLevel) {
        this.policyLevel = policyLevel;
    }

    public String getPolicyResume() {
        return policyResume;
    }

    public void setPolicyResume(String policyResume) {
        this.policyResume = policyResume;
    }

    public String getReleaseChannel() {
        return releaseChannel;
    }

    public void setReleaseChannel(String releaseChannel) {
        this.releaseChannel = releaseChannel;
    }

    public String getGovernmentBranch() {
        return governmentBranch;
    }

    public void setGovernmentBranch(String governmentBranch) {
        this.governmentBranch = governmentBranch;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRevenueWay() {
        return revenueWay;
    }

    public void setRevenueWay(Integer revenueWay) {
        this.revenueWay = revenueWay;
    }

    public Integer getAssist() {
        return assist;
    }

    public void setAssist(Integer assist) {
        this.assist = assist;
    }

    @Override
    public String toString() {
        return "PolicyData{" +
                "id='" + id + '\'' +
                ", policyId='" + policyId + '\'' +
                ", creator='" + creator + '\'' +
                ", dept=" + dept +
                ", policyTitle='" + policyTitle + '\'' +
                ", policyUrl='" + policyUrl + '\'' +
                ", category='" + category + '\'' +
                ", policyLevel=" + policyLevel +
                ", policyResume='" + policyResume + '\'' +
                ", releaseChannel='" + releaseChannel + '\'' +
                ", governmentBranch='" + governmentBranch + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", collectTime='" + collectTime + '\'' +
                ", collector='" + collector + '\'' +
                ", revenueWay=" + revenueWay +
                ", revenue='" + revenue + '\'' +
                ", condition='" + condition + '\'' +
                ", assist=" + assist +
                ", department='" + department + '\'' +
                ", measure='" + measure + '\'' +
                ", acceptor='" + acceptor + '\'' +
                ", accpetorPhone='" + accpetorPhone + '\'' +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}
