import http from '@/utils/request.js'

// 新增队伍信息
export function addTeamInfo(data) {
  return http.request({
    url: '/ci/teamInfo',
    method: 'POST',
    data: data
  })
}

// 新增队伍信息
export function listTeamInfo(query) {
  return request({
    url: '/ci/teamInfo/list',
    method: 'GET',
    params: query
  })
}