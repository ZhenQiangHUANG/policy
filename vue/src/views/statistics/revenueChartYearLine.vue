<template>
    <div class="app-container">
        <span style="font-weight: 500; font-size:2em;padding:0.2em;margin:20px 0 20px 35%">
            各欢乐谷政策创收年对比
        </span>
        <div id="countByYearPolicyRevenueLineDiv">
            <el-form :inline="true" :model="countByYearPolicyRevenueLineSearch" class="demo-form-inline" > 
                <el-form-item style="margin:10px 0 10px 15%">
                    <el-select v-model="countByYearPolicyRevenueLineSearch.category" placeholder="政策类别" clearable>
                        <el-option label="人力" value="人力"></el-option>
                        <el-option label="财税" value="财税"></el-option>
                        <el-option label="行业" value="行业"></el-option>
                        <el-option label="新基建" value="新基建"></el-option>
                        <el-option label="安全环保" value="安全环保"></el-option>
                        <el-option label="其他" value="其他"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-select v-model="countByYearPolicyRevenueLineSearch.schedule" placeholder="进度" clearable>
                        <el-option label="研究或申报中" value="研究或申报中"></el-option>
                        <el-option label="完成申报，销项" value="完成申报，销项"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <div class="block">
                        <el-date-picker
                            v-model="countByYearPolicyRevenueLineSearch.startTime"
                            type="year"
                            value-format="yyyy" 
                            format="yyyy" 
                            placeholder="请选择起始年份"
                            change="setXAxisData()"
                        >
                        </el-date-picker>
                        <span>至</span>
                        <el-date-picker
                            v-model="countByYearPolicyRevenueLineSearch.endTime"
                            type="year"
                            value-format="yyyy" 
                            format="yyyy" 
                            placeholder="请选择结束年份"
                            change="setXAxisData()"
                        >
                        </el-date-picker>
                    </div>
                </el-form-item>     
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" icon="el-icon-search" @click="getCountByYearPolicyRevenueLine()">查看</el-button>
                </el-form-item>  
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" @click="resetCountByYearPolicyRevenueLineSearch()">重置</el-button>
                </el-form-item>                
            </el-form>    
            <div id="countByYearPolicyRevenueLineChart" style="width:800px; height:500px; margin-left: 15%"></div>
        </div>          
    </div>
</template>

<script>
  export default {
    data() {
        return {
            countByYearPolicyRevenueLineSearch: {//图6(折线图),所有欢乐谷每年的政策创收的搜索框
                category: '',
                schedule: '',
                startTime: '',
                endTime: ''
            },
            xAxisData:[]
        }
    },
    created() {
        var nowDate = new Date();
        this.countByYearPolicyRevenueLineSearch.startTime = (nowDate.getFullYear() - 2) + '';
        this.countByYearPolicyRevenueLineSearch.endTime = nowDate.getFullYear() + '';
        this.setXAxisData()
        this.getCountByYearPolicyRevenueLine()
    },
    methods: {
        getCountByYearPolicyRevenueLine() { //图6(折线图),所有欢乐谷每年的政策创收
            if(this.countByYearPolicyRevenueLineSearch.startTime == null || this.countByYearPolicyRevenueLineSearch.endTime == null)
            {
                this.$message({
                    message: '起始年份/结束年份不能为空',
                    type: 'error'
                });
                return 
            }
            else if(this.countByYearPolicyRevenueLineSearch.startTime > this.countByYearPolicyRevenueLineSearch.endTime)
            {
                this.$message({
                    message: '起始年份必须小于结束年份',
                    type: 'error'
                });
                return 
            }
            this.api({
                url: "/statistics/getCountByYearPolicyRevenueLine",
                method: "post",
                data: this.countByYearPolicyRevenueLineSearch
            }).then(data => {
                this.setXAxisData()
                let countByYearPolicyRevenueLineChart = this.$echarts.init(document.getElementById('countByYearPolicyRevenueLineChart'))
                // 绘制图表
                countByYearPolicyRevenueLineChart.setOption({   
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
                        data: this.xAxisData
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: data.countByYearPolicyRevenueLine
                });
                
            })
        },
        resetCountByYearPolicyRevenueLineSearch() {
            this.countByYearPolicyRevenueLineSearch = {
                category: '',
                schedule: '',
            }
            var nowDate = new Date();
            this.countByYearPolicyRevenueLineSearch.startTime = (nowDate.getFullYear() - 2) + '';
            this.countByYearPolicyRevenueLineSearch.endTime = nowDate.getFullYear() + '';
            this.setXAxisData()
            this.getCountByYearPolicyRevenueLine()
        },
        setXAxisData() {
            this.xAxisData = []
            for(var i = parseInt(this.countByYearPolicyRevenueLineSearch.startTime); i <= parseInt(this.countByYearPolicyRevenueLineSearch.endTime); i++)
            {
                var yearStr = i+'年'
                this.xAxisData.push(yearStr)
            }
        }
    }
  }
</script>
