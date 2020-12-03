package com.heeexy.example.pojo;

/**
 * @ClassName PolicyMeasureData
 * @Description TODO
 * @Author Huangzhenqiang
 * @Date 2020/11/9 10:28
 * @Version 1.0
 **/
public class PolicyMeasureData {

    private String policyId;
    private String measure;
    private String creator;

    PolicyMeasureData() {

    }

    public PolicyMeasureData(String policyId, String measure, String creator) {
        this.policyId = policyId;
        this.measure = measure;
        this.creator = creator;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
