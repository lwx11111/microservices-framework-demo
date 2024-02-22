import {
  createRouter,
  createWebHistory,
  createWebHashHistory,
} from 'vue-router'

import Login from '@/views/Login/index.vue';

import Frame from "@/frame/index.vue";
import HomePage from "@/views/homePage.vue";

const ShopItemIndex = () => import('@/views/shopitem/index.vue');
const UserIndex = () => import('@/views/user/index.vue');
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
        name: 'Login'
    },
    {
        path: '/',
        component: Frame,
        redirect: '/homePage',
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

