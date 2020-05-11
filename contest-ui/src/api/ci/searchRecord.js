import request from '@/utils/request'

// 查询searchRecord列表
export function listSearchRecord(query) {
  return request({
    url: '/ci/searchRecord/list',
    method: 'get',
    params: query
  })
}

// 查询searchRecord详细
export function getSearchRecord(id) {
  return request({
    url: '/ci/searchRecord/' + id,
    method: 'get'
  })
}

export function getSearchRecordOptions(label) {
    return request({
        url: '/ci/searchRecord/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增searchRecord
export function addSearchRecord(data) {
  return request({
    url: '/ci/searchRecord',
    method: 'post',
    data: data
  })
}

// 修改searchRecord
export function updateSearchRecord(data) {
  return request({
    url: '/ci/searchRecord',
    method: 'put',
    data: data
  })
}

// 删除searchRecord
export function delSearchRecord(id) {
  return request({
    url: '/ci/searchRecord/' + id,
    method: 'delete'
  })
}

// 导出searchRecord
export function exportSearchRecord(query) {
  return request({
    url: '/ci/searchRecord/export',
    method: 'get',
    params: query
  })
}