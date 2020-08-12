import request from '@/utils/request'

// 查询默认团队头像列表
export function listTeamAvatar(query) {
  return request({
    url: '/ci/teamAvatar/list',
    method: 'get',
    params: query
  })
}

// 查询默认团队头像详细
export function getTeamAvatar(id) {
  return request({
    url: '/ci/teamAvatar/' + id,
    method: 'get'
  })
}

export function getTeamAvatarOptions(label) {
    return request({
        url: '/ci/teamAvatar/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增默认团队头像
export function addTeamAvatar(data) {
  return request({
    url: '/ci/teamAvatar',
    method: 'post',
    data: data
  })
}

// 修改默认团队头像
export function updateTeamAvatar(data) {
  return request({
    url: '/ci/teamAvatar',
    method: 'put',
    data: data
  })
}

// 删除默认团队头像
export function delTeamAvatar(id) {
  return request({
    url: '/ci/teamAvatar/' + id,
    method: 'delete'
  })
}

// 导出默认团队头像
export function exportTeamAvatar(query) {
  return request({
    url: '/ci/teamAvatar/export',
    method: 'get',
    params: query
  })
}