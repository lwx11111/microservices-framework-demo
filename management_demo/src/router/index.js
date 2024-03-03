import {
  createRouter,
  createWebHistory,
  createWebHashHistory,
} from 'vue-router'

import Frame from "@/frame/index.vue";
import HomePage from "@/views/homePage.vue";

const ShopItemIndex = () => import('@/views/shopitem/index.vue');
const UserIndex = () => import('@/views/user/index.vue');
const Register = () => import('@/views/Login/register.vue')
const Login = () => import('@/views/Login/index.vue')
const CommentIndex = () => import('@/views/comment/index.vue');
// 模块化路由
const routes = [
    // 路由守卫 vue3动态路由问题导致刷新完页面会爆出No match found for location with path
    {
        path: "/:pathMatch(.*)*", // 必备
        component: () => import("@/views/404.vue"),
    },
    {
        path: '/login',
        component: Login,
        name: '登录'
    },
    {
        path: '/register',
        component: Register,
        name: '注册'
    },
    {
        path: '/',
        component: Frame,
        redirect: '/login',
        name: '首页',
        children: [
            {
                path: 'homePage',
                component: HomePage,
                name: '欢迎页',
            },
            {
                path: '/shopitem/index',
                component: ShopItemIndex,
                name: '物品管理'
            },
            {
                path: '/user/index',
                component: UserIndex,
                name: '用户管理'
            },
            {
                path: '/comment/index',
                component: CommentIndex,
                name: '评论管理'
            },

        ]
    }

]

// 创建路由对象
const router = createRouter({
  // history: createWebHashHistory(), // hash模式：createWebHashHistory，有#
  history: createWebHistory(),  // history模式：createWebHistory
  routes
})

export default router

