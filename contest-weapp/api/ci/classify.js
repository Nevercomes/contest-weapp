import http from '@/utils/request.js'

export function listClassify(query) {
	return http.request({
		url: '/ci/classify/list',
		method: 'get',
		params: query
	})
}