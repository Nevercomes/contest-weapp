import http from '@/utils/request.js'

export function search(keyword, type) {
	return http.request({
		url: '/search/result/' + type + "/" + keyword,
		method: 'get'
	})
}