<template>
    <div class="navbar">
        <el-row>
            <el-col :span="14" >
                <div class="l-content">
                    <el-breadcrumb separator="/">
                        <el-breadcrumb-item
                            v-for="(item,index) in data.breadList"
                            :key="index"
                            :to="{ path: item.path }"
                        >{{item.name}}</el-breadcrumb-item>
                    </el-breadcrumb>
                </div>
            </el-col>
            <el-col :span="10">
                <div class="right-menu">
                    <el-dropdown class="avatar-container right-menu-item hover-effect"
                                 trigger="click">
                        <div class="avatar-wrapper">
                            <img class="user-avatar" src="../assets/images/login.jpg" alt="">
                            <span class="user-name">{{ data.name }}</span>
                            <i class="el-icon-caret-bottom"/>
                        </div>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item>
                                        <span style="display:block;"
                                              @click="handleModifyPass()">
                                            修改密码
                                        </span>
                                </el-dropdown-item>
                                <el-dropdown-item divided>
                                        <span style="display:block;"
                                              @click="logout()">
                                            退出系统
                                        </span>
                                </el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>
            </el-col>
        </el-row>

        <el-dialog title="修改密码"
                   :modal="true"
                   :append-to-body="false"
                   :close-on-click-modal="false"
                   v-model="data.dialogVisible"
                   width="400px">
            <template #header>
                <i class="el-icon-key"/> 修改密码
            </template>
            <el-form ref="form" :model="data.form" label-width="100px" :rules="data.rules">
                <el-form-item label="账户名">
                    <el-input v-model="data.form.name" style="width:250px" :disabled="true"/>
                </el-form-item>
                <el-form-item label="旧密码" prop="oldPass">
                    <el-input v-model="data.form.oldPass" style="width:250px" show-password/>
                </el-form-item>
                <el-form-item label="新密码" prop="newPass">
                    <el-input v-model="data.form.newPass" style="width:250px" show-password/>
                </el-form-item>
                <el-form-item label="确认密码" prop="confirmPass">
                    <el-input v-model="data.form.confirmPass" style="width:250px" show-password/>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="data.dialogVisible = false">取 消</el-button>
                <el-button type="danger" @click="handleSavePass()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script lang="js" setup>
import { useStore } from 'vuex'
import { onMounted, reactive, ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import Api from '@/api/api_user.js'
import {ElMessage} from "element-plus";

import {getEncryptPassword} from "@/utils/passwordEncrypt";
import { onBeforeRouteUpdate } from "vue-router";

const store = useStore();
const router = useRouter()
const route = useRoute();

const data = reactive({
    // 路由集合
    breadList: [],
    sidebarOpened: false,
    dialogVisible: false,
    form: {
        oldPass: '',
        newPass: '',
        confirmPass: '',
        accountId: localStorage.getItem("userId"),
    },
    rules: {
        oldPass: [
            {required: true, message: '请输入旧密码', trigger: 'blur'}
        ],
        newPass: [
            {
                required: true,
                // pattern: /(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}/,
                message: '请输入新密码',
                trigger: 'blur'
            }
        ],
        confirmPass: [
            // {validator: validatePass, trigger: 'blur'}
        ]
    }
})


// Mounted
onMounted(() => {
    data.name = localStorage.getItem("userName")
    data.form.name = data.name;

    getBreadcrumb();
})

/**
 * 路由变化
 */
onBeforeRouteUpdate((val, oldVal) => {
    getBreadcrumb(val.matched);
});

// Methods
const isHome = (route) => {
    return route.name === "首页";
}

const getBreadcrumb = (matched) => {
    if (matched === undefined) {
        matched = route.matched;
    }
    //如果不是首页
    if (!isHome(matched[0])) {
        matched = [{ path: "/home", meta: { title: "首页" } }].concat(matched);
    }
    data.breadList = matched;
}

const validatePass = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请输入确认密码'));
    } else if (value !== this.form.newPass) {
        callback(new Error('两次输入密码不一致!'));
    } else {
        callback();
    }
};

/**
 * 修改密码
 */
const handleModifyPass = () => {
    data.dialogVisible = true;
}

/**
 * 确认修改密码
 */
const form = ref();
const handleSavePass = () => {
    form.value.validate(valid => {
        if (valid) {
            data.form.newPass = getEncryptPassword(data.form.newPass, 'aes');
            data.form.oldPass = getEncryptPassword(data.form.oldPass, 'aes');
            // Api.modifyPass(data.form).then(res => {
            //     console.log(res)
            //     if (res.code === '20000'){
            //         ElMessage.success('修改成功');
            //         logout();
            //     } else {
            //         ElMessage.error(res.message)
            //     }
            // })
        } else {
            return false;
        }
    });
}

/**
 * 退出系统
 */
const logout = () => {
    Api.logout().then(res => {
        removeToken();
        router.push({
            path: '/login',
        })
    })
}
</script>

<style lang="scss" scoped>
.l-content{
    margin-top: 20px;
}
.navbar {
    margin-left: 10px;
  width: 99%;
  height: 60px;
  overflow: hidden;
  position: relative;
  background: #fafafa;
  box-shadow: 0 1px 4px rgba(0, 21, 41, .08);


  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 20px;
          vertical-align: middle;
          margin-bottom: 8px;
        }

        .user-name {
          font-size: 16px;
          color: #000;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}

</style>
