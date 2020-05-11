import request from '@/utils/request'

// 查询popular列表
export function listPopular(query) {
  return request({
    url: '/ci/popular/list',
    method: 'get',
    params: query
  })
}

// 查询popular详细
export function getPopular(id) {
  return request({
    url: '/ci/popular/' + id,
    method: 'get'
  })
}

export function getPopularOptions(label) {
    return request({
        url: '/ci/popular/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增popular
export function addPopular(data) {
  return request({
    url: '/ci/popular',
    method: 'post',
    data: data
  })
}

// 修改popular
export function updatePopular(data) {
  return request({
    url: '/ci/popular',
    method: 'put',
    data: data
  })
}

// 删除popular
export function delPopular(id) {
  return request({
    url: '/ci/popular/' + id,
    method: 'delete'
  })
}

// 导出popular
export function exportPopular(query) {
  return request({
    url: '/ci/popular/export',
    method: 'get',
    params: query
  })
}