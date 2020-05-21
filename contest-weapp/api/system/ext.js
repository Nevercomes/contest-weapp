import http from '@/utils/request'

// 查询用户扩展信息列表
export function listExt(query) {
  return http.request({
    url: '/system/ext/list',
    method: 'get',
    params: query
  })
}

// 查询用户扩展信息详细
export function getExt(userId) {
  return http.request({
    url: userId ? '/system/ext/' + userId : '/system/ext/',
    method: 'get'
  })
}

export function getExtOptions(label) {
    return http.request({
        url: '/system/ext/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增用户扩展信息
export function addExt(data) {
  return http.request({
    url: '/system/ext',
    method: 'post',
    data: data
  })
}

// 修改用户扩展信息
export function updateExt(data) {
  return http.request({
    url: '/system/ext',
    method: 'put',
    data: data
  })
}

// 删除用户扩展信息
export function delExt(userId) {
  return http.request({
    url: '/system/ext/' + userId,
    method: 'delete'
  })
}

// 导出用户扩展信息
export function exportExt(query) {
  return request({
    url: '/system/ext/export',
    method: 'get',
    params: query
  })
}