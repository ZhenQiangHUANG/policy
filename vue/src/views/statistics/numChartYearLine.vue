<template>
    <div class="app-container">
        <span style="font-weight: 500; font-size:2em;padding:0.2em;margin:20px 0 20px 35%">
            各欢乐谷政策收集量年对比
        </span>
        <div id="countByYearPolicyNumLineDiv">
            <el-form :inline="true" :model="countByYearPolicyNumLineSearch" class="demo-form-inline" > 
                <el-form-item style="margin:10px 0 10px 15%">
                    <el-select v-model="countByYearPolicyNumLineSearch.category" placeholder="政策类别" clearable>
                        <el-option label="人力" value="人力"></el-option>
                        <el-option label="财税" value="财税"></el-option>
                        <el-option label="行业" value="行业"></el-option>
                        <el-option label="新基建" value="新基建"></el-option>
                        <el-option label="安全环保" value="安全环保"></el-option>
                        <el-option label="其他" value="其他"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-select v-model="countByYearPolicyNumLineSearch.schedule" placeholder="进度" clearable>
                        <el-option label="研究或申报中" value="研究或申报中"></el-option>
                        <el-option label="完成申报，销项" value="完成申报，销项"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <div class="block">
                        <el-date-picker
                            v-model="countByYearPolicyNumLineSearch.startTime"
                            type="year"
                            value-format="yyyy" 
                            format="yyyy" 
                            placeholder="请选择起始年份"
                            change="setXAxisData()"
                        >
                        </el-date-picker>
                        <span>至</span>
                        <el-date-picker
                            v-model="countByYearPolicyNumLineSearch.endTime"
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
                    <el-button type="primary" icon="el-icon-search" @click="getCountByYearPolicyNumLine()">查看</el-button>
                </el-form-item>  
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" @click="resetCountByYearPolicyNumLineSearch()">重置</el-button>
                </el-form-item>                
            </el-form>    
            <div id="countByYearPolicyNumLineChart" style="width:800px; height:500px; margin-left: 15%"></div>
        </div>          
    </div>
</template>

<script>
  export default {
    data() {
        return {
            countByYearPolicyNumLineSearch: {//图5(折线图),所有欢乐谷每年的政策数量的搜索框
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
        this.countByYearPolicyNumLineSearch.startTime = (nowDate.getFullYear() - 2) + '';
        this.countByYearPolicyNumLineSearch.endTime = nowDate.getFullYear() + '';
        this.setXAxisData()
        this.getCountByYearPolicyNumLine()
    },
    methods: {
        getCountByYearPolicyNumLine() { //图5(折线图),所有欢乐谷每年的政策数量
            if(this.countByYearPolicyNumLineSearch.startTime == null || this.countByYearPolicyNumLineSearch.endTime == null)
            {
                this.$message({
                    message: '起始年份/结束年份不能为空',
                    type: 'error'
                });
                return 
            }
            else if(this.countByYearPolicyNumLineSearch.startTime > this.countByYearPolicyNumLineSearch.endTime)
            {
                this.$message({
                    message: '起始年份必须小于结束年份',
                    type: 'error'
                });
                return 
            }
            this.api({
                url: "/statistics/getCountByYearPolicyNumLine",
                method: "post",
                data: this.countByYearPolicyNumLineSearch
            }).then(data => {
                this.setXAxisData()
                let countByYearPolicyNumLineChart = this.$echarts.init(document.getElementById('countByYearPolicyNumLineChart'))
                // 绘制图表
                countByYearPolicyNumLineChart.setOption({   
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
                    series: data.countByYearPolicyNumLine
                });
                
            })
        },
        resetCountByYearPolicyNumLineSearch() {
            this.countByYearPolicyNumLineSearch = {
                category: '',
                schedule: '',
            }
            var nowDate = new Date();
            this.countByYearPolicyNumLineSearch.startTime = (nowDate.getFullYear() - 2) + '';
            this.countByYearPolicyNumLineSearch.endTime = nowDate.getFullYear() + '';
            this.setXAxisData()
            this.getCountByYearPolicyNumLine()
        },
        setXAxisData() {
            this.xAxisData = []
            for(var i = parseInt(this.countByYearPolicyNumLineSearch.startTime); i <= parseInt(this.countByYearPolicyNumLineSearch.endTime); i++)
            {
                var yearStr = i+'年'
                this.xAxisData.push(yearStr)
            }
        }
    }
  }
</script>
