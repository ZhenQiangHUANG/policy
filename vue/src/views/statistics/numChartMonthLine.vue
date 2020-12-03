<template>
    <div class="app-container">
        <span style="font-weight: 500; font-size:2em;padding:0.2em;margin:20px 0 20px 35%">
            各欢乐谷政策收集量月对比
        </span>
        <div id="countByMonthPolicyNumLineDiv">
            <el-form :inline="true" :model="countByMonthPolicyNumLineSearch" class="demo-form-inline" >     
                <el-form-item style="margin:10px 0 10px 15%">
                    <el-select v-model="countByMonthPolicyNumLineSearch.category" placeholder="政策类别" clearable>
                        <el-option label="人力" value="人力"></el-option>
                        <el-option label="财税" value="财税"></el-option>
                        <el-option label="行业" value="行业"></el-option>
                        <el-option label="新基建" value="新基建"></el-option>
                        <el-option label="安全环保" value="安全环保"></el-option>
                        <el-option label="其他" value="其他"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-select v-model="countByMonthPolicyNumLineSearch.schedule" placeholder="进度" clearable>
                        <el-option label="研究或申报中" value="研究或申报中"></el-option>
                        <el-option label="完成申报，销项" value="完成申报，销项"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-date-picker
                        v-model="countByMonthPolicyNumLineSearch.collectTime"
                        type="year"
                        value-format="yyyy" 
                        format="yyyy" 
                        placeholder="请选择年份"
                    >
                    </el-date-picker>
                </el-form-item>     
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" icon="el-icon-search" @click="getCountByMonthPolicyNumLine()">查看</el-button>
                </el-form-item> 
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" @click="resetCountByMonthPolicyNumLineSearch()">重置</el-button>
                </el-form-item>                  
            </el-form>    
            <div id="countByMonthPolicyNumLineChart" style="width:800px; height:500px; margin-left: 15%"></div>
        </div>          
    </div>
</template>

<script>
  export default {
    data() {
        return {
            countByMonthPolicyNumLineSearch: {//图3(折线图),每年所有欢乐谷各月份的政策数量的搜索框
                category: '',
                schedule: '',
                collectTime: ''
            }
        }
    },
    created() {
        this.getCountByMonthPolicyNumLine()
    },
    methods: {
        getCountByMonthPolicyNumLine() { //图3(折线图),每年所有欢乐谷各月份的政策数量
            this.api({
                url: "/statistics/getCountByMonthPolicyNumLine",
                method: "post",
                data: this.countByMonthPolicyNumLineSearch
            }).then(data => {
                let countByMonthPolicyNumLineChart = this.$echarts.init(document.getElementById('countByMonthPolicyNumLineChart'))
                // 绘制图表
                countByMonthPolicyNumLineChart.setOption({   
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
                    series: data.countByMonthPolicyNumLine
                });
                
            })
        },
        resetCountByMonthPolicyNumLineSearch() {
            this.countByMonthPolicyNumLineSearch = {
                category: '',
                schedule: '',
                collectTime: ''
            }
            this.getCountByMonthPolicyNumLine()
        }
    }
  }
</script>
