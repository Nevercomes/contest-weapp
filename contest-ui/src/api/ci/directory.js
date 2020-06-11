import request from '@/utils/request'

// 查询收藏夹列表
export function listDirectory(query) {
  return request({
    url: '/ci/directory/list',
    method: 'get',
    params: query
  })
}

// 查询收藏夹详细
export function getDirectory(id) {
  return request({
    url: '/ci/directory/' + id,
    method: 'get'
  })
}

export function getDirectoryOptions(label) {
    return request({
        url: '/ci/directory/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增收藏夹
export function addDirectory(data) {
  return request({
    url: '/ci/directory',
    method: 'post',
    data: data
  })
}

// 修改收藏夹
export function updateDirectory(data) {
  return request({
    url: '/ci/directory',
    method: 'put',
    data: data
  })
}

// 删除收藏夹
export function delDirectory(id) {
  return request({
    url: '/ci/directory/' + id,
    method: 'delete'
  })
}

// 导出收藏夹
export function exportDirectory(query) {
  return request({
    url: '/ci/directory/export',
    method: 'get',
    params: query
  })
}