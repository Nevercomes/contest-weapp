import request from '@/utils/request'

// 查询收藏内容列表
export function listCollection(query) {
  return request({
    url: '/ci/collection/list',
    method: 'get',
    params: query
  })
}

// 查询收藏内容详细
export function getCollection(id) {
  return request({
    url: '/ci/collection/' + id,
    method: 'get'
  })
}

export function getCollectionOptions(label) {
    return request({
        url: '/ci/collection/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增收藏内容
export function addCollection(data) {
  return request({
    url: '/ci/collection',
    method: 'post',
    data: data
  })
}

// 修改收藏内容
export function updateCollection(data) {
  return request({
    url: '/ci/collection',
    method: 'put',
    data: data
  })
}

// 删除收藏内容
export function delCollection(id) {
  return request({
    url: '/ci/collection/' + id,
    method: 'delete'
  })
}

// 导出收藏内容
export function exportCollection(query) {
  return request({
    url: '/ci/collection/export',
    method: 'get',
    params: query
  })
}