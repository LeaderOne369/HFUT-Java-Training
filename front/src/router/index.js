import { createWebHistory, createRouter } from 'vue-router'
import Index from '../pages/Index.vue'
import Dashboard from "../pages/Dashboard.vue";

const router = createRouter({
	history: createWebHistory(),
	routes: [
		{
			path: '/',
			redirect: '/project',
			component: Index,
			meta: { title: '首页' },
			children: [
				// {
				// 	path: '/dashboard',
				// 	name: 'dashboard',
				// 	component: () => import('../pages/Dashboard.vue'),
				// 	meta: { title: '图表分析' },
				// },
				{
					path: '/project',
					name: 'project',
					component: () => import('../pages/Project.vue'),
					meta: { title: '项目列表' },
				},
				{
					path: '/plan',
					name: 'plan',
					component: () => import('../pages/Plan.vue'),
					meta: { title: '项目计划' },
				},
				{
					path: '/budget',
					name: 'budget',
					component: () => import('../pages/Budget.vue'),
					meta: { title: '项目预算' },
				},
				{
					path: '/member',
					name: 'member',
					component: () => import('../pages/Member.vue'),
					meta: { title: '成员管理' },
				},
				{
					path: '/setting',
					name: 'setting',
					component: () => import('../pages/Setting.vue'),
					meta: { title: '个人中心' },
				},
			],
		},
		// {
		// 	path: '/login',
		// 	name: 'login',
		// 	component: () => import('../pages/Login.vue'),
		// 	meta: { title: '登录' },
		// },
		{
			path: '/login',
			name: 'login',
			component: () => import('../pages/Login2.vue'),
			meta: { title: '登录' },
		}
	],
})
export default router
