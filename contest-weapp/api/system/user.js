import http from '@/utils/request.js'

export function identify(data) {
	return http.request({
		url: '/system/user/identify',
		method: 'POST',
		data: data
	})
}

// 查询用户个人信息
export function getUserProfile() {
  return request({
    url: '/system/user/profile',
    method: 'GET'
  })
}


