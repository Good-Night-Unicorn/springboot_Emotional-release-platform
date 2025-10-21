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
import adminexam from '@/views/modules/exampaperlist/exam'
	import qingxujianyi from '@/views/modules/qingxujianyi/list'
	import qingxuxuanxie from '@/views/modules/qingxuxuanxie/list'
	import xuesheng from '@/views/modules/xuesheng/list'
	import zixunfuwu from '@/views/modules/zixunfuwu/list'
	import examquestion from '@/views/modules/examquestion/list'
	import biaoqianxinxi from '@/views/modules/biaoqianxinxi/list'
	import discussqingxuwenzhang from '@/views/modules/discussqingxuwenzhang/list'
	import yinlexinxi from '@/views/modules/yinlexinxi/list'
	import sensitivewords from '@/views/modules/sensitivewords/list'
	import qingxufenlei from '@/views/modules/qingxufenlei/list'
	import qingxurizhi from '@/views/modules/qingxurizhi/list'
	import syslog from '@/views/modules/syslog/list'
	import exampaper from '@/views/modules/exampaper/list'
	import youxixinxi from '@/views/modules/youxixinxi/list'
	import yuyuezixunshi from '@/views/modules/yuyuezixunshi/list'
	import discussqingxuxuanxie from '@/views/modules/discussqingxuxuanxie/list'
	import qingxuwenzhang from '@/views/modules/qingxuwenzhang/list'
	import zixunshi from '@/views/modules/zixunshi/list'
	import discussyinlexinxi from '@/views/modules/discussyinlexinxi/list'
	import discussqingxurizhi from '@/views/modules/discussqingxurizhi/list'
	import examquestionbank from '@/views/modules/examquestionbank/list'
	import config from '@/views/modules/config/list'
	import examrecord from '@/views/modules/examrecord/list'
	import discussyouxixinxi from '@/views/modules/discussyouxixinxi/list'


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
		path: '/qingxujianyi',
		name: '情绪建议',
		component: qingxujianyi
	}
	,{
		path: '/qingxuxuanxie',
		name: '情绪宣泄',
		component: qingxuxuanxie
	}
	,{
		path: '/xuesheng',
		name: '学生',
		component: xuesheng
	}
	,{
		path: '/zixunfuwu',
		name: '咨询服务',
		component: zixunfuwu
	}
	,{
		path: '/examquestion',
		name: '试题管理',
		component: examquestion
	}
	,{
		path: '/biaoqianxinxi',
		name: '标签信息',
		component: biaoqianxinxi
	}
	,{
		path: '/discussqingxuwenzhang',
		name: '情绪文章评论',
		component: discussqingxuwenzhang
	}
	,{
		path: '/yinlexinxi',
		name: '音乐信息',
		component: yinlexinxi
	}
	,{
		path: '/sensitivewords',
		name: '敏感词',
		component: sensitivewords
	}
	,{
		path: '/qingxufenlei',
		name: '情绪分类',
		component: qingxufenlei
	}
	,{
		path: '/qingxurizhi',
		name: '情绪日志',
		component: qingxurizhi
	}
	,{
		path: '/syslog',
		name: '系统日志',
		component: syslog
	}
	,{
		path: '/exampaper',
		name: '情绪测评管理',
		component: exampaper
	}
	,{
		path: '/youxixinxi',
		name: '游戏信息',
		component: youxixinxi
	}
	,{
		path: '/yuyuezixunshi',
		name: '预约咨询师',
		component: yuyuezixunshi
	}
	,{
		path: '/discussqingxuxuanxie',
		name: '情绪宣泄评论',
		component: discussqingxuxuanxie
	}
	,{
		path: '/qingxuwenzhang',
		name: '情绪文章',
		component: qingxuwenzhang
	}
	,{
		path: '/zixunshi',
		name: '咨询师',
		component: zixunshi
	}
	,{
		path: '/discussyinlexinxi',
		name: '音乐信息评论',
		component: discussyinlexinxi
	}
	,{
		path: '/discussqingxurizhi',
		name: '情绪日志评论',
		component: discussqingxurizhi
	}
	,{
		path: '/examquestionbank',
		name: '试题库管理',
		component: examquestionbank
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/examrecord',
		name: '考试记录',
		component: examrecord
	}
	,{
		path: '/discussyouxixinxi',
		name: '游戏信息评论',
		component: discussyouxixinxi
	}
	]
	},
	{
		path: '/adminexam',
		name: 'adminexam',
		component: adminexam,
		meta: {icon:'', title:'adminexam'}
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
