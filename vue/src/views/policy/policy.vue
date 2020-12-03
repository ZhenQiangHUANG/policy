<template>
  <div class="app-container">
    <div class="filter-container">
        <el-form :inline="true" :model="searchForm" class="demo-form-inline" >
            <el-form-item>
                <el-popover
                    placement="top"
                    width="200"
                    v-model="addButtonVisible">
                    <div style="text-align: right; margin: 0">
                        <el-button type="primary" size="mini" @click="showAddDialog()">手动添加</el-button>
                        <el-button type="primary" size="mini" @click="showExcelDialog()">批量添加</el-button>
                    </div>
                    <el-button type="primary" slot="reference" icon="plus" v-if="hasPerm('policy:add')">添加
                    </el-button>
                </el-popover>
            </el-form-item>
            <el-form-item :label-width="formLabelWidth" >
                <el-select v-model="searchForm.dept" clearable placeholder="所属欢乐谷" style="width:125px">
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
            <el-form-item :label-width="formLabelWidth">
                <el-input
                    placeholder="政策名称关键字"
                    v-model="searchForm.policyTitle"
                    style="width:240px"
                    clearable>
                </el-input>
            </el-form-item>   
            <el-form-item :label-width="formLabelWidth">                
                <el-select v-model="searchForm.policyLevel" clearable placeholder="政策等级" style="width:110px">
                    <el-option label="一般关注" value="1"></el-option>
                    <el-option label="积极关注" value="2"></el-option>
                    <el-option label="重点关注" value="3"></el-option>
                    <el-option label="强烈关注" value="4"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item :label-width="formLabelWidth"> 
                <el-input  v-model="searchForm.minRevenue" placeholder="¥" style="width:75px"></el-input>
                <em>-</em>
                <el-input v-model="searchForm.maxRevenue" placeholder="¥" style="width:75px"></el-input>
            </el-form-item>  
            <el-form-item :label-width="formLabelWidth">
                <el-select v-model="searchForm.category" placeholder="政策类别" clearable>
                    <el-option label="人力" value="人力"></el-option>
                    <el-option label="财税" value="财税"></el-option>
                    <el-option label="行业" value="行业"></el-option>
                    <el-option label="新基建" value="新基建"></el-option>
                    <el-option label="安全环保" value="安全环保"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                </el-select>
            </el-form-item> 
            <el-form-item :label-width="formLabelWidth">
                <el-input
                    placeholder="项目进度"
                    v-model="searchForm.schedule"
                    style="width:150px"
                    clearable>
                </el-input>
            </el-form-item>  
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="getList()">搜索</el-button>
            </el-form-item> 
            <el-form-item>
                <el-button type="primary" plain @click="resetSearchForm()">重置</el-button>
            </el-form-item>     
        </el-form>
        <el-form :inline="true" class="demo-form-inline" >
            <el-form-item>
                <el-checkbox :indeterminate="isIndeterminate" v-model="checkIdsAll" @change="handleCheckIdsAllChange">当前页所有数据</el-checkbox>
            </el-form-item> 
            <el-form-item>
                <el-checkbox v-model="checkedCondition">当前条件所有数据</el-checkbox>
            </el-form-item> 
            <el-form-item>
                <el-checkbox v-model="checkedAll">所有数据</el-checkbox>
            </el-form-item>     
            <el-form-item >
                <el-button type="primary" size="small" @click="exportExcel()">导出</el-button>
            </el-form-item>  
        </el-form>    
    </div>
    <el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit
              highlight-current-row>
        <el-table-column align="center" prop="checkboxGroup"  width="30">
            <template slot-scope="scope">
                <el-checkbox-group v-model="checkedIds" @change="handleCheckedIdsChange">
                    <el-checkbox :label="scope.row.id"></el-checkbox>
                 </el-checkbox-group>
            </template>            
        </el-table-column>      
        <el-table-column align="center" label="序号" width="80">
            <template slot-scope="scope">
            <span v-text="getIndex(scope.$index)"> </span>
            </template>
        </el-table-column>
        <el-table-column align="center" prop="dept" label="所属欢乐谷" style="width: 20px;">
          <template slot-scope="scope">
            <span v-if="scope.row.dept == 0">本部</span>
            <span v-if="scope.row.dept == 1">深圳</span> 
            <span v-if="scope.row.dept == 2">北京</span> 
            <span v-if="scope.row.dept == 3">成都</span> 
            <span v-if="scope.row.dept == 4">上海</span> 
            <span v-if="scope.row.dept == 5">武汉</span> 
            <span v-if="scope.row.dept == 6">天津</span> 
            <span v-if="scope.row.dept == 7">重庆</span> 
            <span v-if="scope.row.dept == 8">南京</span> 
          </template> 
        </el-table-column>
        <el-table-column align="center" prop="policyTitle" label="政策名称" style="width: 20px;">
            <template slot-scope="scope">
                <el-popover
                    placement="top-start"
                    width="200"
                    trigger="hover"
                    :content="scope.row.policyTitle">                
                    <span slot="reference" @click="toPolicy(scope.row.policyUrl)" style="text-overflow: ellipsis; overflow: hidden; width: 20em; white-space: nowrap;">
                        <a style="text-decoration:underline;" >
                            {{scope.row.policyTitle}}
                        </a>
                    </span>
                </el-popover>
            </template>
        </el-table-column>
        <el-table-column align="center" label="政策等级" style="width: 20px;">
            <template slot-scope="scope">
                <span v-if="scope.row.policyLevel == 1">一般关注</span> 
                <span v-if="scope.row.policyLevel == 2">积极关注</span> 
                <span v-if="scope.row.policyLevel == 3">重点关注</span> 
                <span v-if="scope.row.policyLevel == 4">强烈关注</span> 
            </template>          
        </el-table-column>
        <el-table-column align="center" label="可利用条款" width="100" >
            <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="edit" @click="showDetail('condition',scope.row.id)">查看</el-button>
            </template>
        </el-table-column>
        <el-table-column align="center" prop="policyResume" label="政策简述" style="width: 60px;">
            <template slot-scope="scope">
                <el-popover
                    placement="top-start"
                    width="200"
                    trigger="hover"
                    :content="scope.row.policyResume"> 
                    <span slot="reference" style="text-overflow: ellipsis; overflow: hidden; width: 20em; white-space: nowrap;">
                        {{scope.row.policyResume}}
                    </span>
                </el-popover>
            </template>
        </el-table-column>
        <el-table-column align="center" label="预计创效（万元）" width="170">
            <template slot-scope="scope" >
            <span>{{formatCurrency(scope.row.revenue,2)}}</span>
            </template>
        </el-table-column>
        <el-table-column align="center" label="利用措施" width="100" >
            <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="edit" @click="showDetail('measure',scope.row.id)">查看</el-button>
            </template>
        </el-table-column>
        <el-table-column align="center" label="进度" width="170">
            <template slot-scope="scope" >
            <span>{{scope.row.schedule}}</span>
            </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="200" v-if="hasPerm('policy:update')" >
            <template slot-scope="scope">
                <el-button-group>
                    <el-button type="primary" icon="edit" size="mini" @click="showDetail('policy',scope.$index)">查看</el-button>    
                    <el-button type="primary" icon="edit" size="mini" @click="showDetail('annotation',scope.row.id)" v-if="hasPerm('policy:annotate')">批注</el-button>    
                    <el-button type="danger" icon="edit" size="mini" @click="handleDelete('policy',scope.row.id,scope.$index)">删除</el-button>
                </el-button-group>
            </template>
        </el-table-column>
    </el-table>
    
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="searchForm.pageNum"
      :page-size="searchForm.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-backtop></el-backtop>

    <el-dialog title="添加政策" :visible.sync="dialogAddFormVisible" @close="closeDialog('addPolicy')">
        <el-form :model="addForm" :label-position="labelPosition" :rules="addFormRules" ref="addForm">
            <el-form-item label="所属欢乐谷" :label-width="formLabelWidth" prop="dept">
                <el-select v-model="addForm.dept" placeholder="请选择所属欢乐谷">
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
            <el-form-item label="政策名称" :label-width="formLabelWidth" prop="policyTitle">
                <el-input v-model="addForm.policyTitle" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="政策访问地址" :label-width="formLabelWidth" prop="policyUrl">
                <el-input v-model="addForm.policyUrl" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="政策类别" :label-width="formLabelWidth" prop="category">
                <el-select v-model="addForm.category" placeholder="政策类别" clearable>
                    <el-option label="人力" value="人力"></el-option>
                    <el-option label="财税" value="财税"></el-option>
                    <el-option label="行业" value="行业"></el-option>
                    <el-option label="新基建" value="新基建"></el-option>
                    <el-option label="安全环保" value="安全环保"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="政策等级" :label-width="formLabelWidth" prop="policyLevel">                
                <el-select v-model="addForm.policyLevel" placeholder="请选择政策等级">
                    <el-option label="一般关注" value="1"></el-option>
                    <el-option label="积极关注" value="2"></el-option>
                    <el-option label="重点关注" value="3"></el-option>
                    <el-option label="强烈关注" value="4"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="政策简述" :label-width="formLabelWidth" prop="policyResume">
                <el-input type="textarea" v-model="addForm.policyResume" autocomplete="off" placeholder="请输入政策的简述"></el-input>
            </el-form-item>
            <el-form-item label="政策发布渠道" :label-width="formLabelWidth" prop="releaseChannel">
                <el-input v-model="addForm.releaseChannel" autocomplete="off" placeholder="请输入政策的发布渠道"></el-input>
            </el-form-item>
            <el-form-item label="政策出台部门" :label-width="formLabelWidth" prop="政策出台部门">
                <el-input v-model="addForm.governmentBranch" autocomplete="off" placeholder="请输入政策的出台部门"></el-input>
            </el-form-item>
            <el-form-item label="政策出台时间" :label-width="formLabelWidth" placeholder="请输入" prop="releaseTime">
                <div class="block">
                    <el-date-picker
                    v-model="addForm.releaseTime"
                    type="date"
                    value-format="yyyy-MM-dd" 
                    format="yyyy-MM-dd" 
                    placeholder="选择日期时间"
                    >
                    </el-date-picker>
                </div>
            </el-form-item>
            <el-form-item label="政策收集时间" :label-width="formLabelWidth" prop="collectTime">
                <div class="block">
                    <el-date-picker
                    v-model="addForm.collectTime"
                    type="date"
                    value-format="yyyy-MM-dd" 
                    format="yyyy-MM-dd" 
                    placeholder="选择日期时间"
                    >
                    </el-date-picker>
                </div>                
            </el-form-item>
            <el-form-item label="政策收集人" :label-width="formLabelWidth" prop="collector">
                <el-input v-model="addForm.collector" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="直接/间接创效" :label-width="formLabelWidth" prop="revenueWay">
                <el-select v-model="addForm.revenueWay" placeholder="请选择创效方式">
                    <el-option label="间接创效" value="1"></el-option>
                    <el-option label="直接创效" value="2"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="直接创效" :label-width="formLabelWidth" prop="revenue">
                <el-input v-model="addForm.revenue" autocomplete="off" placeholder="最多可精确到小数点后两位,单位为万元"></el-input>
            </el-form-item>
            <el-form-item label="需本部协助" :label-width="formLabelWidth" prop="assist">
                <el-select v-model="addForm.assist" placeholder="请选择是否需要协助">
                    <el-option label="不需要" value="1"></el-option>
                    <el-option label="需要" value="2"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="建议承办部门" :label-width="formLabelWidth" prop="department">
                <el-input v-model="addForm.department" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="负责人" :label-width="formLabelWidth" prop="acceptor">
                <el-input v-model="addForm.acceptor" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="负责人电话" :label-width="formLabelWidth" prop="acceptorPhone">
                <el-input v-model="addForm.acceptorPhone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="项目进度" :label-width="formLabelWidth" prop="schedule">
                <el-input v-model="addForm.schedule" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
        <el-button type="primary" @click="createPolicy()" style="margin-left : 40%" v-if="hasPerm('policy:add')">确认</el-button>        
    </el-dialog>

    <el-dialog title="添加政策" :visible.sync="dialogExcelFormVisible">
        <el-form label-width="120px">
            <el-form-item label="信息描述">
                <el-tag type="info">excel模版说明</el-tag>
                <el-tag>
                <i class="el-icon-download"/>
                <a>点击下载模版</a>
                </el-tag>
            </el-form-item>

            <el-form-item label="选择Excel">
                <el-upload
                ref="upload"
                :auto-upload="false"
                :on-success="fileUploadSuccess"
                :on-error="fileUploadError"
                :disabled="importBtnDisabled"
                :limit="1"
                action="/api/easyExcel/addPolicyByEasyExcel"
                name="file"
                accept=".xls,.xlsx ">
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button
                    :loading="uploadFileLoading"
                    style="margin-left: 10px;"
                    size="small"
                    type="success"
                    @click="uploadFileAddPolicy">{{ fileUploadBtnText }}</el-button>
                </el-upload>
            </el-form-item>
        </el-form>       
    </el-dialog>
    
    <el-dialog title="政策详情" :visible.sync="dialogPolicyFormVisible" @close="closeDialog('policy')">
        <el-form v-model="form" :label-position="labelPosition">
            <el-form-item label="所属欢乐谷" :label-width="formLabelWidth">
                <el-select v-model="form.dept" placeholder="请选择所属欢乐谷" :disabled="dialogPolicyFormEditable">
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
            <el-form-item label="政策名称" :label-width="formLabelWidth">
                <input 
                    v-model="form.policyTitle" 
                    :class="dialogPolicyFormEditable ? 'input_none_border' : 'el-input__inner'"
                    autocomplete="off" 
                    :readonly="dialogPolicyFormEditable" 
                >
            </el-form-item>
            <el-form-item label="政策访问地址" :label-width="formLabelWidth">
                <input :class="dialogPolicyFormEditable ? 'input_none_border' : 'el-input__inner'" v-model="form.policyUrl" autocomplete="off" :readonly="dialogPolicyFormEditable">
            </el-form-item>
            <el-form-item label="政策类别" :label-width="formLabelWidth">
                <input :class="dialogPolicyFormEditable ? 'input_none_border' : 'el-input__inner'" v-model="form.category" autocomplete="off" :readonly="dialogPolicyFormEditable">
            </el-form-item>
            <el-form-item label="政策等级" :label-width="formLabelWidth">                
                <el-select v-model="form.policyLevel" placeholder="请选择政策等级" :disabled="dialogPolicyFormEditable">
                    <el-option label="一般关注" value="1"></el-option>
                    <el-option label="积极关注" value="2"></el-option>
                    <el-option label="重点关注" value="3"></el-option>
                    <el-option label="强烈关注" value="4"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="政策简述" :label-width="formLabelWidth">
                <input :class="dialogPolicyFormEditable ? 'input_none_border' : 'el-input__inner'" type="textarea" v-model="form.policyResume" autocomplete="off" :readonly="dialogPolicyFormEditable">
            </el-form-item>
            <el-form-item label="政策发布渠道" :label-width="formLabelWidth">
                <input :class="dialogPolicyFormEditable ? 'input_none_border' : 'el-input__inner'" v-model="form.releaseChannel" autocomplete="off" :readonly="dialogPolicyFormEditable">
            </el-form-item>
            <el-form-item label="政策出台部门" :label-width="formLabelWidth">
                <input :class="dialogPolicyFormEditable ? 'input_none_border' : 'el-input__inner'" v-model="form.governmentBranch" autocomplete="off" :readonly="dialogPolicyFormEditable">
            </el-form-item>
            <el-form-item label="政策出台时间" :label-width="formLabelWidth">
                <div class="block">
                    <el-date-picker
                    v-model="form.releaseTime"
                    type="date"
                    value-format="yyyy-MM-dd" 
                    format="yyyy-MM-dd" 
                    placeholder="选择日期时间"
                    :readonly="dialogPolicyFormEditable"
                    >
                    </el-date-picker>
                </div>
            </el-form-item>
            <el-form-item label="政策收集时间" :label-width="formLabelWidth">
                <div class="block">
                    <el-date-picker
                    v-model="form.collectTime"
                    type="date"
                    value-format="yyyy-MM-dd" 
                    format="yyyy-MM-dd" 
                    placeholder="选择日期时间"
                    :readonly="dialogPolicyFormEditable"
                    >
                    </el-date-picker>
                </div>                
            </el-form-item>
            <el-form-item label="政策收集人" :label-width="formLabelWidth">
                <input :class="dialogPolicyFormEditable ? 'input_none_border' : 'el-input__inner'" v-model="form.collector" autocomplete="off" :readonly="dialogPolicyFormEditable">
            </el-form-item>
            <el-form-item label="直接/间接创效" :label-width="formLabelWidth">
                <el-select v-model="form.revenueWay" placeholder="请选择创效方式" :disabled="dialogPolicyFormEditable">
                    <el-option label="间接创效" value="1"></el-option>
                    <el-option label="直接创效" value="2"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="直接创效(万元)" :label-width="formLabelWidth">
                <input 
                    v-model="form.revenue" 
                    :class="dialogPolicyFormEditable ? 'input_none_border' : 'el-input__inner'"
                    autocomplete="off" 
                    :readonly="dialogPolicyFormEditable"
                    value="formatCurrency(form.revenue,2)"
                >
            </el-form-item>
            <el-form-item label="需本部协助" :label-width="formLabelWidth">
                <el-select v-model="form.assist" placeholder="请选择是否需要协助" :disabled="dialogPolicyFormEditable">
                    <el-option label="不需要" value="1"></el-option>
                    <el-option label="需要" value="2"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="建议承办部门" :label-width="formLabelWidth">
                <input :class="dialogPolicyFormEditable ? 'input_none_border' : 'el-input__inner'" v-model="form.department" autocomplete="off" :readonly="dialogPolicyFormEditable">
            </el-form-item>
            <el-form-item label="负责人" :label-width="formLabelWidth">
                <input :class="dialogPolicyFormEditable ? 'input_none_border' : 'el-input__inner'" v-model="form.acceptor" autocomplete="off" :readonly="dialogPolicyFormEditable">
            </el-form-item>
            <el-form-item label="负责人电话" :label-width="formLabelWidth" prop="acceptor">
                <input :class="dialogPolicyFormEditable ? 'input_none_border' : 'el-input__inner'" v-model="form.accpetorPhone" autocomplete="off">
            </el-form-item>
            <el-form-item label="政策利用进度" :label-width="formLabelWidth">
                <input :class="dialogPolicyFormEditable ? 'input_none_border' : 'el-input__inner'" v-model="form.schedule" autocomplete="off" :readonly="dialogPolicyFormEditable">
            </el-form-item>
        </el-form>
        <el-button type="primary" @click="editDialog('policy')" style="margin-left : 32%" v-if="hasPerm('policy:update')">编辑</el-button>
        <el-button type="primary" @click="updatePolicy()" controls-position="right" v-if="hasPerm('policy:update')">保存</el-button>        
    </el-dialog>
    <el-dialog title="可利用条款详情" :visible.sync="dialogConditionFormVisible" @close="closeDialog('condition')">
        <el-table
            :data="conditionForm"
            style="width: 100%">                        
            <el-table-column
            label="序号"
            width="50">
                <template slot-scope="scope">                       
                    <span>{{ scope.$index + 1 }}</span>
                 </template>
            </el-table-column>
            <el-table-column
                label="可利用条款内容"
            >
                <template slot-scope="scope">
                    <el-input 
                        :readonly="dialogConditionFormEditable" 
                        v-model="scope.row.conditions" 
                        el-textarea__inner
                        type="textarea"
                    ></el-input>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="150px" v-if="hasPerm('policy:add') || hasPerm('policy:update')">
                <template slot-scope="scope">
                    <el-button                            
                        size="mini"
                        @click="handleUpdate('condition',scope.row.id,scope.$index)">保存</el-button>
                    <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete('condition',scope.row.id,scope.$index)" v-if="hasPerm('policy:update')">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-button type="primary" @click="addItems('condition')" style="margin-left : 32%" v-if="hasPerm('policy:add')">添加</el-button>
        <el-button type="primary" @click="editDialog('condition')" v-if="hasPerm('policy:add') || hasPerm('policy:update')">编辑</el-button>
    </el-dialog>
    <el-dialog title="利用措施详情" :visible.sync="dialogMeasureFormVisible" @close="closeDialog('measure')">
        <el-table
            :data="measureForm"
            style="width: 100%">                        
            <el-table-column
            label="序号"
            width="50">
                <template slot-scope="scope">                       
                    <span>{{ scope.$index + 1 }}</span>
                 </template>
            </el-table-column>
            <el-table-column
                label="利用措施内容"
            >
                <template slot-scope="scope">
                    <el-input 
                        :readonly="dialogMeasureFormEditable" 
                        v-model="scope.row.measure" 
                        type="textarea"
                    ></el-input>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="150px" v-if="hasPerm('policy:add') || hasPerm('policy:update')">
                <template slot-scope="scope">
                    <el-button                            
                        size="mini"
                        @click="handleUpdate('measure',scope.row.id,scope.$index)">保存</el-button>
                    <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete('measure',scope.row.id,scope.$index)" v-if="hasPerm('policy:update')">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-button type="primary" @click="addItems('measure')" style="margin-left : 32%" v-if="hasPerm('policy:add')">添加</el-button>
        <el-button type="primary" @click="editDialog('measure')" v-if="hasPerm('policy:add') || hasPerm('policy:update')">编辑</el-button>
    </el-dialog>
    <el-dialog title="批注信息详情" :visible.sync="dialogAnnotationFormVisible" @close="closeDialog('annotation')">        
        <el-input
            type="textarea"
            :autosize="{ minRows: 4, maxRows: 8}"
            :readonly="dialogAnnotationFormEditable"
            placeholder="暂无批注信息"
            v-model="annotationForm.annotation">
        </el-input>
        <el-button type="primary" style="margin: 15px 15px 10px 40%" @click="editDialog('annotation')" v-if="hasPerm('policy:annotate')">编辑</el-button>
        <el-button type="primary" style="margin: 15px 0 10px 0" @click="handleUpdate('annotation','','')" v-if="hasPerm('policy:annotate')">保存</el-button>
    </el-dialog>
  </div>
