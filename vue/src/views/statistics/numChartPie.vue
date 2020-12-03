<template>
    <div class="app-container">
        <span style="font-weight: 500; font-size:2em;padding:0.2em;margin:20px 0 20px 35%">
            各类别政策数量统计
        </span>
        <div id="groupByCategoryPolicyNumPieDiv">
            <el-form :inline="true" :model="groupByCategoryPolicyNumPieSearch" class="demo-form-inline" >    
                <el-form-item style="margin:10px 0 10px 15%">
                    <el-select v-model="groupByCategoryPolicyNumPieSearch.dept" placeholder="所属欢乐谷" clearable>
                        <el-option label="本部" value="0"></el-option>
                        <el-option label="深圳" value="1"></el-option>
                        <el-option label="北京" value="2"></el-option>
                        <el-option label="成都" value="3"></el-option>
                        <el-option label="上海" value="4"></el-option>
                        <el-option label="武汉" value="5"></el-option>
                        <el-option label="天津" value="6"></el-option>
                        <el-option label="重庆" value="7"></el-option>
                        <el-option label="南京" value="8"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-select v-model="groupByCategoryPolicyNumPieSearch.schedule" placeholder="进度" clearable>
                        <el-option label="研究或申报中" value="研究或申报中"></el-option>
                        <el-option label="完成申报，销项" value="完成申报，销项"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-date-picker
                        v-model="groupByCategoryPolicyNumPieSearch.collectTime"
                        type="year"
                        value-format="yyyy" 
                        format="yyyy" 
                        placeholder="请选择年份"
                    >
                    </el-date-picker>
                </el-form-item>     
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" icon="el-icon-search" @click="getGroupByCategoryPolicyNumPie()">查看</el-button>
                </el-form-item>               
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" @click="resetGroupByCategoryPolicyNumPieSearch()">重置</el-button>
                </el-form-item>   
            </el-form>    
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item style="margin-left: 12.5%">
                    <div id="groupByCategoryPolicyNumPieChart" style="width:500px; height:400px;"></div>
                </el-form-item>
                <el-form-item>
                    <div id="groupByCategoryPolicyNumLineChart" style="width:500px; height:400px;"></div>
                </el-form-item>
            </el-form>    
        </div> 
        
        <span style="font-weight: 500; font-size:2em;padding:0.2em;margin:20px 0 20px 35%">
            各部门政策数量统计
        </span>
        <div id="groupByDeptPolicyNumPieDiv">
            <el-form :inline="true" :model="groupByDeptPolicyNumPieSearch" class="demo-form-inline" >    
                <el-form-item style="margin:10px 0 10px 15%">
                    <el-select v-model="groupByDeptPolicyNumPieSearch.category" placeholder="政策类别" clearable>
                        <el-option label="人力" value="人力"></el-option>
                        <el-option label="财税" value="财税"></el-option>
                        <el-option label="行业" value="行业"></el-option>
                        <el-option label="新基建" value="新基建"></el-option>
                        <el-option label="安全环保" value="安全环保"></el-option>
                        <el-option label="其他" value="其他"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-select v-model="groupByDeptPolicyNumPieSearch.schedule" placeholder="进度" clearable>
                        <el-option label="研究或申报中" value="研究或申报中"></el-option>
                        <el-option label="完成申报，销项" value="完成申报，销项"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-date-picker
                        v-model="groupByDeptPolicyNumPieSearch.collectTime"
                        type="year"
                        value-format="yyyy" 
                        format="yyyy" 
                        placeholder="请选择年份"
                    >
                    </el-date-picker>
                </el-form-item>     
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" icon="el-icon-search" @click="getGroupByDeptPolicyNumPie()">查看</el-button>
                </el-form-item>               
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" @click="resetGruopByDeptPolicyNumPieSearch()">重置</el-button>
                </el-form-item>   
            </el-form>   
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item style="margin-left: 12.5%">
                    <div id="groupByDeptPolicyNumPieChart" style="width:500px; height:400px;"></div>
                </el-form-item>
                <el-form-item>
                    <div id="groupByDeptPolicyNumLineChart" style="width:500px; height:400px;"></div>
                </el-form-item>
            </el-form>   
        </div>                   
    </div>
</template>

