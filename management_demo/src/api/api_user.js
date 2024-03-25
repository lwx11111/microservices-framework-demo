import request from '@/utils/request'
const serverName = 'uaa'


export default {
  login(data) {
    return request({
      url: serverName + '/user/login',
      method: 'post',
      data: data
    })
  },

  // 分页查询
  selpage4user(data) {
    return request({
      url: serverName + '/user/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4user(obj) {
    return request({
      url: serverName + '/user',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4user(id, obj) {
    return request({
      url: serverName + '/user/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4user(id) {
    return request({
      url: serverName + '/user/' + id,
      method: 'delete',
    })
  },


  // 删除多条
  dels4user(ids) {
    return request({
      url: serverName + '/user/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4user(id) {
    return request({
      url: serverName + '/user/' + id,
      method: 'get',
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/user/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return request({
      url: serverName + '/user/uploadExcel',
      type: 'form',
      method: 'post',
    })

  },
  // 导出Excel
  excelData4user(params) {
    return request({
      url: serverName + '/user/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
