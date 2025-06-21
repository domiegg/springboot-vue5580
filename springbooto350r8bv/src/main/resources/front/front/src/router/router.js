import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Forum from '../pages/forum/list'
import ForumAdd from '../pages/forum/add'
import ForumDetail from '../pages/forum/detail'
import MyForumList from '../pages/forum/myForumList'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import nonghuList from '../pages/nonghu/list'
import nonghuDetail from '../pages/nonghu/detail'
import nonghuAdd from '../pages/nonghu/add'
import zhuanjiaList from '../pages/zhuanjia/list'
import zhuanjiaDetail from '../pages/zhuanjia/detail'
import zhuanjiaAdd from '../pages/zhuanjia/add'
import zhishifenleiList from '../pages/zhishifenlei/list'
import zhishifenleiDetail from '../pages/zhishifenlei/detail'
import zhishifenleiAdd from '../pages/zhishifenlei/add'
import zhishikuList from '../pages/zhishiku/list'
import zhishikuDetail from '../pages/zhishiku/detail'
import zhishikuAdd from '../pages/zhishiku/add'
import nongzuowuList from '../pages/nongzuowu/list'
import nongzuowuDetail from '../pages/nongzuowu/detail'
import nongzuowuAdd from '../pages/nongzuowu/add'
import tianqiyubaoList from '../pages/tianqiyubao/list'
import tianqiyubaoDetail from '../pages/tianqiyubao/detail'
import tianqiyubaoAdd from '../pages/tianqiyubao/add'
import chatmessageList from '../pages/chatmessage/list'
import chatmessageDetail from '../pages/chatmessage/detail'
import chatmessageAdd from '../pages/chatmessage/add'
import friendList from '../pages/friend/list'
import friendDetail from '../pages/friend/detail'
import friendAdd from '../pages/friend/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import systemintroList from '../pages/systemintro/list'
import systemintroDetail from '../pages/systemintro/detail'
import systemintroAdd from '../pages/systemintro/add'
import systemnoticeList from '../pages/systemnotice/list'
import systemnoticeDetail from '../pages/systemnotice/detail'
import systemnoticeAdd from '../pages/systemnotice/add'
import discusszhishikuList from '../pages/discusszhishiku/list'
import discusszhishikuDetail from '../pages/discusszhishiku/detail'
import discusszhishikuAdd from '../pages/discusszhishiku/add'
import discusstianqiyubaoList from '../pages/discusstianqiyubao/list'
import discusstianqiyubaoDetail from '../pages/discusstianqiyubao/detail'
import discusstianqiyubaoAdd from '../pages/discusstianqiyubao/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'forum',
					component: Forum
				},
				{
					path: 'forumAdd',
					component: ForumAdd
				},
				{
					path: 'forumDetail',
					component: ForumDetail
				},
				{
					path: 'myForumList',
					component: MyForumList
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'nonghu',
					component: nonghuList
				},
				{
					path: 'nonghuDetail',
					component: nonghuDetail
				},
				{
					path: 'nonghuAdd',
					component: nonghuAdd
				},
				{
					path: 'zhuanjia',
					component: zhuanjiaList
				},
				{
					path: 'zhuanjiaDetail',
					component: zhuanjiaDetail
				},
				{
					path: 'zhuanjiaAdd',
					component: zhuanjiaAdd
				},
				{
					path: 'zhishifenlei',
					component: zhishifenleiList
				},
				{
					path: 'zhishifenleiDetail',
					component: zhishifenleiDetail
				},
				{
					path: 'zhishifenleiAdd',
					component: zhishifenleiAdd
				},
				{
					path: 'zhishiku',
					component: zhishikuList
				},
				{
					path: 'zhishikuDetail',
					component: zhishikuDetail
				},
				{
					path: 'zhishikuAdd',
					component: zhishikuAdd
				},
				{
					path: 'nongzuowu',
					component: nongzuowuList
				},
				{
					path: 'nongzuowuDetail',
					component: nongzuowuDetail
				},
				{
					path: 'nongzuowuAdd',
					component: nongzuowuAdd
				},
				{
					path: 'tianqiyubao',
					component: tianqiyubaoList
				},
				{
					path: 'tianqiyubaoDetail',
					component: tianqiyubaoDetail
				},
				{
					path: 'tianqiyubaoAdd',
					component: tianqiyubaoAdd
				},
				{
					path: 'chatmessage',
					component: chatmessageList
				},
				{
					path: 'chatmessageDetail',
					component: chatmessageDetail
				},
				{
					path: 'chatmessageAdd',
					component: chatmessageAdd
				},
				{
					path: 'friend',
					component: friendList
				},
				{
					path: 'friendDetail',
					component: friendDetail
				},
				{
					path: 'friendAdd',
					component: friendAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'systemintro',
					component: systemintroList
				},
				{
					path: 'systemintroDetail',
					component: systemintroDetail
				},
				{
					path: 'systemintroAdd',
					component: systemintroAdd
				},
				{
					path: 'systemnotice',
					component: systemnoticeList
				},
				{
					path: 'systemnoticeDetail',
					component: systemnoticeDetail
				},
				{
					path: 'systemnoticeAdd',
					component: systemnoticeAdd
				},
				{
					path: 'discusszhishiku',
					component: discusszhishikuList
				},
				{
					path: 'discusszhishikuDetail',
					component: discusszhishikuDetail
				},
				{
					path: 'discusszhishikuAdd',
					component: discusszhishikuAdd
				},
				{
					path: 'discusstianqiyubao',
					component: discusstianqiyubaoList
				},
				{
					path: 'discusstianqiyubaoDetail',
					component: discusstianqiyubaoDetail
				},
				{
					path: 'discusstianqiyubaoAdd',
					component: discusstianqiyubaoAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
