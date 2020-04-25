import request from '@/utils/request'

// 查询关注竞赛列表
export function listConcernCp(query) {
  return request({
    url: '/ci/concernCp/list',
    method: 'get',
    params: query
  })
}

// 查询关注竞赛详细
export function getConcernCp(id) {
  return request({
    url: '/ci/concernCp/' + id,
    method: 'get'
  })
}

export function getConcernCpOptions(label) {
    return request({
        url: '/ci/concernCp/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增关注竞赛
export function addConcernCp(data) {
  return request({
    url: '/ci/concernCp',
    method: 'post',
    data: data
  })
}

// 修改关注竞赛
export function updateConcernCp(data) {
  return request({
    url: '/ci/concernCp',
    method: 'put',
    data: data
  })
}

// 删除关注竞赛
export function delConcernCp(id) {
  return request({
    url: '/ci/concernCp/' + id,
    method: 'delete'
  })
}

// 导出关注竞赛
export function exportConcernCp(query) {
  return request({
    url: '/ci/concernCp/export',
    method: 'get',
    params: query
  })
}