<script>
  export default {
    data() {
        return {
            groupByCategoryPolicyNumPieSearch: {//每年所有欢乐谷各类别的政策数量的搜索框
                dept: '',
                schedule: '',
                collectTime: ''
            },
            groupByDeptPolicyNumPieSearch: {//每年所有类别各欢乐谷的政策数量的搜索框
                category: '',
                schedule: '',
                collectTime: ''
            },
            groupByCategoryPolicyNumLineChartXAxis:['人力', '财税', '行业', '新基建', '安全环保', '其他'],
            groupByDeptPolicyNumLineChartXAxis:['本部', '深圳', '北京', '成都', '上海', '武汉','天津', '重庆', '南京']
        }
    },
    created() {
        this.getGroupByCategoryPolicyNumPie()
        this.getGroupByDeptPolicyNumPie()
    },
    methods: {
        getGroupByCategoryPolicyNumPie() { //每年所有欢乐谷各类别的政策数量
            this.api({
                url: "/statistics/getGroupByCategoryPolicyNumPie",
                method: "post",
                data: this.groupByCategoryPolicyNumPieSearch
            }).then(data => {
                // 绘制饼状图
                let groupByCategoryPolicyNumPieChart = this.$echarts.init(document.getElementById('groupByCategoryPolicyNumPieChart'))
                
                groupByCategoryPolicyNumPieChart.setOption({    
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },            
                    backgroundColor: '#a2c5da',
                    series : [
                        {
                            name: '政策类别数量以及占比:',
                            type: 'pie',
                            radius: '70%',
                            data:data.groupByCategoryPolicyNumPieData,
                            selectedMode: 'single',
                            label: {
                                normal: {
                                    formatter: '{b} :{d}%',
                                    textStyle: {
                                        color: 'rgba(22, 36, 50, 1)'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    lineStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
                                    }
                                }
                            },
                            itemStyle: {
                                normal: {
                                    shadowBlur: 200,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });

                var groupByCategoryPolicyNumLineChartData = []
                for(var i = 0; i < 6; i++)
                {
                    var j = 0;
                    for( ; j < data.groupByCategoryPolicyNumPieData.length; j++)
                    {
                        if( this.groupByCategoryPolicyNumLineChartXAxis[i] == data.groupByCategoryPolicyNumPieData[j].name)
                        {
                            groupByCategoryPolicyNumLineChartData.push(data.groupByCategoryPolicyNumPieData[j].value)
                            break;
                        }    
                    }
                    if( j == data.groupByCategoryPolicyNumPieData.length)
                    {
                        groupByCategoryPolicyNumLineChartData.push(0)
                    }
                }
                // 绘制折线图
                let groupByCategoryPolicyNumLineChart = this.$echarts.init(document.getElementById('groupByCategoryPolicyNumLineChart'))
                
                groupByCategoryPolicyNumLineChart.setOption({ 
                    tooltip: {
                        trigger: 'axis'
                    },  
                    backgroundColor: '#a2c5da',
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    
                    xAxis: {
                        
                        type: 'category',
                        boundaryGap: true,
                        data: this.groupByCategoryPolicyNumLineChartXAxis
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: groupByCategoryPolicyNumLineChartData,
                        type: 'line',
                        itemStyle : { 
                            normal: {
                                label : {show: true}
                            }
                        },
                    }]
                });
                
            })
        },
        resetGroupByCategoryPolicyNumPieSearch() {
            this.groupByCategoryPolicyNumPieSearch = {
                dept: '',
                schedule: '',
                collectTime: ''
            }
            this.getGroupByCategoryPolicyNumPie()
        },
        getGroupByDeptPolicyNumPie() { //每年所有类别各欢乐谷的政策数量的搜索框
            this.api({
                url: "/statistics/getGroupByDeptPolicyNumPie",
                method: "post",
                data: this.groupByDeptPolicyNumPieSearch
            }).then(data => {
                let groupByDeptPolicyNumPieChart = this.$echarts.init(document.getElementById('groupByDeptPolicyNumPieChart'))
                // 绘制图表
                groupByDeptPolicyNumPieChart.setOption({    
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },            
                    backgroundColor: '#a2c5da',
                    series : [
                        {
                            name: '欢乐谷政策数量以及占比:',
                            type: 'pie',
                            radius: '70%',
                            data:data.groupByDeptPolicyNumPieData,
                            selectedMode: 'single',
                            label: {
                                normal: {
                                    formatter: '{b} :{d}%',
                                    textStyle: {
                                        color: 'rgba(22, 36, 50, 1)'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    lineStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
                                    }
                                }
                            },
                            itemStyle: {
                                normal: {
                                    shadowBlur: 200,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });

                var groupByDeptPolicyNumLineChartData = []
                for(var i = 0; i < 9; i++)
                {
                    var j = 0;
                    for( ; j < data.groupByDeptPolicyNumPieData.length; j++)
                    {
                        if( this.groupByDeptPolicyNumLineChartXAxis[i] == data.groupByDeptPolicyNumPieData[j].name)
                        {
                            groupByDeptPolicyNumLineChartData.push(data.groupByDeptPolicyNumPieData[j].value)
                            break;
                        }    
                    }
                    if( j == data.groupByDeptPolicyNumPieData.length)
                    {
                        groupByDeptPolicyNumLineChartData.push(0)
                    }
                }
                // 绘制折线图
                let groupByDeptPolicyNumLineChart = this.$echarts.init(document.getElementById('groupByDeptPolicyNumLineChart'))
                
                groupByDeptPolicyNumLineChart.setOption({ 
                    tooltip: {
                        trigger: 'axis'
                    },  
                    backgroundColor: '#a2c5da',
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },                    
                    xAxis: {
                        
                        type: 'category',
                        boundaryGap: true,
                        data: this.groupByDeptPolicyNumLineChartXAxis
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: groupByDeptPolicyNumLineChartData,
                        type: 'line',
                        itemStyle : { 
                            normal: {
                                label : {show: true}
                            }
                        },
                    }]
                });
                
            })
        },
        resetGruopByDeptPolicyNumPieSearch() {
            this.groupByDeptPolicyNumPieSearch = {
                category: '',
                schedule: '',
                collectTime: ''
            }
            this.getGroupByDeptPolicyNumPie()
        }
    }
  }
</script>
