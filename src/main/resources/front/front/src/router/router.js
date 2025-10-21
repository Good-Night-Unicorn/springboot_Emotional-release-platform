import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import ExamPaper from '../pages/exam/examPaper'
import Exam from '../pages/exam/exam'
import ExamList from '../pages/exam/examList'
import ExamRecord from '../pages/exam/examRecord'
import Storeup from '../pages/storeup/list'
import payList from '../pages/pay'

import xueshengList from '../pages/xuesheng/list'
import xueshengDetail from '../pages/xuesheng/detail'
import xueshengAdd from '../pages/xuesheng/add'
import zixunshiList from '../pages/zixunshi/list'
import zixunshiDetail from '../pages/zixunshi/detail'
import zixunshiAdd from '../pages/zixunshi/add'
import zixunfuwuList from '../pages/zixunfuwu/list'
import zixunfuwuDetail from '../pages/zixunfuwu/detail'
import zixunfuwuAdd from '../pages/zixunfuwu/add'
import yuyuezixunshiList from '../pages/yuyuezixunshi/list'
import yuyuezixunshiDetail from '../pages/yuyuezixunshi/detail'
import yuyuezixunshiAdd from '../pages/yuyuezixunshi/add'
import biaoqianxinxiList from '../pages/biaoqianxinxi/list'
import biaoqianxinxiDetail from '../pages/biaoqianxinxi/detail'
import biaoqianxinxiAdd from '../pages/biaoqianxinxi/add'
import qingxujianyiList from '../pages/qingxujianyi/list'
import qingxujianyiDetail from '../pages/qingxujianyi/detail'
import qingxujianyiAdd from '../pages/qingxujianyi/add'
import qingxuxuanxieList from '../pages/qingxuxuanxie/list'
import qingxuxuanxieDetail from '../pages/qingxuxuanxie/detail'
import qingxuxuanxieAdd from '../pages/qingxuxuanxie/add'
import qingxuwenzhangList from '../pages/qingxuwenzhang/list'
import qingxuwenzhangDetail from '../pages/qingxuwenzhang/detail'
import qingxuwenzhangAdd from '../pages/qingxuwenzhang/add'
import qingxufenleiList from '../pages/qingxufenlei/list'
import qingxufenleiDetail from '../pages/qingxufenlei/detail'
import qingxufenleiAdd from '../pages/qingxufenlei/add'
import qingxurizhiList from '../pages/qingxurizhi/list'
import qingxurizhiDetail from '../pages/qingxurizhi/detail'
import qingxurizhiAdd from '../pages/qingxurizhi/add'
import yinlexinxiList from '../pages/yinlexinxi/list'
import yinlexinxiDetail from '../pages/yinlexinxi/detail'
import yinlexinxiAdd from '../pages/yinlexinxi/add'
import youxixinxiList from '../pages/youxixinxi/list'
import youxixinxiDetail from '../pages/youxixinxi/detail'
import youxixinxiAdd from '../pages/youxixinxi/add'
import chatmessageList from '../pages/chatmessage/list'
import chatmessageDetail from '../pages/chatmessage/detail'
import chatmessageAdd from '../pages/chatmessage/add'
import friendList from '../pages/friend/list'
import friendDetail from '../pages/friend/detail'
import friendAdd from '../pages/friend/add'
import syslogList from '../pages/syslog/list'
import syslogDetail from '../pages/syslog/detail'
import syslogAdd from '../pages/syslog/add'
import sensitivewordsList from '../pages/sensitivewords/list'
import sensitivewordsDetail from '../pages/sensitivewords/detail'
import sensitivewordsAdd from '../pages/sensitivewords/add'
import discussqingxuxuanxieList from '../pages/discussqingxuxuanxie/list'
import discussqingxuxuanxieDetail from '../pages/discussqingxuxuanxie/detail'
import discussqingxuxuanxieAdd from '../pages/discussqingxuxuanxie/add'
import discussqingxuwenzhangList from '../pages/discussqingxuwenzhang/list'
import discussqingxuwenzhangDetail from '../pages/discussqingxuwenzhang/detail'
import discussqingxuwenzhangAdd from '../pages/discussqingxuwenzhang/add'
import discussqingxurizhiList from '../pages/discussqingxurizhi/list'
import discussqingxurizhiDetail from '../pages/discussqingxurizhi/detail'
import discussqingxurizhiAdd from '../pages/discussqingxurizhi/add'
import discussyinlexinxiList from '../pages/discussyinlexinxi/list'
import discussyinlexinxiDetail from '../pages/discussyinlexinxi/detail'
import discussyinlexinxiAdd from '../pages/discussyinlexinxi/add'
import discussyouxixinxiList from '../pages/discussyouxixinxi/list'
import discussyouxixinxiDetail from '../pages/discussyouxixinxi/detail'
import discussyouxixinxiAdd from '../pages/discussyouxixinxi/add'

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
					path: 'examPaper',
					component: ExamPaper
				},
				{
					path: 'examList',
					component:ExamList
				},
				{
					path: 'examRecord/:type',
					component:ExamRecord
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'xuesheng',
					component: xueshengList
				},
				{
					path: 'xueshengDetail',
					component: xueshengDetail
				},
				{
					path: 'xueshengAdd',
					component: xueshengAdd
				},
				{
					path: 'zixunshi',
					component: zixunshiList
				},
				{
					path: 'zixunshiDetail',
					component: zixunshiDetail
				},
				{
					path: 'zixunshiAdd',
					component: zixunshiAdd
				},
				{
					path: 'zixunfuwu',
					component: zixunfuwuList
				},
				{
					path: 'zixunfuwuDetail',
					component: zixunfuwuDetail
				},
				{
					path: 'zixunfuwuAdd',
					component: zixunfuwuAdd
				},
				{
					path: 'yuyuezixunshi',
					component: yuyuezixunshiList
				},
				{
					path: 'yuyuezixunshiDetail',
					component: yuyuezixunshiDetail
				},
				{
					path: 'yuyuezixunshiAdd',
					component: yuyuezixunshiAdd
				},
				{
					path: 'biaoqianxinxi',
					component: biaoqianxinxiList
				},
				{
					path: 'biaoqianxinxiDetail',
					component: biaoqianxinxiDetail
				},
				{
					path: 'biaoqianxinxiAdd',
					component: biaoqianxinxiAdd
				},
				{
					path: 'qingxujianyi',
					component: qingxujianyiList
				},
				{
					path: 'qingxujianyiDetail',
					component: qingxujianyiDetail
				},
				{
					path: 'qingxujianyiAdd',
					component: qingxujianyiAdd
				},
				{
					path: 'qingxuxuanxie',
					component: qingxuxuanxieList
				},
				{
					path: 'qingxuxuanxieDetail',
					component: qingxuxuanxieDetail
				},
				{
					path: 'qingxuxuanxieAdd',
					component: qingxuxuanxieAdd
				},
				{
					path: 'qingxuwenzhang',
					component: qingxuwenzhangList
				},
				{
					path: 'qingxuwenzhangDetail',
					component: qingxuwenzhangDetail
				},
				{
					path: 'qingxuwenzhangAdd',
					component: qingxuwenzhangAdd
				},
				{
					path: 'qingxufenlei',
					component: qingxufenleiList
				},
				{
					path: 'qingxufenleiDetail',
					component: qingxufenleiDetail
				},
				{
					path: 'qingxufenleiAdd',
					component: qingxufenleiAdd
				},
				{
					path: 'qingxurizhi',
					component: qingxurizhiList
				},
				{
					path: 'qingxurizhiDetail',
					component: qingxurizhiDetail
				},
				{
					path: 'qingxurizhiAdd',
					component: qingxurizhiAdd
				},
				{
					path: 'yinlexinxi',
					component: yinlexinxiList
				},
				{
					path: 'yinlexinxiDetail',
					component: yinlexinxiDetail
				},
				{
					path: 'yinlexinxiAdd',
					component: yinlexinxiAdd
				},
				{
					path: 'youxixinxi',
					component: youxixinxiList
				},
				{
					path: 'youxixinxiDetail',
					component: youxixinxiDetail
				},
				{
					path: 'youxixinxiAdd',
					component: youxixinxiAdd
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
					path: 'syslog',
					component: syslogList
				},
				{
					path: 'syslogDetail',
					component: syslogDetail
				},
				{
					path: 'syslogAdd',
					component: syslogAdd
				},
				{
					path: 'sensitivewords',
					component: sensitivewordsList
				},
				{
					path: 'sensitivewordsDetail',
					component: sensitivewordsDetail
				},
				{
					path: 'sensitivewordsAdd',
					component: sensitivewordsAdd
				},
				{
					path: 'discussqingxuxuanxie',
					component: discussqingxuxuanxieList
				},
				{
					path: 'discussqingxuxuanxieDetail',
					component: discussqingxuxuanxieDetail
				},
				{
					path: 'discussqingxuxuanxieAdd',
					component: discussqingxuxuanxieAdd
				},
				{
					path: 'discussqingxuwenzhang',
					component: discussqingxuwenzhangList
				},
				{
					path: 'discussqingxuwenzhangDetail',
					component: discussqingxuwenzhangDetail
				},
				{
					path: 'discussqingxuwenzhangAdd',
					component: discussqingxuwenzhangAdd
				},
				{
					path: 'discussqingxurizhi',
					component: discussqingxurizhiList
				},
				{
					path: 'discussqingxurizhiDetail',
					component: discussqingxurizhiDetail
				},
				{
					path: 'discussqingxurizhiAdd',
					component: discussqingxurizhiAdd
				},
				{
					path: 'discussyinlexinxi',
					component: discussyinlexinxiList
				},
				{
					path: 'discussyinlexinxiDetail',
					component: discussyinlexinxiDetail
				},
				{
					path: 'discussyinlexinxiAdd',
					component: discussyinlexinxiAdd
				},
				{
					path: 'discussyouxixinxi',
					component: discussyouxixinxiList
				},
				{
					path: 'discussyouxixinxiDetail',
					component: discussyouxixinxiDetail
				},
				{
					path: 'discussyouxixinxiAdd',
					component: discussyouxixinxiAdd
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
		{
			path: '/exam',
			component: Exam
		}
	]
})
