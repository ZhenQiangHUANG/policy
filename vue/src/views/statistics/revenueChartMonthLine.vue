<template>
    <div class="app-container">
        <span style="font-weight: 500; font-size:2em;padding:0.2em;margin:20px 0 20px 35%">
            各欢乐谷政策创收月对比
        </span>
        <div id="countByMonthPolicyRevenueLineDiv">
            <el-form :inline="true" :model="countByMonthPolicyRevenueLineSearch" class="demo-form-inline" > 
                <el-form-item style="margin:10px 0 10px 15%">
                    <el-select v-model="countByMonthPolicyRevenueLineSearch.category" placeholder="政策类别" clearable>
                        <el-option label="人力" value="人力"></el-option>
                        <el-option label="财税" value="财税"></el-option>
                        <el-option label="行业" value="行业"></el-option>
                        <el-option label="新基建" value="新基建"></el-option>
                        <el-option label="安全环保" value="安全环保"></el-option>
                        <el-option label="其他" value="其他"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-select v-model="countByMonthPolicyRevenueLineSearch.schedule" placeholder="进度" clearable>
                        <el-option label="研究或申报中" value="研究或申报中"></el-option>
                        <el-option label="完成申报，销项" value="完成申报，销项"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-date-picker
                        v-model="countByMonthPolicyRevenueLineSearch.collectTime"
                        type="year"
                        value-format="yyyy" 
                        format="yyyy" 
                        placeholder="请选择年份"
                    >
                    </el-date-picker>
                </el-form-item>     
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" icon="el-icon-search" @click="getCountByMonthPolicyRevenueLine()">查看</el-button>
                </el-form-item>  
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" @click="resetCountByMonthPolicyRevenueLineSearch()">重置</el-button>
                </el-form-item>                
            </el-form>    
            <div id="countByMonthPolicyRevenueLineChart" style="width:800px; height:500px; margin-left: 15%"></div>
        </div>          
    </div>
</template>

<script>
  export default {
    data() {
        return {
            countByMonthPolicyRevenueLineSearch: {//图4(折线图),每年所有欢乐谷各月份的政策创收的搜索框
                category: '',
                schedule: '',
                collectTime: ''
            }
        }
    },
    created() {
        this.getCountByMonthPolicyRevenueLine()
    },
    methods: {
        getCountByMonthPolicyRevenueLine() { //图4(折线图),每年所有欢乐谷各月份的政策创收
            this.api({
                url: "/statistics/getCountByMonthPolicyRevenueLine",
                method: "post",
                data: this.countByMonthPolicyRevenueLineSearch
            }).then(data => {
                let countByMonthPolicyRevenueLineChart = this.$echarts.init(document.getElementById('countByMonthPolicyRevenueLineChart'))
                // 绘制图表
                countByMonthPolicyRevenueLineChart.setOption({   
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['本部', '深圳', '北京', '成都', '上海','武汉', '天津', '重庆', '南京']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月','八月', '九月', '十月', '十一月', '十二月']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: data.countByMonthPolicyRevenueLine
                });
                
            })
        },
        resetCountByMonthPolicyRevenueLineSearch() {
            this.countByMonthPolicyRevenueLineSearch = {
                category: '',
                schedule: '',
                collectTime: ''
            }
            this.getCountByMonthPolicyRevenueLine()
        }
    }
  }
</script>
