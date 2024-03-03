<template>
    <div>
        <el-scrollbar >
            <el-menu style="margin-right: -1px;"
                class="myMenu"
                default-active="1"
                :collapse="data.isCollapse"
                :unique-opened="true"
                :default-openeds="data.openeds"
                :collapse-transition="false"
                mode="vertical"
                active-text-color="#ffd04b"
                background-color="#545c64"
                text-color="#fff"
            >
            <sidebar-item v-for="route in data.indexDate" :key="route.funId.toString()" :item="route" :base-path="route.url"/>
            <sidebar-item v-for="route in data.menuData"
                          :key="route.funId.toString()"
                          :item="route"
                          :base-path="route.url"/>
            </el-menu>
        </el-scrollbar>
    </div>
</template>

<script lang="js" setup>
    import { useStore } from "vuex";
    import { useRouter } from 'vue-router'
    import SidebarItem from './SidebarItem.vue'
    import { reactive } from "vue";

    const store = useStore();
    const router = useRouter()

    const data = reactive({
        showLogo: true,
        isCollapse: false,
        openeds: [],
        menuData: [],
        indexDate: [
            {
                'funId': '1',
                'funName': '首页',
                'url': '/homePage',
                'icon':'House'
            },
            {
                'funId': '2',
                'funName': '物品管理',
                'icon':'Document',
                'children':[{
                    'funId': '21',
                    'funName': '物品列表',
                    'url': '/shopitem/index',
                    'icon': 'Document'
                }]
            },
            {
                'funId': '3',
                'funName': '用户管理',
                'icon': 'Document',
                'children':[{
                    'funId': '31',
                    'funName': '用户列表',
                    'url': '/user/index',
                    'icon': 'Document'
                }]
            },
            {
                'funId': '4',
                'funName': '评论管理',
                'icon': 'Document',
                'children':[{
                    'funId': '41',
                    'funName': '评论列表',
                    'url': '/comment/index',
                    'icon': 'Document'
                }]
            },
        ]
    })
</script>
