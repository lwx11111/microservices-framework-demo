import request from '@/utils/request'
const serverName = 'comment'


export default {

  // 分页查询
  selpage4comment(data) {
    return request({
      url: serverName + '/comment/selpage',
      method: 'post',
      data: data
    })
  },

  // 添加
  add4comment(obj) {
    return request({
      url: serverName + '/comment',
      method: 'post',
      data: obj
    })
  },

  // 修改
  update4comment(id, obj) {
    return request({
      url: serverName + '/comment/' + id,
      method: 'put',
      data: obj
    })
  },

  // 删除单条
  del4comment(id) {
    return request({
      url: serverName + '/comment/' + id,
      method: 'delete',
    })
  },


  // 删除多条
  dels4comment(ids) {
    return request({
      url: serverName + '/comment/dels',
      method: 'post',
      data: ids
    })
  },

  // 查询单条
  sel4comment(id) {
    return request({
      url: serverName + '/comment/' + id,
      method: 'get',
    })

  },

  // 下载Excel模板
  downloadExcelTemplate(params) {
    return request({
      url: serverName + '/comment/downloadExcelTemplate',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  },

  // 导入Excel接口URL
  uploadExcelUrl() {
    return serverName + '/comment/uploadExcel';
  },
  // 导出Excel
  excelData4comment(params) {
    return request({
      url: serverName + '/comment/excel',
      method: 'post',
      data: params,
      responseType: 'arraybuffer'
    })
  }

}
