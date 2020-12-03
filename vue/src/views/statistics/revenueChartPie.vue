<template>
    <div class="app-container">
        <span style="font-weight: 500; font-size:2em;padding:0.2em;margin:20px 0 20px 30%">
            各类别政策创效统计(万元)
        </span>
        <div id="groupByCategoryPolicyRevenuePieDiv">
            <el-form :inline="true" :model="groupByCategoryPolicyRevenuePieSearch" class="demo-form-inline" >     
                <el-form-item style="margin:10px 0 10px 15%">
                    <el-select v-model="groupByCategoryPolicyRevenuePieSearch.dept" placeholder="所属欢乐谷" clearable>
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
                    <el-select v-model="groupByCategoryPolicyRevenuePieSearch.schedule" placeholder="进度" clearable>
                        <el-option label="研究或申报中" value="研究或申报中"></el-option>
                        <el-option label="完成申报，销项" value="完成申报，销项"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-date-picker
                        v-model="groupByCategoryPolicyRevenuePieSearch.collectTime"
                        type="year"
                        value-format="yyyy" 
                        format="yyyy" 
                        placeholder="请选择年份"
                    >
                    </el-date-picker>
                </el-form-item>     
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" icon="el-icon-search" @click="getGroupByCategoryPolicyRevenuePie()">查看</el-button>
                </el-form-item>    
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" @click="resetGroupByCategoryPolicyRevenuePieSearch()">重置</el-button>
                </el-form-item>              
            </el-form>    
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item style="margin-left: 12.5%">
                    <div id="groupByCategoryPolicyRevenuePieChart" style="width:500px; height:400px;"></div>
                </el-form-item>
                <el-form-item>
                    <div id="groupByCategoryPolicyRevenueLineChart" style="width:500px; height:400px;"></div>
                </el-form-item>
            </el-form>
        </div>  
        <span style="font-weight: 500; font-size:2em;padding:0.2em;margin:20px 0 20px 30%">
            各部门政策创效统计(万元)
        </span>
        <div id="groupByDeptPolicyRevenuePieDiv">
            <el-form :inline="true" :model="groupByDeptPolicyRevenuePieSearch" class="demo-form-inline" >     
                <el-form-item style="margin:10px 0 10px 15%">
                    <el-select v-model="groupByDeptPolicyRevenuePieSearch.category" placeholder="政策类别" clearable>
                        <el-option label="人力" value="人力"></el-option>
                        <el-option label="财税" value="财税"></el-option>
                        <el-option label="行业" value="行业"></el-option>
                        <el-option label="新基建" value="新基建"></el-option>
                        <el-option label="安全环保" value="安全环保"></el-option>
                        <el-option label="其他" value="其他"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-select v-model="groupByDeptPolicyRevenuePieSearch.schedule" placeholder="进度" clearable>
                        <el-option label="研究或申报中" value="研究或申报中"></el-option>
                        <el-option label="完成申报，销项" value="完成申报，销项"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item style="margin:10px 0 10px 0">
                    <el-date-picker
                        v-model="groupByDeptPolicyRevenuePieSearch.collectTime"
                        type="year"
                        value-format="yyyy" 
                        format="yyyy" 
                        placeholder="请选择年份"
                    >
                    </el-date-picker>
                </el-form-item>     
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" icon="el-icon-search" @click="getGroupByDeptPolicyRevenuePie()">查看</el-button>
                </el-form-item>    
                <el-form-item style="margin:10px 0 10px 0">
                    <el-button type="primary" @click="resetGroupByDeptPolicyRevenuePieSearch()">重置</el-button>
                </el-form-item>              
            </el-form> 
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item style="margin-left: 12.5%">
                    <div id="groupByDeptPolicyRevenuePieChart" style="width:500px; height:400px;"></div>
                </el-form-item>
                <el-form-item>
                    <div id="groupByDeptPolicyRevenueLineChart" style="width:500px; height:400px;"></div>
                </el-form-item>
            </el-form>   
        </div>            
    </div>
</template>

