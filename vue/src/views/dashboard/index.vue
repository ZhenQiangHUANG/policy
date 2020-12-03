<template>
  <div class="dashboard-container">
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="1" style="margin-left:35%">基础清单</el-menu-item>
      <el-menu-item index="2">应用清单</el-menu-item>
    </el-menu>
    <el-form :hidden="baseFormHidden" style="margin-left:20%">
      <ul class="form">
        <li width="100px"><a @click="toPage('0','人力')">人力&ensp;&ensp;</a></li>  
        <li width="100px"><a @click="toPage('0','财税')">财税&ensp;&ensp;&ensp;&ensp;</a></li>  
        <li width="100px"><a @click="toPage('0','行业')">行业</a></li>  
      </ul>  
      <ul class="form">
        <li width="100px"><a @click="toPage('0','新基建')">新基建</a></li>  
        <li width="100px"><a @click="toPage('0','安全环保')">安全环保</a></li>  
        <li width="100px"><a @click="toPage('0','其他')">其他</a></li>  
      </ul>
      <ul class="form">
        <li width="100px"><a @click="toPage('0','')">全部</a></li>  
      </ul>
    </el-form>   
    <el-form :hidden="appFormHidden" style="margin-left:20%"> 
      <ul>
        <li><a @click="toPage('1','')">深圳</a></li>  
        <li><a @click="toPage('2','')">北京</a></li>  
        <li><a @click="toPage('3','')">成都</a></li>  
      </ul>
      <ul>
        <li><a @click="toPage('4','')">上海</a></li>  
        <li><a @click="toPage('5','')">武汉</a></li>  
        <li><a @click="toPage('6','')">天津</a></li>  
      </ul> 
      <ul>
        <li><a @click="toPage('7','')">重庆</a></li>  
        <li><a @click="toPage('8','')">南京</a></li>   
        <li><a @click="toPage('9','')">全部</a></li>
      </ul> 
    </el-form>        
  </div>
</template>
<style scoped>
ul,ol {
  margin:0;
  padding:0;
  list-style:none;
}
form ul {
  width: 700px;
}
form li:before {
  content: "";
  width: 6px;
  height: 6px;
  display: inline-block;
  border-radius: 50%;
  background: #4F8EFF;
  vertical-align: middle;
  margin-right: 14px;
}
form li {
  display: inline;
  list-style-type: none;
  margin:20px 70px 20px 70px;
  line-height:58px;
  cursor:pointer;
  max-width: 150px;
  font-weight: 400;
}
form li span {
  width: 150px;
}

li:hover{
  color: red;
}

</style>
<script>
  export default {
    data() {
      return {
        activeIndex: '1',
        baseFormHidden: false,
        appFormHidden: true
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        if(key == 1)
        {
          this.baseFormHidden = false
          this.appFormHidden = true
        }
        else if(key == 2)
        {
          this.baseFormHidden = true
          this.appFormHidden = false
        }
      },
      toPage(dept,category) {        
        //跳转到政策详情页面
        this.$router.push({
          path: '/system/policy',
          query: {
            dept: dept,
            category: category
          }
        })
      }
    }
  }
</script>
