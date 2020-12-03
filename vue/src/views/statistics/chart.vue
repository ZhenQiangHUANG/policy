<template>
    <div class="app-container">
        <div id="countByYearPolicyNumDiv">
            <el-form :inline="true" :model="countByYearPolicyNumSearch" class="demo-form-inline" >
                <el-form-item style="margin-left:15%">
                    <span style="font-weight: 500; font-size:2em;padding:0.2em">
                        各类别政策数量统计
                    </span>
                </el-form-item>     
                <el-form-item>
                    <el-select v-model="countByYearPolicyNumSearch.dept" placeholder="所属欢乐谷" clearable>
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
                <el-form-item>
                    <el-date-picker
                        v-model="countByYearPolicyNumSearch.collectTime"
                        type="year"
                        value-format="yyyy" 
                        format="yyyy" 
                        placeholder="请选择年份"
                    >
                    </el-date-picker>
                </el-form-item>     
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="getCountByYearPolicyNum()">查看</el-button>
                </el-form-item>                  
            </el-form>    
            <div id="countByYearPolicyNumChart" style="width:800px; height:500px; margin-left: 15%"></div>
        </div>       
        <div id="countByYearPolicyRevenueDiv">
            <el-form :inline="true" :model="countByYearPolicyRevenueSearch" class="demo-form-inline" >
                <el-form-item style="margin-left:15%">
                    <span style="font-weight: 500; font-size:2em;padding:0.2em">
                        各类别政策创收统计(万元)
                    </span>
                </el-form-item>     
                <el-form-item>
                    <el-select v-model="countByYearPolicyRevenueSearch.dept" placeholder="所属欢乐谷" clearable>
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
                <el-form-item>
                    <el-date-picker
                        v-model="countByYearPolicyRevenueSearch.collectTime"
                        type="year"
                        value-format="yyyy" 
                        format="yyyy" 
                        placeholder="请选择年份"
                    >
                    </el-date-picker>
                </el-form-item>     
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="getCountByYearPolicyRevenue()">查看</el-button>
                </el-form-item>                  
            </el-form>    
            <div id="countByYearPolicyRevenueChart" style="width:800px; height:500px; margin-left: 15%"></div>
        </div>       
    </div>
</template>

<script>
  export default {
    data() {
        return {
            City: ["本部","深圳","北京","成都","上海","武汉","天津","重庆","南京"],
            policyLevel: ["一般关注","积极关注","重点关注","强烈关注"],
            countByYearPolicyNumSearch: {//图1(饼状图),每年所有欢乐谷各类别的政策数量的搜索框
                dept: '',
                collectTime: ''
            },   
            countByYearPolicyRevenueSearch: {//图2(饼状图),每年所有欢乐谷创收金额的搜索框
                dept: '',
                collectTime: ''
            }
        }
    },
    mounted(){
        this.getCountByYearPolicyNum()
        this.getCountByYearPolicyRevenue()
    },
    created() {
        
    },
    methods: {
        getCountByYearPolicyNum() { //图1(饼状图),每年所有欢乐谷各类别的政策数量
            if(this.countByYearPolicyNumSearch.collectTime == null)
            {
                this.countByYearPolicyNumSearch.collectTime = new Date().getFullYear()
            }
            this.api({
                url: "/statistics/getCountByYearPolicyNum",
                method: "post",
                data: this.countByYearPolicyNumSearch
            }).then(data => {
                let countByYearPolicyNumChart = this.$echarts.init(document.getElementById('countByYearPolicyNumChart'))
                // 绘制图表
                countByYearPolicyNumChart.setOption({    
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },            
                    backgroundColor: '#2c343c',
                    series : [
                        {
                            name: '政策类别数量以及占比:',
                            type: 'pie',
                            radius: '55%',
                            data:data.countByYearPolicyNum,
                            roseType: 'angle',
                            label: {
                                normal: {
                                    textStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
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
                
            })
        },
        getCountByYearPolicyRevenue() {//图2(饼状图),每年所有欢乐谷创收金额的搜索框
            if(this.countByYearPolicyRevenueSearch.collectTime == null)
            {
                this.countByYearPolicyRevenueSearch.collectTime = new Date().getFullYear()
            }
            this.api({
                url: "/statistics/getCountByYearPolicyRevenue",
                method: "post",
                data: this.countByYearPolicyRevenueSearch
            }).then(data => {
                let countByYearPolicyRevenueChart = this.$echarts.init(document.getElementById('countByYearPolicyRevenueChart'))
                // 绘制图表
                countByYearPolicyRevenueChart.setOption({    
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },            
                    backgroundColor: '#2c343c',
                    series : [
                        {
                            name: '政策类别创收金额以及占比:',
                            type: 'pie',
                            radius: '55%',
                            data:data.countByYearPolicyRevenue,
                            roseType: 'angle',
                            label: {
                                normal: {
                                    textStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
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
                
            })
        }
    }
  }
</script>
