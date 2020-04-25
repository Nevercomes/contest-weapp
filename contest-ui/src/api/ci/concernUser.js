import request from '@/utils/request'

// 查询关注用户列表
export function listConcernUser(query) {
  return request({
    url: '/ci/concernUser/list',
    method: 'get',
    params: query
  })
}

// 查询关注用户详细
export function getConcernUser(id) {
  return request({
    url: '/ci/concernUser/' + id,
    method: 'get'
  })
}

export function getConcernUserOptions(label) {
    return request({
        url: '/ci/concernUser/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增关注用户
export function addConcernUser(data) {
  return request({
    url: '/ci/concernUser',
    method: 'post',
    data: data
  })
}

// 修改关注用户
export function updateConcernUser(data) {
  return request({
    url: '/ci/concernUser',
    method: 'put',
    data: data
  })
}

// 删除关注用户
export function delConcernUser(id) {
  return request({
    url: '/ci/concernUser/' + id,
    method: 'delete'
  })
}

// 导出关注用户
export function exportConcernUser(query) {
  return request({
    url: '/ci/concernUser/export',
    method: 'get',
    params: query
  })
}