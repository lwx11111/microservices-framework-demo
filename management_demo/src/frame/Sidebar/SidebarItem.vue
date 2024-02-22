<template>
    <div>
        <!-- 有二级分类的一级分类 -->
        <el-sub-menu
            class="myMenu"
            v-if="item.children && item.children.length > 0"
            :key="item.funId"
            :index="item.funId.toString()"
            teleported>
            <template #title>
                <component :is="item.icon" style="width: 16px;height: 16px;"></component>
                <span>{{item.funName}}</span>
            </template>
            <!--二级分类-->
            <router-link v-for="(citem,cindex) in item.children"
                         :key="cindex" :to="resolvePath(citem.url)">
                <el-menu-item :index="citem.url">
                    <template #title>
                        <component :is="citem.icon" style="width: 16px;height: 16px;"></component>
                        <span>{{citem.funName}}</span>
                    </template>
                </el-menu-item>
            </router-link>
        </el-sub-menu>
        <!--只有一级分类-->
        <router-link class="myMenu"
                     v-else-if="item.children == null || item.children.length == 0"
                    :key="item.funId.toString()"
                    :to="resolvePath(item.url)"
                    :index="item.funId.toString()">
            <el-menu-item :index="item.funId.toString()">
                <template #title>
                    <component :is="item.icon" style="width: 16px;height: 16px;"></component>
                    <span>{{item.funName}}</span>
                </template>
            </el-menu-item>
        </router-link>
    </div>
</template>

<script lang="js" setup>
import {onMounted, reactive} from "vue";
import {
    HelpFilled,
    Flag,
} from '@element-plus/icons-vue'


const data = reactive({

})

// Props
const props = defineProps({
    item: {
        type: Object,
        required: true
    },
    isNest: {
        type: Boolean,
        default: false
    },
    basePath: {
        type: String,
        default: ''
    }
})

// Mounted
onMounted(() => {

})

// Methods
const resolvePath = (routePath) => {
    return routePath;
}
</script>

<style scoped lang="scss">
    a {
        text-decoration: none;
    }

</style>
