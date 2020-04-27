import request from '@/utils/request'

// 查询系统消息列表
export function listNotice(query) {
  return request({
    url: '/ci/notice/list',
    method: 'get',
    params: query
  })
}

// 查询系统消息详细
export function getNotice(id) {
  return request({
    url: '/ci/notice/' + id,
    method: 'get'
  })
}

export function getNoticeOptions(label) {
    return request({
        url: '/ci/notice/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增系统消息
export function addNotice(data) {
  return request({
    url: '/ci/notice',
    method: 'post',
    data: data
  })
}

// 修改系统消息
export function updateNotice(data) {
  return request({
    url: '/ci/notice',
    method: 'put',
    data: data
  })
}

// 删除系统消息
export function delNotice(id) {
  return request({
    url: '/ci/notice/' + id,
    method: 'delete'
  })
}

// 导出系统消息
export function exportNotice(query) {
  return request({
    url: '/ci/notice/export',
    method: 'get',
    params: query
  })
}