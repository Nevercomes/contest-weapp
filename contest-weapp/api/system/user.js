import http from '@/utils/request.js'

export function identify(data) {
	return http.request({
		url: '/system/user/identify',
		method: 'POST',
		data: data
	})
}

