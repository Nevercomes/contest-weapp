import http from '@/utils/request.js'

// 查询竞赛列表信息列表
export function listPeriod(query) {
  return http.request({
    url: '/ci/period/list',
    method: 'GET',
    params: query
  })
}

// 查询用户关注的竞赛
export function listConcernPeriod(query) {
  return http.request({
    url: '/ci/period/list/concern',
    method: 'get',
    params: query
  })
}

// 查询竞赛信息
export function getPeriod(id) {
  return http.request({
    url: '/ci/period/' + id,
    method: 'GET'
  })
}

export function getRecommendComp(query) {
	return http.request({
		url: '/ci/period/recommend',
		method: 'get',
		params: query
	})
}