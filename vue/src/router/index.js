import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading
/* layout */
import Layout from '../views/layout/Layout'

const _import = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)
export const constantRouterMap = [
  {path: '/login', component: _import('login/index'), hidden: true},
  {path: '/404', component: _import('404'), hidden: true},
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    meta: {title: '首页'},
    hidden: true,
    children: [{
      path: 'dashboard', component: _import('dashboard/index')
    }]
  }
]
export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
export const asyncRouterMap = [
  {
    path: '/system',
    component: Layout,
    redirect: '/system/',
    name: '',
    meta: {title: '政策研究', icon: 'tree'},
    children: [
      {
        path: 'policy',
        name: '政策详情',
        component: _import('policy/policy'),
        meta: {title: '政策详情', icon: 'form'},
        menu: 'policy'
      },
      {
        path: 'annotation',
        name: '批注详情',
        component: _import('policy/annotation'),
        meta: {title: '批注详情', icon: 'form'},
        menu: 'policy'
      },
    ]
  },
  {
    path: '/statistics',
    component: Layout,
    redirect: '/statistics/',
    name: '',
    meta: {title: '数据统计', icon: 'table'},
    menu: 'statistics',
    children: [
      {
        path: 'numChartPie', 
        name: '数量统计', 
        component: _import('statistics/numChartPie'), 
        meta: {title: '数量统计', icon: 'form'}, 
        menu: 'statistics'
      },
      {
        path: 'revenueChartPie', 
        name: '创收统计', 
        component: _import('statistics/revenueChartPie'), 
        meta: {title: '创收统计', icon: 'form'}, 
        menu: 'statistics'
      }
      /*{
        path: 'numChartMonthLine', 
        name: '数量月对比', 
        component: _import('statistics/numChartMonthLine'), 
        meta: {title: '数量月对比', icon: 'form'}, 
        menu: 'statistics'
      },
      {
        path: 'revenueChartMonthLine', 
        name: '创收月对比', 
        component: _import('statistics/revenueChartMonthLine'), 
        meta: {title: '创收月对比', icon: 'form'}, 
        menu: 'statistics'
      },
      {
        path: 'numChartYearLine', 
        name: '数量年对比', 
        component: _import('statistics/numChartYearLine'), 
        meta: {title: '数量年对比', icon: 'form'}, 
        menu: 'statistics'
      },
      {
        path: 'revenueChartYearLine', 
        name: '创收年对比', 
        component: _import('statistics/revenueChartYearLine'), 
        meta: {title: '创收年对比', icon: 'form'}, 
        menu: 'statistics'
      }*/
    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user/',
    name: '',
    meta: {title: '用户权限', icon: 'table'},
    children: [
      {
        path: '', name: '用户列表', component: _import('user/user'), meta: {title: '用户列表', icon: 'user'}, menu: 'user'
      },
      {
        path: 'role',
        name: '权限管理',
        component: _import('user/role'),
        meta: {title: '权限管理', icon: 'password'},
        menu: 'role'
      },
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]
