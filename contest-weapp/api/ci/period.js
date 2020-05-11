import http from '@/utils/request.js'

// 查询竞赛列表信息列表
export function listPeriod(query) {
  return http.request({
    url: '/ci/period/list',
    method: 'GET',
    params: query
  })
}