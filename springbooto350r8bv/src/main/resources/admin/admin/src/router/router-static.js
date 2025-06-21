import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
    import news from '@/views/modules/news/list'
    import nonghu from '@/views/modules/nonghu/list'
    import discusszhishiku from '@/views/modules/discusszhishiku/list'
    import zhuanjia from '@/views/modules/zhuanjia/list'
    import tianqiyubao from '@/views/modules/tianqiyubao/list'
    import zhishiku from '@/views/modules/zhishiku/list'
    import systemnotice from '@/views/modules/systemnotice/list'
    import nongzuowu from '@/views/modules/nongzuowu/list'
    import forum from '@/views/modules/forum/list'
    import zhishifenlei from '@/views/modules/zhishifenlei/list'
    import systemintro from '@/views/modules/systemintro/list'
    import discusstianqiyubao from '@/views/modules/discusstianqiyubao/list'
    import config from '@/views/modules/config/list'
    import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
    path: '/',
    name: '系统首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '系统首页',
      component: Home,
      meta: {icon:'', title:'center', affix: true}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
      ,{
	path: '/news',
        name: '农业新闻',
        component: news
      }
      ,{
	path: '/nonghu',
        name: '农户',
        component: nonghu
      }
      ,{
	path: '/discusszhishiku',
        name: '知识库评论',
        component: discusszhishiku
      }
      ,{
	path: '/zhuanjia',
        name: '专家',
        component: zhuanjia
      }
      ,{
	path: '/tianqiyubao',
        name: '天气预报',
        component: tianqiyubao
      }
      ,{
	path: '/zhishiku',
        name: '知识库',
        component: zhishiku
      }
      ,{
	path: '/systemnotice',
        name: '系统公告',
        component: systemnotice
      }
      ,{
	path: '/nongzuowu',
        name: '农作物',
        component: nongzuowu
      }
      ,{
	path: '/forum',
        name: '论坛交流',
        component: forum
      }
      ,{
	path: '/zhishifenlei',
        name: '知识分类',
        component: zhishifenlei
      }
      ,{
	path: '/systemintro',
        name: '系统简介',
        component: systemintro
      }
      ,{
	path: '/discusstianqiyubao',
        name: '天气预报评论',
        component: discusstianqiyubao
      }
      ,{
	path: '/config',
        name: '轮播图管理',
        component: config
      }
      ,{
	path: '/newstype',
        name: '农业新闻分类',
        component: newstype
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}
export default router;
