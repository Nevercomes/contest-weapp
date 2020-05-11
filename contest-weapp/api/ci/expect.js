import http from '@/utils/request.js'

// 查询组队意愿列表
export function listExpect(query) {
  return hhtp.request({
    url: '/ci/expect/list',
    method: 'GET',
    params: query
  })
}

// 新增组队意愿
export function addExpect(data) {
  return hhtp.request({
    url: '/ci/expect',
    method: 'POST',
    data: data
  })
}