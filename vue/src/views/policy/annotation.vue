<template>
  <div class="app-container">
    <el-table :data="annotationTable" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
              highlight-current-row>     
        <el-table-column align="center" label="序号">
            <template slot-scope="scope">
                <span v-text="getIndex(scope.$index)"> </span>
                <el-badge value="new" class="item" v-if="getIndex(scope.$index) <= unCheckedNum">
                </el-badge>
            </template>
        </el-table-column>
        <el-table-column align="center" prop="policyTitle" label="政策名称">
            <template slot-scope="scope">
                {{scope.row.policyTitle}}
            </template>
        </el-table-column>
        <el-table-column align="center" label="批注">
            <template slot-scope="scope">
                {{scope.row.annotation}}
            </template>
        </el-table-column>    
        <el-table-column align="center" label="批注人">
            <template slot-scope="scope">
                {{scope.row.modifier}}
            </template>
        </el-table-column>
        <el-table-column align="center" label="时间">
            <template slot-scope="scope">
                {{scope.row.modify_date}}
            </template>
        </el-table-column>  
        <el-table-column align="center" label="操作">
            <template slot-scope="scope">
                <el-button type="primary" size="mini" @click="toPage(scope.row.id)">详情</el-button>
            </template>
        </el-table-column>  
    </el-table> 
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryAnnotationCondition.pageNum"
      :page-size="queryAnnotationCondition.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>   
  </div>
</template>

<script>
  export default {
    data() {
        return {
            listLoading: false,
            annotationTable:[], //批注列表数据
            queryAnnotationCondition:{
                pageNum: 1,//页码
                pageRow: 50,//每页条数
                unCheckedNum: '',//当前用户未查看的批注数量，如果不为0，则查询当前最新的unCheckedNum条数据，如果为0则将所有的批注信息按照修改时间逆序排列
            },
            totalCount: 0
        }
    },
    created() {
        this.getAnnotationTableData()
    },
    methods: {
        getAnnotationTableData() {
            this.listLoading = true
            var url = ''
            this.api({
                url: "/policy/getAnnotationTableData",
                method: "post",
                data: this.queryAnnotationCondition
            }).then(data => {
                this.listLoading = false
                this.annotationTable = data.item.annotationTable
                if(this.unCheckedNum != '')
                {
                    this.unCheckedNum = data.item.unCheckedNum
                }
            })
        },
        handleSizeChange(val) {
            //改变每页数量
            this.searchForm.pageRow = val
            this.handleFilter(); 
        },
        handleCurrentChange(val) {
            //改变页码
            this.searchForm.pageNum = val
            this.getAnnotationTableData();
        },
        handleFilter() {
            //改变了查询条件,从第一页开始查询
            this.searchForm.pageNum = 1
            this.getAnnotationTableData()
        },
        getIndex($index) {
        //表格序号
            return $index + 1
        },
        toPage(id) {      
            console.log(id)  
            //跳转到政策详情页面
            this.$router.push({
                path: '/system/policy',
                query: {
                    id: id
                }
            })
        }        
    }
  }
</script>
