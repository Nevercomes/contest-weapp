import http from '@/utils/request.js'

// 查询关注竞赛列表
export function listConcernCp(query) {
  return request({
    url: '/ci/concernCp/list',
    method: 'GET',
    params: query
  })
}

// 修改关注竞赛
export function updateConcernCp(data) {
  return request({
    url: '/ci/concernCp',
    method: 'PUT',
    data: data
  })
}

// 查询关注竞赛详细
export function getConcernCp(id) {
  return request({
    url: '/ci/concernCp/' + id,
    method: 'GET'
  })
}

// 新增关注竞赛
export function addConcernCp(data) {
  return request({
    url: '/ci/concernCp',
    method: 'POST',
    data: data
  })
}

// 删除关注竞赛
export function delConcernCp(id) {
  return request({
    url: '/ci/concernCp/' + id,
    method: 'DELETE'
  })
}