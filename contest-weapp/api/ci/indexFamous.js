import http from '@/utils/request.js'

export function listIndexFamous(query) {
	return http.request({
		url: '/ci/index/famous/list',
		method: 'get',
		params: query,
		anonymous: true
	})
}