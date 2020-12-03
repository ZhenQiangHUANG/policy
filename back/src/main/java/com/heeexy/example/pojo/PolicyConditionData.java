package com.heeexy.example.pojo;

/**
 * @ClassName PolicyConditionData
 * @Description TODO
 * @Author Huangzhenqiang
 * @Date 2020/11/9 10:28
 * @Version 1.0
 **/
public class PolicyConditionData {

    private String policyId;
    private String condition;
    private String creator;

    PolicyConditionData() {

    }

    public PolicyConditionData(String policyId, String condition, String creator) {
        this.policyId = policyId;
        this.condition = condition;
        this.creator = creator;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "PolicyConditionData{" +
                "policyId='" + policyId + '\'' +
                ", condition='" + condition + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}
