import request from '@/utils/request'

// 查询组队意愿列表
export function listExpect(query) {
  return request({
    url: '/ci/expect/list',
    method: 'get',
    params: query
  })
}

// 查询组队意愿详细
export function getExpect(id) {
  return request({
    url: '/ci/expect/' + id,
    method: 'get'
  })
}

export function getExpectOptions(label) {
    return request({
        url: '/ci/expect/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增组队意愿
export function addExpect(data) {
  return request({
    url: '/ci/expect',
    method: 'post',
    data: data
  })
}

// 修改组队意愿
export function updateExpect(data) {
  return request({
    url: '/ci/expect',
    method: 'put',
    data: data
  })
}

// 删除组队意愿
export function delExpect(id) {
  return request({
    url: '/ci/expect/' + id,
    method: 'delete'
  })
}

// 导出组队意愿
export function exportExpect(query) {
  return request({
    url: '/ci/expect/export',
    method: 'get',
    params: query
  })
}