</template>

<style scoped>
.input_none_border{
    -webkit-appearance: none;
    background-color: #FFF;
    background-image: none;
    border-radius: 4px;
    border: none;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    color: #606266;
    display: inline-block;
    font-size: inherit;
    height: 40px;
    line-height: 40px;
    outline: 0;
    padding: 0 15px;
    -webkit-transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    width: 100%;
}
</style>

<script>
  export default {
    data() {
      return {
        totalCount: 0, //分页组件--数据总条数
        list: [],//表格的数据
        listLoading: false,//数据加载等待动画
        dialogStatus: 'create',
        dialogPolicyFormVisible: false,      //控制政策详情显示
        dialogAddFormVisible: false,         //控制添加表单的显示
        dialogExcelFormVisible: false,       //控制批量添加表单的显示
        dialogConditionFormVisible: false,   //控制可利用条款详情显示
        dialogMeasureFormVisible: false,     //控制条款利用措施详情显示
        dialogAnnotationFormVisible: false,  //控制批注信息详情显示
        dialogPolicyFormEditable: true,      //控制政策详情表单的修改
        dialogConditionFormEditable: true,   //控制可利用条款表单的修改
        dialogMeasureFormEditable: true,     //控制条款利用措施表单的修改
        dialogAnnotationFormEditable: true,  //控制批注输入框的修改状态
        addButtonVisible: false,             //两个添加按钮的显示
        labelPosition: 'left',               //控制表单中标签的对齐方式
        dialogFormIndex : -1,                //当前显示的表单为对应政策的在list中的下标
        fileUploadBtnText: '添加',           // 按钮文字
        importBtnDisabled: false,            // 按钮是否禁用,
        uploadFileLoading: false,
        form: {//政策详情中的数据显示
          id: '',
          dept: '',
          policyId: '',
          policyTitle: '',
          policyUrl: '',
          category: '',
          policyLevel: '',
          policyResume: '',
          releaseChannel: '',
          governmentBranch: '',
          releaseTime: '',
          collectTime: '',
          collector: '',
          revenueWay: '',
          revenue: '',
          conditions: '',
          assist: '',
          department: '',
          measure: '',
          acceptor: '',
          acceptorPhone: '',
          schedule: ''
        },
        addForm: {
            dept: '',
            policyTitle: '',
            policyUrl: '',
            category: '',
            policyLevel: '',
            policyResume: '',
            releaseChannel: '',
            governmentBranch: '',
            releaseTime: '',
            collectTime: '',
            collector: '',
            revenueWay: '',
            revenue: '',
            assist: '',
            department: '',
            acceptor: '',
            acceptorPhone: '',
            schedule: ''
        },//添加表单的内容
        addFormRules: {
            dept: [
                { required: true, message: '请选择政策所属欢乐谷', trigger: 'change' }
            ],
            policyTitle: [
                { required: true, message: '请输入政策的标题', trigger: 'blur' },
                { min: 0, max: 100, message: '长度在 0 到 100 个字符', trigger: 'blur' }
            ],
            policyUrl: [
                { required: true, message: '请输入完整的政策对应的链接地址', trigger: 'blur' },
            ],
            category: [
                { required: true, message: '请输入政策的所属类别', trigger: 'blur' }
            ],
            policyLevel: [
                { required: true, message: '请选择政策的关注程度', trigger: 'change' }
            ],
            policyResume: [
                { required: true, message: '请输入政策的简述', trigger: 'blur' },
                { min: 0, message: '长度在 0 个字符以上', trigger: 'blur' }
            ],
            releaseChannel: [
                { required: true, message: '请输入政策的发布渠道', trigger: 'blur' },
                { min: 0, max: 100, message: '长度在 0 到 100 个字符', trigger: 'blur' }
            ],
            governmentBranch: [
                { required: true, message: '请输入政策的出台部门', trigger: 'blur' },
                { min: 0, max: 100, message: '长度在 0 到 100 个字符', trigger: 'blur' }
            ],
            releaseTime: [
                { required: true, message: '请选择政策的发布日期', trigger: 'change' }
            ],
            collectTime: [
               { required: true, message: '请选择政策的收集日期', trigger: 'change' }
            ],
            collector: [
                { required: true, message: '请输入政策的收集者', trigger: 'blur' },
                { min: 0, max: 100, message: '长度在 0 到 100 个字符', trigger: 'blur' }
            ],
            revenueWay: [
                { required: true, message: '请选择政策的创效方式', trigger: 'change' }
            ],
            revenue: [
                {   
                    required: true, trigger: 'blur' ,
                    validator: (rule,value) => {
                        return new Promise((resolve, reject) => {
                            if(value != null && value != "")
                            {
                                var reg = /^[0-9]+\.?[0-9]*$/;
                                if(reg.test(value))
                                {
                                    if(value.indexOf('.') != -1)
                                    {
                                        if(value.split('.')[0].length > 5)
                                        {
                                            reject(new Error('创效金额的最多可为位(亿级别)'))
                                        }
                                        if(value.split('.')[1].length > 2)
                                        {
                                            reject(new Error('最多可保留小数点后两位数字'))
                                        }   
                                        else
                                        {
                                            resolve()
                                        }    
                                    }
                                    else
                                    {
                                        if(value.length > 8)
                                        {
                                            reject(new Error('创效金额的最多可为8位(千万级别)'))
                                        }
                                        else
                                        {
                                            resolve()
                                        }  
                                    }                                                     
                                } else {
                                    reject("请输入正确的金额，仅含数字和'.' ")                            
                                }                               
                            } else {
                                reject('请输入政策预计创效')
                            } 
                        }); 
                    }   
               }
            ],
            assist: [
                { required: true, message: '请选择是否需要集团本部的援助', trigger: 'change' }
            ],
            department: [
                { required: true, message: '请输入政策的承办部门', trigger: 'blur' },
                { min: 0, max: 100, message: '长度在 0 到 100 个字符', trigger: 'blur' }
            ],
            acceptor: [
                { required: true, message: '请输入政策的负责人', trigger: 'blur' },
                { min: 0, max: 100, message: '长度在 0 到 100 个字符', trigger: 'blur' }
            ],
            acceptorPhone: [
                {
                    trigger: 'blur',
                    validator: (rule,value) => {
                        return new Promise((resolve, reject) => {
                            var reg=/^[1][3,4,5,7,8][0-9]{9}$/;
                            if(!reg.test(value))
                            {
                                reject("请输入正确的手机号")
                            }
                            else{
                                resolve()
                            }
                        }); 
                    }   
                }
            ],
            schedule: [
                { required: true, message: '请输入政策的利用进度', trigger: 'blur' },
                { min: 0, max: 100, message: '长度在 0 到 100 个字符', trigger: 'blur' }
            ],             
        },
        addOfPolicyId: '',//被打开的表单对应的政策的id
        conditionForm: {},//条件表单的内容
        measureForm: {},//条件措施的内容
        annotationForm: { //批注表单的内容
            id: '',
            annotation: ''
        },
        searchForm: {
            pageNum: 1,//页码
            pageRow: 50,//每页条数
            id:'',//政策id
            dept: '',//部门
            policyTitle: '',//政策名称
            policyLevel: '',//政策等级
            minRevenue: '',//创效下限
            maxRevenue: '',//创效上限
            collectTime: '',//收集年份
            schedule: '',//项目进度
            category: ''//政策类别
        },//搜索条件表单
        formLabelWidth: '110px',
        tempArticle: {
          id: "",
          title: "",
          url: "",
          content: ""
        },
        isIndeterminate: true, //多选框控制标志
        checkIdsAll: false,  
        checkedCondition: false,//当前条件所有数据勾选框的勾选标志
        checkedAll:false,//所有数据勾选框的勾选标志
        checkedIds:[],//被选中的id
        allIds:[],//当前页内所有id,
        excelLabelName:[
            "所属欢乐谷","政策编号","政策名称","政策链接","政策类别","政策等级",
            "政策简述","政策发布渠道","政策出台部门（省/市、区级）","政策出台时间",
            "政策收集时间","政策收集人","直接/间接创效","预计直接创效（万元）",
            "政策利用条件","是否需集团本部协助","建议承办部门（责任人）","承办措施",
            "负责人", "负责人联系方式","是否销项","批注"
        ],
        excelLabel:[
            'dept', 'policyId', 'policyTitle','policyUrl','category', 'policyLevel',
            'policyResume','releaseChannel','governmentBranch', 'releaseTime',
            'collectTime','collector','revenueWay','revenue', 'condition', 'assist',
            'department','measure','acceptor','acceptorPhone', 'schedule', 'annotation'
        ],
        json_fields:{ //导出的excel的表头信息
            "所属欢乐谷": "dept",
            "政策编号"  : "policyId",
            "政策名称"  : "policyTitle",
            "政策链接"  : "policyUrl",
            "政策类别"  : "category",
            "政策等级"  : "policyLevel",
            "政策简述"  : "policyResume",
            "政策发布渠道"              : "releaseChannel",
            "政策出台部门（省/市、区级）": "governmentBranch",
            "政策出台时间"              : "releaseTime",
            "政策收集时间"              : "collectTime",
            "政策收集人"                : "collector",
            "直接/间接创效"             : "revenueWay",
            "预计直接创效（万元）"     : "revenue",
            "政策利用条件"          : "condition",
            "是否需集团本部协助"     : "assist",
            "建议承办部门（责任人）" : "department",
            "承办措施"              : "measure",
            "负责人"                : "acceptor",
            "负责人联系方式"         : "acceptorPhone",
            "是否销项"              : "schedule",
            "批注"                  : "annotation",                 
        },
        excelData:[],
        deptOptions:["本部","深圳","北京","成都","上海","武汉","天津","重庆","南京"],
        levelOptions:["","★","★★","★★★","★★★★"],
        assistOptions:["","否","是"],
        revenueWayOptions:["","间接创效","直接创效"]
    }
    },
    created() {
        if( this.$route.query != null)
        {
            if(this.$route.query.dept != null )
                this.searchForm.dept     = this.$route.query.dept
            if(this.$route.query.category != null )
                this.searchForm.category = this.$route.query.category
            if(this.$route.query.id != null )
                this.searchForm.id = this.$route.query.id
        }  
        else
        {
            this.searchForm.dept = ''
            this.searchForm.category = ''
            this.searchForm.id=''
        }      
        this.getList();
    },
    methods: {
      getList() {
        //查询列表
        if (!this.hasPerm('policy:list')) {
            return
        }
        this.listLoading = true;
        this.api({
            url: "/policy/listPolicy",
            method: "get",
            params: this.searchForm
        }).then(data => {
            this.listLoading = false;
            if(this.searchForm.dept == '9')
            {
                this.searchForm.dept = ''
            }
            this.list = data.list;
            this.totalCount = data.totalCount;
            
            for(var i = 0; i < this.list.length; i++)
            {
                this.allIds.push(this.list[i].id)
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
        this.getList();
      },
      handleFilter() {
        //改变了查询条件,从第一页开始查询
        this.searchForm.pageNum = 1
        this.getList()
      },
      getIndex($index) {
        //表格序号
        return (this.searchForm.pageNum - 1) * this.searchForm.pageRow + $index + 1
      },
      toPolicy(url) {
          //跳转政策链接地址
          window.open(url, "_blank");
      },
      showDetail(target,index) {
            this.dialogFormIndex = index          
            if(target == 'policy') {
              //显示政策的详情           
                this.form.id = this.list[index].id
                this.form.dept = this.list[index].dept
                this.form.policyTitle = this.list[index].policyTitle
                this.form.policyUrl = this.list[index].policyUrl
                this.form.policyId = this.list[index].policyId  
                this.form.category = this.list[index].category
                this.form.policyLevel = this.list[index].policyLevel
                this.form.policyResume = this.list[index].policyResume
                this.form.releaseChannel = this.list[index].releaseChannel
                this.form.governmentBranch = this.list[index].governmentBranch
                this.form.releaseTime = new Date(this.list[index].releaseTime)
                this.form.collectTime = new Date(this.list[index].collectTime)
                this.form.collector = this.list[index].collector
                this.form.revenueWay = this.list[index].revenueWay
                this.form.revenue = this.list[index].revenue              
                this.form.assist = this.list[index].assist
                this.form.department = this.list[index].department
                this.form.acceptor = this.list[index].acceptor
                this.form.accpetorPhone = this.list[index].acceptorPhone
                this.form.schedule = this.list[index].schedule
                this.dialogPolicyFormVisible = true
            }
            
            var paramId = {
                policyId : index
            }

            this.addOfPolicyId = index //保存政策id，为后续的添加操作作准备

            if(target == 'condition') {
                this.conditionForm = [] //清空数组再push，防止多次点击详情造成冗余
                this.api({
                    url: "/policy/listPolicyConditionById",
                    method: "post",
                    data: paramId
                }).then(data => {
                    this.conditionForm = data.list;
                    this.dialogConditionFormVisible = true
                })
            }
             
            if(target == 'measure') {

                this.measureForm = [] //清空数组再push，防止多次点击详情造成冗余

                this.api({
                    url: "/policy/listPolicyMeasureById",
                    method: "post",
                    data: paramId
                }).then(data => {
                    this.measureForm = data.list;
                    this.dialogMeasureFormVisible = true
                })   
            }
             
            if(target == 'annotation') {

                this.annotationForm = {} 

                this.api({
                    url: "/policy/listPolicyAnnotationById",
                    method: "post",
                    data: paramId
                }).then(data => {
                    if( data.item != null)
                        this.annotationForm = data.item;
                    else 
                        this.annotationForm = {
                            id: ''
                        }    
                    this.dialogAnnotationFormVisible = true
                })   
            } 

      },
      addItems(target) {
        //判断是否拥有添加权限
        if (!this.hasPerm('policy:add')) {
          return
        }
        this.editDialog(target)  
        if( target == "condition") {
            //增加可利用条款填写框
            this.conditionForm.push({
                id: "",
                conditions: ""
            })
        }

        if( target == "measure") {
            //增加可利用条款利用措施填写框
            this.measureForm.push({
                id: "",
                measure: ""
            })
        }
    
      },
      handleUpdate(target,Id,index)
      {      
        if(target == 'condition' && this.dialogConditionFormEditable == false ) {
            //如果id为空，说明这次操作为添加操作
            if(this.conditionForm[index].id == '' && this.hasPerm('policy:add'))
            {
                var dataCondition = {
                    policyId : this.addOfPolicyId,
                    condition : this.conditionForm[index].conditions
                }
                this.api({
                    url: "/policy/addPolicyCondition",
                    method: "post",
                    data: dataCondition
                }).then(data => {
                    this.$message({
                        message: '政策的可利用条款添加成功',
                        type: 'success'
                    });
                    this.dialogConditionFormVisible = false
                    this.showDetail("condition",this.addOfPolicyId)
                })
            }
            else if( this.hasPerm('policy:update') )//如果id不为空，则此次操作为修改操作
            {
                this.api({
                    url: "/policy/updatePolicyCondition",
                    method: "post",
                    data: this.conditionForm[index]
                }).then(data => {
                    this.$message({
                        message: '政策的可利用条款修改成功',
                        type: 'success'
                    });
                    this.dialogConditionFormVisible = false
                    this.showDetail("condition",this.addOfPolicyId)
                })
            }

        }

        if(target == 'measure' && this.dialogMeasureFormEditable == false) {
            //如果id为空，说明这次操作为添加操作
            if(this.measureForm[index].id == '' && this.hasPerm('policy:add'))
            {
                var dataMeasure = {
                    policyId : this.addOfPolicyId,
                    measure : this.measureForm[index].measure
                }
                this.api({
                    url: "/policy/addPolicyMeasure",
                    method: "post",
                    data: dataMeasure
                }).then(data => {
                    this.$message({
                        message: '政策的可利用条款的利用措施添加成功',
                        type: 'success'
                    });
                    this.dialogMeasureFormVisible = false
                    this.showDetail("measure",this.addOfPolicyId)
                })
            }
            else if( this.hasPerm('policy:update') )
            {
                this.api({
                    url: "/policy/updatePolicyMeasure",
                    method: "post",
                    data: this.measureForm[index]
                }).then(data => {
                    this.$message({
                        message: '政策的可利用条款的利用措施修改成功',
                        type: 'success'
                    });
                    this.dialogMeasureFormVisible = false
                    this.showDetail("measure",this.addOfPolicyId)
                })
            }
        }
        if(!this.hasPerm('policy:annotate'))
        {
            return
        }
        if(target == 'annotation' && this.dialogAnnotationFormEditable == false) {
            //如果id为空，说明这次操作为添加操作
            if(this.annotationForm.id == '')
            {
                var dataAnnotation = {
                    policyId : this.addOfPolicyId,
                    annotation : this.annotationForm.annotation
                }
                this.api({
                    url: "/policy/addPolicyAnnotation",
                    method: "post",
                    data: dataAnnotation
                }).then(data => {
                    this.$message({
                        message: '政策的批注信息添加成功',
                        type: 'success'
                    });
                    this.dialogAnnotationFormVisible = false
                    this.showDetail("annotation",this.addOfPolicyId)
                })
            }
            else
            {
                this.api({
                    url: "/policy/updatePolicyAnnotation",
                    method: "post",
                    data: this.annotationForm
                }).then(data => {
                    this.$message({
                        message: '政策的批注信息修改成功',
                        type: 'success'
                    });
                    this.dialogAnnotationFormVisible = false
                    this.showDetail("annotation",this.addOfPolicyId)
                })
            }
        }
        
        this.$forceUpdate(); //强制刷新，解决页面不会重新渲染的问题

      },
      handleDelete(target,id,index)
      {
          //删除政策记录
        if (!this.hasPerm('policy:update')) {
          return
        }

        this.$confirm('此操作将永久删除所选记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            
            if(target == 'condition') {
                var data = {
                    deleteId : id
                }
                this.api({
                    url: "/policy/deletePolicyCondition",
                    method: "post",
                    data: data
                }).then(data => {
                    this.$message({
                        message: '政策的可利用条款删除成功',
                        type: 'success'
                    });
                    this.showDetail("condition",this.addOfPolicyId)
                })
            }

            if(target == 'measure') {
                var data = {
                    deleteId : id
                }
                this.api({
                    url: "/policy/deletePolicyMeasure",
                    method: "post",
                    data: data
                }).then(data => {
                    this.$message({
                        message: '政策的可利用条款的利用措施删除成功',
                        type: 'success'
                    });
                    this.showDetail("measure",this.addOfPolicyId)
                })
            }   

            if(target == 'policy') {
                var data = {
                    id : this.list[index].id,
                }
                this.api({
                    url: "/policy/deletePolicy",
                    method: "post",
                    data: data
                }).then(data => {
                    this.$message({
                        message: '政策记录删除成功',
                        type: 'success'
                    });
                    this.getList()
                })
            }   

        }).catch(() => {
            this.$message({
                type: 'info',
                message: '已取消删除'
            });          
        });

      },
      idController(prefix,index){

          //组件id动态创建器，perfix为id的前缀，index的当前组件在组件列表里的序号
          return prefix+""+index;

      },     
      formatCurrency(s, n) {
           //格式化数据，小数部分不做处理，对整数部分进行千分位格式化的处理，如果有符号，正常保留
           n = n > 0 && n <= 20 ? n : 2;  
           s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
           var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];  
           var t = "";  
           for (var i = 0; i < l.length; i++) {  
                t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
            }  
            return t.split("").reverse().join("") + "." + r;

       },
        editDialog(target) {
            if( target == "policy") {
                this.dialogPolicyFormEditable = false;
            }
            else if( target == "condition") {
                this.dialogConditionFormEditable = false;
            }
            else if( target == "measure") {
                this.dialogMeasureFormEditable = false;
            }
            else if( target == "annotation") {
                this.dialogAnnotationFormEditable = false;
            }

        },
        closeDialog(target) {
            if(target == "addPolicy")
            {
                this.addForm = {}   
                this.$refs["addForm"].resetFields();  
            }
            else if( target == "policy") {
                this.dialogPolicyFormEditable = true;
            }
            else if( target == "condition") {
                this.dialogConditionFormEditable = true;
            }
            else if( target == "measure") {
                this.dialogMeasureFormEditable = true;
            }
            else if( target == "annotation")
            {
                this.dialogAnnotationFormEditable = true;
            }
            

        },  
        showAddDialog() {
            this.addButtonVisible = false
            this.dialogAddFormVisible = true
        },
        showExcelDialog() {
            this.addButtonVisible = false
            this.dialogExcelFormVisible = true
        },
        createPolicy() {
            //判断是否拥有添加权限
            if (!this.hasPerm('policy:add')) {
            return
            }

            this.$refs["addForm"].validate((valid) => {
                if (valid) {
                    //保存新政策
                    this.api({
                        url: "/policy/addPolicy",
                        method: "post",
                        data: this.addForm
                    }).then(() => {
                        this.$message({
                            message: '政策录入成功',
                            type: 'success'
                        });
                        this.addForm = {} //清空添加表单中的数据
                        this.getList();
                        this.dialogAddFormVisible = false
                    })
                } else {
                    this.$message({
                        message: '请输入正确的政策信息再进行添加操作',
                        type: 'success'
                    });
                }
            });
        },
        renderTime(date) {
            var dateee = new Date(date).toJSON();
            return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '') 
        },
        updatePolicy() {
            //判断是否拥有添加权限
            if (!this.hasPerm('policy:update')) {
            return
            }
            //更新政策的基本信息
            this.form.releaseTime = this.renderTime(this.form.releaseTime)
            this.form.collectTime = this.renderTime(this.form.collectTime)
            console.log(this.form.releaseTime)
            this.api({
                url: "/policy/updatePolicy",
                method: "post",
                data: this.form
            }).then(() => {
                this.$message({
                    message: '政策修改成功',
                    type: 'success'
                });
                this.form = {} //清空添加表单中的数据
                this.getList();
                this.dialogPolicyFormVisible = false
                
            })
        },
        uploadFileAddPolicy() {
            this.fileUploadBtnText = '正在上传'
            this.importBtnDisabled = true
            this.loading = true
            this.$refs.upload.submit()
        },
        fileUploadSuccess(response) {
            if (response.code == 100) {
                this.fileUploadBtnText = '导入成功'
                this.loading = false
                this.dialogExcelFormVisible = false;
                this.$message({
                    type: 'success',
                    message: '批量导入成功'
                })
                this.$refs.upload.clearFiles();
                this.getList()
            }
        },
        fileUploadError(response) {
            this.fileUploadBtnText = '导入失败'
            this.loading = false
            this.$message({
            type: 'error',
            message: '批量导入失败'
            })
        },
        resetSearchForm() {
            this.searchForm.dept='',        //部门
            this.searchForm.policyTitle='', //政策名称
            this.searchForm.policyLevel='', //政策等级
            this.searchForm.minRevenue='',  //创效下限
            this.searchForm.maxRevenue='',  //创效上限
            this.searchForm.category='',    //收集政策类别
            this.searchForm.schedule=''     //项目进度
            this.getList()
        },
        handleCheckIdsAllChange(val) {
            this.checkedIds = val ? this.allIds : [];
            this.isIndeterminate = false;           
        },
        handleCheckedIdsChange(value) {
            let checkedCount = value.length;
            this.checkIdsAll = checkedCount === this.allIds.length;
            this.isIndeterminate = checkedCount > 0 && checkedCount < this.allIds.length;
        },
        handleCheckCurConditionChange() {

        },
        handleCheckAllChange() {E

        },
        exportExcel() {
            var excelCondition = {
                ids : ''
            }
            if(this.checkedAll)
            {
                excelCondition = {}
            }
            else if(this.checkedCondition)
            {
                excelCondition = {
                    dept: this.searchForm.dept,
                    policyTitle: this.searchForm.policyTitle,
                    policyLevel: this.searchForm.policyLevel,
                    minRevenue: this.searchForm.minRevenue,
                    maxRevenue: this.searchForm.maxRevenue,
                    category: this.searchForm.category,
                    schedule: this.searchForm.schedule
                }
            }
            else{
                if(this.checkedIds.length == 0)
                {
                    this.$message.error('请勾选要到导出的政策记录');
                    return ; 
                }
                excelCondition.ids = '('
                for(var i = 0; i < this.checkedIds.length; i++)
                {
                    if( i != 0)
                        excelCondition.ids = excelCondition.ids + ' , '
                    excelCondition.ids = excelCondition.ids+this.checkedIds[i]
                }
                excelCondition.ids = excelCondition.ids + ')'
            }
            
            this.api({
                url: "/easyExcel/exportPolicy",
                method: "post",
                data: excelCondition
             }).then(data => {
                this.excelData = []
                for(var i = 0; i < data.list.length; i++)
                {
                    var cur = {
                        dept : this.deptOptions[data.list[i].dept],
                        policyId : data.list[i].policyId,
                        policyTitle : data.list[i].policyTitle,
                        policyUrl : data.list[i].policyUrl,
                        category : data.list[i].category,
                        policyLevel : this.levelOptions[data.list[i].policyLevel],
                        policyResume : data.list[i].policyResume,
                        releaseChannel : data.list[i].releaseChannel,
                        governmentBranch : data.list[i].governmentBranch,
                        releaseTime : this.renderTime(data.list[i].releaseTime).split(" ")[0],
                        collectTime : this.renderTime(data.list[i].collectTime).split(" ")[0],
                        collector : data.list[i].collector,
                        revenueWay : this.revenueWayOptions[data.list[i].revenueWay],
                        revenue : data.list[i].revenue,
                        condition : data.list[i].condition,
                        assist : this.assistOptions[data.list[i].assist],
                        department : data.list[i].department,
                        measure : data.list[i].measure,
                        acceptor : data.list[i].acceptor,
                        acceptorPhone : data.list[i].acceptorPhone,
                        schedule : data.list[i].schedule,
                        annotation : data.list[i].annotation
                    }
                    this.excelData[i] = cur
                } 
                require.ensure([], () => {
                    const { export_json_to_excel } = require('../../vendor/Export2Excel');
                    //表头
                    const tHeader = this.excelLabelName;
                    //对应的标签
                    const filterVal = this.excelLabel;
                    //标签对应的内容  是一个数组结构            
                    const list = this.excelData;
                    const data = this.formatJson(filterVal, list);
                    export_json_to_excel(tHeader, data, '政策记录');
                })
            })   
        },
        formatJson(filterVal, jsonData){
            return jsonData.map(v => filterVal.map(j => v[j]))
        }
    },
    watch: {
        '$route'(to, from) {
            if( this.$route.query != null)
            {
                this.searchForm.dept     = this.$route.query.dept
                this.searchForm.category = this.$route.query.category
            }  
            else
            {
                this.searchForm.dept = ''
                this.searchForm.category = ''
            }      
            this.getList();
        }
    }
  }
</script>
