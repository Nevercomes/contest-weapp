import request from '@/utils/request'

// 查询队伍信息列表
export function listTeamInfo(query) {
  return request({
    url: '/ci/teamInfo/list',
    method: 'get',
    params: query
  })
}

// 查询队伍信息详细
export function getTeamInfo(id) {
  return request({
    url: '/ci/teamInfo/' + id,
    method: 'get'
  })
}

export function getTeamInfoOptions(label) {
    return request({
        url: '/ci/teamInfo/options',
        method: 'get',
        params: {
          name: label || ''
        }
    })
}

// 新增队伍信息
export function addTeamInfo(data) {
  return request({
    url: '/ci/teamInfo',
    method: 'post',
    data: data
  })
}

// 修改队伍信息
export function updateTeamInfo(data) {
  return request({
    url: '/ci/teamInfo',
    method: 'put',
    data: data
  })
}

// 删除队伍信息
export function delTeamInfo(id) {
  return request({
    url: '/ci/teamInfo/' + id,
    method: 'delete'
  })
}

// 导出队伍信息
export function exportTeamInfo(query) {
  return request({
    url: '/ci/teamInfo/export',
    method: 'get',
    params: query
  })
}