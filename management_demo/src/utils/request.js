/**
 * ajax请求配置
 */
import axios from 'axios'
import {ElMessage} from 'element-plus'
import qs from "qs";

// axios默认配置
axios.defaults.timeout = 10000 // 超时时间

// 网关地址
axios.defaults.baseURL = 'http://127.0.0.1:8888';
// axios.defaults.baseURL = 'http://43.138.149.121:8888';
// 整理数据
axios.defaults.transformRequest = function(data) {
    return data
}

// 路由请求拦截
axios.interceptors.request.use(
    config => {
        // 网关跨域问题解决不了了
        // if (config.url.includes("app")){
        //     config.baseURL = 'http://127.0.0.1:7000'
        //     config.url = config.url.replace("app", "")
        // }
        //
        // if (config.url.includes("user")){
        //     config.baseURL = 'http://127.0.0.1:8000'
        //     config.url = config.url.replace("user", "")
        // }
        //
        // if (config.url.includes("comment")){
        //     config.baseURL = 'http://127.0.0.1:8001'
        //     config.url = config.url.replace("comment", "")
        // }


        if (config.type === 'form'){
            // 后端@RequestParams注解接收
            config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
            config.data = qs.stringify(config.data)
        } else if (config.type === 'file') {
            // 后端@RequestParams注解接收
            config.headers['Content-Type'] = 'multipart/form-data'
            config.data = qs.stringify(config.data)
        } else {
            // 后端@RequestBody注解接收
            config.headers['Content-Type'] = 'application/json;charset=UTF-8'
            config.data = JSON.stringify(config.data)
        }

        return config
    },
    error => {
        return Promise.reject(error.response)
    }
)

// 路由响应拦截
axios.interceptors.response.use(
    response => {
        if (response.headers && (response.headers['content-type'] === 'application/x-msdownload' ||
                response.headers['content-type'].indexOf('application/vnd.ms-excel') !== -1 ||
                response.headers['content-type'].indexOf('application/octet-stream') !== -1)) {
            return response;
        } else {
            return response.data
        }
    },
    error => {
        console.log('err' + error) // for debug
        if (error && error.response) {
            const {status} = error.response;
            if (status === 401) {
                ElMessage.error('Token值无效，请重新登录');
                router.replace('/login');
            } else {

            }
        } else {
            ElMessage.error('网络出现问题，请稍后再试');
        }
        return Promise.reject(error)
    }
)
export default axios
