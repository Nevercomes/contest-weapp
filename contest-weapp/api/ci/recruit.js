import http from '@/utils/request.js'

// 查询队员招募列表
export function listRecruit(query) {
  return http.request({
    url: '/ci/recruit/list',
    method: 'get',
    params: query
  })
}

// 查询队员招募详细
export function getRecruit(id) {
  return http.request({
    url: '/ci/recruit/' + id,
    method: 'get'
  })
}

export function getRecruitOptions(label) {
    return http.request({
        url: '/ci/recruit/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增队员招募
export function addRecruit(data) {
  return http.request({
    url: '/ci/recruit',
    method: 'post',
    data: data
  })
}

// 修改队员招募
export function updateRecruit(data) {
  return http.request({
    url: '/ci/recruit',
    method: 'put',
    data: data
  })
}

// 删除队员招募
export function delRecruit(id) {
  return http.request({
    url: '/ci/recruit/' + id,
    method: 'delete'
  })
}

// 导出队员招募
export function exportRecruit(query) {
  return http.request({
    url: '/ci/recruit/export',
    method: 'get',
    params: query
  })
}