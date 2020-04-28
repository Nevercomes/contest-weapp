import request from '@/utils/request'

// 查询OSS上传列表
export function listOssRecord(query) {
  return request({
    url: '/system/ossRecord/list',
    method: 'get',
    params: query
  })
}

// 查询OSS上传详细
export function getOssRecord(id) {
  return request({
    url: '/system/ossRecord/' + id,
    method: 'get'
  })
}

export function getOssRecordOptions(label) {
    return request({
        url: '/system/ossRecord/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增OSS上传
export function addOssRecord(data) {
  return request({
    url: '/system/ossRecord',
    method: 'post',
    data: data
  })
}

// 修改OSS上传
export function updateOssRecord(data) {
  return request({
    url: '/system/ossRecord',
    method: 'put',
    data: data
  })
}

// 删除OSS上传
export function delOssRecord(id) {
  return request({
    url: '/system/ossRecord/' + id,
    method: 'delete'
  })
}

// 导出OSS上传
export function exportOssRecord(query) {
  return request({
    url: '/system/ossRecord/export',
    method: 'get',
    params: query
  })
}