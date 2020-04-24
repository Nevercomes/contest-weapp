import request from '@/utils/request'

// 查询入队邀请列表
export function listInvite(query) {
  return request({
    url: '/ci/invite/list',
    method: 'get',
    params: query
  })
}

// 查询入队邀请详细
export function getInvite(id) {
  return request({
    url: '/ci/invite/' + id,
    method: 'get'
  })
}

export function getInviteOptions(label) {
    return request({
        url: '/ci/invite/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增入队邀请
export function addInvite(data) {
  return request({
    url: '/ci/invite',
    method: 'post',
    data: data
  })
}

// 修改入队邀请
export function updateInvite(data) {
  return request({
    url: '/ci/invite',
    method: 'put',
    data: data
  })
}

// 删除入队邀请
export function delInvite(id) {
  return request({
    url: '/ci/invite/' + id,
    method: 'delete'
  })
}

// 导出入队邀请
export function exportInvite(query) {
  return request({
    url: '/ci/invite/export',
    method: 'get',
    params: query
  })
}