<script>
  export default {
    data() {
        return {  
            groupByCategoryPolicyRevenuePieSearch: {//所有欢乐谷各政策类别创效金额的搜索框
                collectTime: '',
                schedule: '',
                dept: ''
            },
            groupByDeptPolicyRevenuePieSearch: {//所有政策类别各欢乐谷创效金额的搜索框
                collectTime: '',
                schedule: '',
                category: ''
            },
            groupByCategoryPolicyRevenueLineChartXAxis:['人力', '财税', '行业', '新基建', '安全环保', '其他'],
            groupByDeptPolicyRevenueLineChartXAxis:['本部', '深圳', '北京', '成都', '上海', '武汉','天津', '重庆', '南京']
        }
    },
    created() {
        this.getGroupByCategoryPolicyRevenuePie()
        this.getGroupByDeptPolicyRevenuePie()
    },
    methods: {
        getGroupByCategoryPolicyRevenuePie() {//所有欢乐谷各政策类别创效金额
            this.api({
                url: "/statistics/getGroupByCategoryPolicyRevenuePie",
                method: "post",
                data: this.groupByCategoryPolicyRevenuePieSearch
            }).then(data => {
                let groupByCategoryPolicyRevenuePieChart = this.$echarts.init(document.getElementById('groupByCategoryPolicyRevenuePieChart'))
                // 绘制图表
                groupByCategoryPolicyRevenuePieChart.setOption({    
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },            
                    backgroundColor: '#a2c5da',
                    series : [
                        {
                            name: '政策类别创效金额以及占比:',
                            type: 'pie',
                            radius: '70%',
                            data:data.groupByCategoryPolicyRevenuePieData,
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

                var groupByCategoryPolicyRevenueLineChartData = []
                for(var i = 0; i < 6; i++)
                {
                    var j = 0;
                    for( ; j < data.groupByCategoryPolicyRevenuePieData.length; j++)
                    {
                        if( this.groupByCategoryPolicyRevenueLineChartXAxis[i] == data.groupByCategoryPolicyRevenuePieData[j].name)
                        {
                            groupByCategoryPolicyRevenueLineChartData.push(data.groupByCategoryPolicyRevenuePieData[j].value)
                            break;
                        }    
                    }
                    if( j == data.groupByCategoryPolicyRevenuePieData.length)
                    {
                        groupByCategoryPolicyRevenueLineChartData.push(0)
                    }
                }
                // 绘制折线图
                let groupByCategoryPolicyRevenueLineChart = this.$echarts.init(document.getElementById('groupByCategoryPolicyRevenueLineChart'))
                
                groupByCategoryPolicyRevenueLineChart.setOption({ 
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
                        data: this.groupByCategoryPolicyRevenueLineChartXAxis
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: groupByCategoryPolicyRevenueLineChartData,
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
        resetGroupByCategoryPolicyRevenuePieSearch() {
            this.groupByCategoryPolicyRevenuePieSearch = {
                collectTime: '',
                schedule: '',
                dept: ''
            }
            this.getGroupByCategoryPolicyRevenuePie()
        },
        getGroupByDeptPolicyRevenuePie() {//所有政策类别各欢乐谷创效金额
            this.api({
                url: "/statistics/getGroupByDeptPolicyRevenuePie",
                method: "post",
                data: this.groupByDeptPolicyRevenuePieSearch
            }).then(data => {
                let groupByDeptPolicyRevenuePieChart = this.$echarts.init(document.getElementById('groupByDeptPolicyRevenuePieChart'))
                // 绘制图表
                groupByDeptPolicyRevenuePieChart.setOption({    
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },            
                    backgroundColor: '#a2c5da',
                    series : [
                        {
                            name: '欢乐谷创效金额以及占比:',
                            type: 'pie',
                            radius: '80%',
                            data:data.groupByDeptPolicyRevenuePieData,
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


                var groupByDeptPolicyRevenueLineChartData = []
                for(var i = 0; i < 9; i++)
                {
                    var j = 0;
                    for( ; j < data.groupByDeptPolicyRevenuePieData.length; j++)
                    {
                        if( this.groupByDeptPolicyRevenueLineChartXAxis[i] == data.groupByDeptPolicyRevenuePieData[j].name)
                        {
                            groupByDeptPolicyRevenueLineChartData.push(data.groupByDeptPolicyRevenuePieData[j].value)
                            break;
                        }    
                    }
                    if( j == data.groupByDeptPolicyRevenuePieData.length)
                    {
                        groupByDeptPolicyRevenueLineChartData.push(0)
                    }
                }
                // 绘制折线图
                let groupByDeptPolicyRevenueLineChart = this.$echarts.init(document.getElementById('groupByDeptPolicyRevenueLineChart'))
                
                groupByDeptPolicyRevenueLineChart.setOption({ 
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
                        data: this.groupByDeptPolicyRevenueLineChartXAxis
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: groupByDeptPolicyRevenueLineChartData,
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
        resetGroupByDeptPolicyRevenuePieSearch() {
            this.groupByDeptPolicyRevenuePieSearch = {
                collectTime: '',
                schedule: '',
                category: ''
            }
            this.getGroupByDeptPolicyRevenuePie()
        }
    }
  }
</script>
