<template>
    <el-card class="login-form-content">
        <h1 style="text-align: center">注册</h1>
        <el-form :model="data.form"
                 ref="formRef"
                 :rules="data.rules"
                 label-width="100px">
            <el-input v-model="data.form.username"
                      class="input-item"
                      placeholder="用户名">
                <template #prefix>
                    <el-icon style="color: gold"><Avatar /></el-icon>
                </template>
            </el-input>
            <el-input v-model="data.form.password"
                      class="input-item flex align-center"
                      show-password
                      placeholder="密码">
                <template #prefix>
                    <el-icon style="color: gold"><Lock /></el-icon>
                </template>
            </el-input>
            <el-input v-model="data.form.confirmPassword"
                      class="input-item flex align-center"
                      show-password
                      placeholder="确认密码">
                <template #prefix>
                    <el-icon style="color: gold"><Lock /></el-icon>
                </template>
            </el-input>
            <el-input v-model="data.form.phone"
                      class="input-item flex align-center"
                      placeholder="手机号">
                <template #prefix>
                    <el-icon style="color: gold"><Iphone /></el-icon>
                </template>
            </el-input>
            <el-button style="width: 100%; height: 45px;"
                       type="primary"
                       @click="submitForm()">
                注册
            </el-button>
        </el-form>
    </el-card>
</template>

<script lang="ts" setup>
import {reactive, onMounted, ref, getCurrentInstance} from 'vue';
import Api from '@/api/api_user.js';

import { useStore } from "vuex";
import {useRouter} from "vue-router";
import {ElMessage} from "element-plus";
const store = useStore();
const router = useRouter()

// Data
const data = reactive({

    form : {
        username: '',
        password: '',
        confirmPassword: '',
        phone: '',
    },
    rules: {

    }
})


// Mounted
onMounted(() => {

})


const validateForm = () => {
    if (data.form.username === ''){
        ElMessage.error('用户名不能为空');
        return false;
    }
    if (data.form.password === ''){
        ElMessage.error('密码不能为空');
        return false;
    }
    if (data.form.confirmPassword === ''){
        ElMessage.error('确认密码不能为空');
        return false;
    }
    if (data.form.phone === ''){
        ElMessage.error('电话号不能为空');
        return false;
    }

    if (data.form.password !== data.form.confirmPassword) {
        ElMessage.error('两次密码不一致');
        return false;
    }
    if (data.form.phone.length !== 11){
        ElMessage.error('电话不满足11位');
        return false;
    }
    return true;
}

// 提交表单的方法
const formRef = ref();
const submitForm = () => {
    // 表单验证
    formRef.value.validate(valid => {
        if (valid) {
            if (validateForm()){
                const data1 = {
                    name: data.form.username,
                    password: data.form.password,
                    phone: data.form.phone
                }

                Api.register(data1).then(res => {
                    console.log(res);
                    if (res.code === 200){
                        ElMessage.success('注册成功');
                        // 页面跳转
                        router.push({
                            path: '/login',
                        })
                    } else {
                        ElMessage.error('注册失败');
                    }
                })
            }
        }
    })
}
</script>

<style scoped>

.login-form-content {
    text-align: center;
    margin-top: 5%;
    margin-left: 17%;
    width: 70%;
    height: 140%;
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
