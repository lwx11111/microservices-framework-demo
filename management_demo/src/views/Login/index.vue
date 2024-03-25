<template>
    <el-card class="login-form-content">
        <h1 style="text-align: center">登录</h1>
        <el-form :model="form"
                 ref="formRef">
            <el-input v-model="form.username"
                      class="input-item"
                      placeholder="用户名">
                <template #prefix>
                    <el-icon style="color: blue"><Avatar /></el-icon>
                </template>
            </el-input>
            <el-input v-model="form.password"
                      placeholder="密码"
                      class="input-item"
                      show-password>
                <template #prefix>
                    <el-icon style="color: blue"><Lock /></el-icon>
                </template>
            </el-input>

            <el-button style="width: 100%; height: 45px;"
                       type="warning"
                       @click="onSubmit()">
                登录
            </el-button>
            <el-text class="input-item" @click="toRegister()" type="primary">去注册</el-text>
        </el-form>
    </el-card>
</template>

<script lang="ts" setup>
import {getCurrentInstance, onMounted, reactive, ref} from 'vue'
import Api from '@/api/api_user.js';
import { useStore } from "vuex";
import { useRouter} from "vue-router";
import {ElMessage} from "element-plus";

const store = useStore();
const router = useRouter()

const form = reactive({
    username: '',
    password: '',
})

// Mounted
onMounted(() => {

})

/**
 * 登录校验
 */
const onSubmit = () => {
    if (form.username === ''){
        ElMessage.error('用户名不能为空');
        return;
    }
    if (form.password === ''){
        ElMessage.error('密码不能为空');
        return;
    }
    loginWithCode();
}

/**
 * 请求后端登录
 */
const loginWithCode = () => {

    const params = {
        name: form.username,
        password: form.password,
    };

    Api.login(params).then(res => {
        console.log(res)
        if (res.code === 200){
            localStorage.setItem("userId", res.data.id);
            localStorage.setItem("userName", res.data.name);
            // 跳转
            router.push({
                path: '/homePage',
            })
        } else {
            ElMessage.error(res.message)
        }
    });
}

/**
 * 跳转注册页面
 */
const toRegister = () => {
    router.push({
        path: '/register',
    })
}
</script>

<style scoped>

.login-form-content {
    text-align: center;
    margin-top: 15%;
    margin-left: 25%;
    width: 40%;
    height: 90%;
    /*background-color: #f5f6f7;*/
    .input-item {
        margin: 20px auto;
        height: 45px;
        border-radius: 20px;
    }

    .login-code {
        margin: 20px auto;
        height: 45px;
        float: right;
    }
}
</style>
